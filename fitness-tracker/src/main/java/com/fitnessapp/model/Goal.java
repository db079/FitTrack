package com.fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who created the goal
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // The exercise (chosen from admin-created list)
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    // Duration in minutes
    private double duration;

    // Calories burned (calculated automatically)
    private double caloriesBurned;

    // true = completed, false = pending
    private boolean completed = false;

    // Date of goal creation
    private LocalDate date = LocalDate.now();
}
