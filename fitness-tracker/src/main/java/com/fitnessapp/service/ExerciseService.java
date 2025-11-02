package com.fitnessapp.service;

import com.fitnessapp.model.Exercise;
import java.util.List;

public interface ExerciseService {
    Exercise createExercise(Exercise exercise);
    Exercise updateExercise(Long id, Exercise exerciseDetails);
    void deleteExercise(Long id);
    List<Exercise> getAllExercises();
    Exercise getExerciseById(Long id);
}
