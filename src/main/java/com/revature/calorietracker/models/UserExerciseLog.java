package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_exercise_logs")
@Getter
@Setter
public class UserExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private Double durationMinutes;
    private Double caloriesBurned;

    @Column(nullable = false)
    private LocalDate logDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
