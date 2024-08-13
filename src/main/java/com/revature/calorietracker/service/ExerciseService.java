package com.revature.calorietracker.service;

import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.repos.ExerciseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExerciseService {
    @Autowired
    private ExerciseRepo exerciseRepo;

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }
}
