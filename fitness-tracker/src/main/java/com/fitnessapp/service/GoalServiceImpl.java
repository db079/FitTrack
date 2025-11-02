package com.fitnessapp.service;

import com.fitnessapp.model.Exercise;
import com.fitnessapp.model.Goal;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.ExerciseRepository;
import com.fitnessapp.repository.GoalRepository;
import com.fitnessapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Goal setGoal(Long userId, Long exerciseId, double duration) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        double caloriesBurned = exercise.getMetsValue() * duration;

        Goal goal = new Goal();
        goal.setUser(user);
        goal.setExercise(exercise);
        goal.setDuration(duration);
        goal.setCaloriesBurned(caloriesBurned);
        goal.setCompleted(false);
        goal.setDate(LocalDate.now());

        return goalRepository.save(goal);
    }

    @Override
    public Goal markGoalCompleted(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));
        goal.setCompleted(true);
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> getUserGoals(Long userId) {
    	LocalDate today = LocalDate.now();
        return goalRepository.findByUserIdAndDate(userId, today);
    }
    
    
    public boolean deleteGoalById(Long goalId) {
        if (goalRepository.existsById(goalId)) {
            goalRepository.deleteById(goalId);
            return true;
        } else {
            return false;
        }
    }
}
