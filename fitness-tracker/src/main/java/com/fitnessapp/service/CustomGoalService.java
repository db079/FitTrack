package com.fitnessapp.service;

import com.fitnessapp.model.CustomGoal;
import java.util.List;

public interface CustomGoalService {
    CustomGoal createCustomGoal(Long userId, CustomGoal goal);
    List<CustomGoal> getUserCustomGoals(Long userId);
    CustomGoal updateGoalStatus(Long goalId, boolean completed);
	boolean deleteCustomGoalById(Long customGoalId);
}
