package com.fitnessapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who created the goal
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"goals", "password"})
    private User user;

    // Custom exercise info
    private String customName;
    private String customDescription;

    // Expected or target calories user sets manually
    private double expectedCalories;

    // Duration (minutes)
    private double duration;

    // Status
    private boolean completed = false;

    // Created date
    private LocalDate date = LocalDate.now();
}
