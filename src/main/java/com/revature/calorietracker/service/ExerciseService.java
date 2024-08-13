package com.revature.calorietracker.service;

import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.repos.ExerciseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExerciseService {
    @Autowired
    private ExerciseRepo exerciseRepo;

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    public void deleteExerciseById(Exercise exercise) {
        Optional<Exercise> existingExercise = exerciseRepo.findById(
                exercise.getId());

        if (existingExercise.isPresent()) {
            exerciseRepo.delete(existingExercise.get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }
    }
}
