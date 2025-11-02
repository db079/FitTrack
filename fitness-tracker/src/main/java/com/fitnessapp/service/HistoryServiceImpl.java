package com.fitnessapp.service;

import com.fitnessapp.dto.DailyCaloriesDTO;
import com.fitnessapp.dto.GoalHistoryDTO;
import com.fitnessapp.model.CustomGoal;
import com.fitnessapp.model.Goal;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.CustomGoalRepository;
import com.fitnessapp.repository.GoalRepository;
import com.fitnessapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private CustomGoalRepository customGoalRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GoalHistoryDTO> getUserHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Goal> goals = goalRepository.findByUser(user);
        List<CustomGoal> customGoals = customGoalRepository.findByUser(user);

        List<GoalHistoryDTO> merged = new ArrayList<>();

        merged.addAll(goals.stream()
                .map(g -> GoalHistoryDTO.builder()
                        .name(g.getExercise().getName())
                        .description(g.getExercise().getDescription())
                        .duration(g.getDuration())
                        .caloriesBurned(g.getCaloriesBurned())
                        .expectedCalories(0)
                        .completed(g.isCompleted())
                        .date(g.getDate())
                        .type("GOAL")
                        .build())
                .collect(Collectors.toList()));

        merged.addAll(customGoals.stream()
                .map(c -> GoalHistoryDTO.builder()
                        .name(c.getCustomName())
                        .description(c.getCustomDescription())
                        .duration(c.getDuration())
                        .caloriesBurned(0)
                        .expectedCalories(c.getExpectedCalories())
                        .completed(c.isCompleted())
                        .date(c.getDate())
                        .type("CUSTOM_GOAL")
                        .build())
                .collect(Collectors.toList()));

        merged.sort(Comparator.comparing(GoalHistoryDTO::getDate).reversed());
        return merged;
    }
    public List<DailyCaloriesDTO> getDailyCalories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Goal> goals = goalRepository.findByUser(user);
        List<CustomGoal> customGoals = customGoalRepository.findByUser(user);

        Map<LocalDate, Double> caloriesByDate = new HashMap<>();

        // ✅ Add calories only from completed default goals
        for (Goal goal : goals) {
            if (goal.isCompleted()) { // count only completed goals
                caloriesByDate.merge(goal.getDate(), goal.getCaloriesBurned(), Double::sum);
            }
        }

        // ✅ Add calories only from completed custom goals
        for (CustomGoal custom : customGoals) {
            if (custom.isCompleted()) { // count only completed goals
                caloriesByDate.merge(custom.getDate(), custom.getExpectedCalories(), Double::sum);
            }
        }

        // ✅ Find min and max dates
        Optional<LocalDate> minDate = caloriesByDate.keySet().stream().min(LocalDate::compareTo);
        Optional<LocalDate> maxDate = caloriesByDate.keySet().stream().max(LocalDate::compareTo);

        List<DailyCaloriesDTO> result = new ArrayList<>();

        if (minDate.isPresent() && maxDate.isPresent()) {
            LocalDate current = minDate.get();
            while (!current.isAfter(maxDate.get())) {
                double calories = caloriesByDate.getOrDefault(current, 0.0);
                result.add(new DailyCaloriesDTO(current, calories));
                current = current.plusDays(1);
            }
        }

        // ✅ Sort by date ascending
        result.sort(Comparator.comparing(DailyCaloriesDTO::getDate));

        return result;
    }

    
}