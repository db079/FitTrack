package com.fitnessapp.service;

import com.fitnessapp.model.Exercise;
import com.fitnessapp.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise createExercise(Exercise exercise) {
    	exercise.setId(null);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise updateExercise(Long id, Exercise exerciseDetails) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.setName(exerciseDetails.getName());
                    exercise.setDescription(exerciseDetails.getDescription());
                    exercise.setMetsValue(exerciseDetails.getMetsValue());
                    exercise.setImageUrl(exerciseDetails.getImageUrl());
                    return exerciseRepository.save(exercise);
                })
                .orElseThrow(() -> new RuntimeException("Exercise not found with id " + id));
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
    }
}
