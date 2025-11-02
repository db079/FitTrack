package com.fitnessapp.controller;

import com.fitnessapp.dto.DailyCaloriesDTO;
import com.fitnessapp.dto.GoalHistoryDTO;
import com.fitnessapp.model.CustomGoal;
import com.fitnessapp.model.Goal;
import com.fitnessapp.repository.CustomGoalRepository;
import com.fitnessapp.repository.GoalRepository;
import com.fitnessapp.repository.UserRepository;
import com.fitnessapp.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {
	
	 @Autowired
	    private GoalRepository goalRepository;

	    @Autowired
	    private CustomGoalRepository customGoalRepository;

	    
    @Autowired
    private HistoryService historyService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<GoalHistoryDTO>> getUserHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }
    
    
    @GetMapping("/calories/{userId}")
    public List<DailyCaloriesDTO> getDailyCalories(@PathVariable Long userId) {
        return historyService.getDailyCalories(userId);
    }
    
    @GetMapping("/graph/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getCaloriesGraph(@PathVariable Long userId) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        List<CustomGoal> customGoals = customGoalRepository.findByUserId(userId);

        Map<LocalDate, Double> caloriesByDate = new HashMap<>();

        // Process normal goals
        for (Goal goal : goals) {
            double calories = goal.isCompleted() ? goal.getCaloriesBurned() : 0.0;
            caloriesByDate.merge(goal.getDate(), calories, Double::sum);
        }

        // Process custom goals
        for (CustomGoal customGoal : customGoals) {
            double calories = customGoal.isCompleted() ? customGoal.getExpectedCalories() : 0.0;
            caloriesByDate.merge(customGoal.getDate(), calories, Double::sum);
        }

        // Determine range: last 7 days including today
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(6);

        // Fill missing days with 0 calories
        for (LocalDate date = startDate; !date.isAfter(today); date = date.plusDays(1)) {
            caloriesByDate.putIfAbsent(date, 0.0);
        }

        // Convert to sorted list (reverse order: newest → oldest)
        List<Map<String, Object>> result = caloriesByDate.entrySet().stream()
            .filter(entry -> !entry.getKey().isBefore(startDate))
            .sorted(Map.Entry.<LocalDate, Double>comparingByKey().reversed()) // ✅ reverse sort
            .map(entry -> {
                Map<String, Object> map = new HashMap<>();
                map.put("date", entry.getKey());
                map.put("caloriesBurned", entry.getValue());
                return map;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    /*
    @GetMapping("/graph/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getCaloriesGraph(@PathVariable() Long userId) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        List<CustomGoal> customGoals = customGoalRepository.findByUserId(userId);

        Map<LocalDate, Double> caloriesByDate = new HashMap<>();

        // Process normal goals
        for (Goal goal : goals) {
            double calories = goal.isCompleted() ? goal.getCaloriesBurned() : 0.0;
            caloriesByDate.merge(goal.getDate(), calories, Double::sum);
        }

        // Process custom goals
        for (CustomGoal customGoal : customGoals) {
            double calories = customGoal.isCompleted() ? customGoal.getExpectedCalories() : 0.0;
            caloriesByDate.merge(customGoal.getDate(), calories, Double::sum);
        }

        // Determine range: last 7 days including today
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(6);

        // Fill missing days with 0 calories
        for (LocalDate date = startDate; !date.isAfter(today); date = date.plusDays(1)) {
            caloriesByDate.putIfAbsent(date, 0.0);
        }

        // Convert to sorted list
        List<Map<String, Object>> result = caloriesByDate.entrySet().stream()
            .filter(entry -> !entry.getKey().isBefore(startDate)) // only recent 7 days
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> {
                Map<String, Object> map = new HashMap<>();
                map.put("date", entry.getKey());
                map.put("caloriesBurned", entry.getValue());
                return map;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
*/


}
