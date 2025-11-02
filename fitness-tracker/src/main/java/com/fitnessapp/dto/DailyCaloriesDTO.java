package com.fitnessapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DailyCaloriesDTO {
    private LocalDate date;
    private double caloriesBurned;
}
