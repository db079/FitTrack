package com.fitnessapp.controller;

import com.fitnessapp.model.Goal;
import com.fitnessapp.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // User sets a new goal
    @PostMapping("/{userId}/{exerciseId}")
    public ResponseEntity<Goal> setGoal(
            @PathVariable Long userId,
            @PathVariable Long exerciseId,
            @RequestParam double duration) {
        return ResponseEntity.ok(goalService.setGoal(userId, exerciseId, duration));
    }

    // User marks a goal as completed
    @PutMapping("/{goalId}/complete")
    public ResponseEntity<Goal> markGoalCompleted(@PathVariable Long goalId) {
        return ResponseEntity.ok(goalService.markGoalCompleted(goalId));
    }

    // Get all goals for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<Goal>> getUserGoals(@PathVariable Long userId) {
        return ResponseEntity.ok(goalService.getUserGoals(userId));
    }
    
    @DeleteMapping("/{goalId}")
    public ResponseEntity<String> deleteGoal(@PathVariable Long goalId) {
        boolean deleted = goalService.deleteGoalById(goalId);
        if (deleted) {
            return ResponseEntity.ok("Goal deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Goal not found!");
        }
    }
}
