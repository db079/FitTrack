package com.fitnessapp.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalHistoryDTO {
    private String name;          // Exercise or CustomGoal name
    private String description;   // Exercise or CustomGoal description
    private double duration;
    private double caloriesBurned; // For Goal
    private double expectedCalories; // For CustomGoal
    private boolean completed;
    private LocalDate date;
    private String type; // "GOAL" or "CUSTOM_GOAL"
}
