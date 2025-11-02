package com.fitnessapp.controller;

import com.fitnessapp.model.CustomGoal;
import com.fitnessapp.service.CustomGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-goals")
@CrossOrigin(origins = "*")
public class CustomGoalController {

    @Autowired
    private CustomGoalService customGoalService;

    @PostMapping("/{userId}")
    public ResponseEntity<CustomGoal> createCustomGoal(@PathVariable Long userId, @RequestBody CustomGoal goal) {
        return ResponseEntity.ok(customGoalService.createCustomGoal(userId, goal));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CustomGoal>> getUserCustomGoals(@PathVariable Long userId) {
        return ResponseEntity.ok(customGoalService.getUserCustomGoals(userId));
    }

    @PutMapping("/{goalId}/status")
    public ResponseEntity<CustomGoal> updateGoalStatus(@PathVariable Long goalId, @RequestParam boolean completed) {
        return ResponseEntity.ok(customGoalService.updateGoalStatus(goalId, completed));
    }
    
    @DeleteMapping("/{customGoalId}")
    public ResponseEntity<String> deleteCustomGoal(@PathVariable Long customGoalId) {
        boolean deleted = customGoalService.deleteCustomGoalById(customGoalId);
        if (deleted) {
            return ResponseEntity.ok("Custom goal deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Custom goal not found!");
        }
    }
}
