package com.fitnessapp.service;

import com.fitnessapp.model.Goal;
import java.util.List;

public interface GoalService {
    Goal setGoal(Long userId, Long exerciseId, double duration);
    Goal markGoalCompleted(Long goalId);
    List<Goal> getUserGoals(Long userId);
	boolean deleteGoalById(Long goalId);
}
