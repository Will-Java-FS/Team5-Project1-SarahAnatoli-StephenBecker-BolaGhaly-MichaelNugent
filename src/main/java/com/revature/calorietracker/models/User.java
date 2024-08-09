package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;
    private String lastName;
    private Integer age;
    private Double weight;
    private Double height;
    private String gender;
    private String role = "user";
    private Integer dailyCalorieGoal;

    @OneToMany(mappedBy = "user")
    private Set<UserFoodLog> foodLogs;

    @OneToMany(mappedBy = "user")
    private Set<BMIRecord> bmiRecords;

    @OneToMany(mappedBy = "user")
    private Set<UserExerciseLog> exerciseLogs;
}

