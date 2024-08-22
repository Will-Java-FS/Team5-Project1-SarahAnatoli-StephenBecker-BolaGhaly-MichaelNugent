package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.saveExercise(exercise);
        return ResponseEntity.status(200).body(createdExercise);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.status(200).body(exercises);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExercise(@RequestBody Exercise exercise) {
        try {
            exerciseService.deleteExerciseById(exercise);
            return ResponseEntity.status(200).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
