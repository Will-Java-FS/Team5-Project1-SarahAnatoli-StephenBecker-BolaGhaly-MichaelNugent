package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exercises")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double caloriesBurnedPerMinute;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnore
    private Set<UserExerciseLog> exerciseLogs;
}
