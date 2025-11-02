package com.fitnessapp.service;

import com.fitnessapp.model.CustomGoal;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.CustomGoalRepository;
import com.fitnessapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomGoalServiceImpl implements CustomGoalService {

    @Autowired
    private CustomGoalRepository customGoalRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomGoal createCustomGoal(Long userId, CustomGoal goal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        goal.setUser(user);
        goal.setCompleted(false);
        return customGoalRepository.save(goal);
    }

    @Override
    public List<CustomGoal> getUserCustomGoals(Long userId) {
    	LocalDate today = LocalDate.now();
        return customGoalRepository.findByUserIdAndDate(userId, today);
    }

    @Override
    public CustomGoal updateGoalStatus(Long goalId, boolean completed) {
        CustomGoal goal = customGoalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Custom goal not found"));
        goal.setCompleted(completed);
        return customGoalRepository.save(goal);
    }
    public boolean deleteCustomGoalById(Long customGoalId) {
        if (customGoalRepository.existsById(customGoalId)) {
            customGoalRepository.deleteById(customGoalId);
            return true;
        } else {
            return false;
        }
    }
    
}
