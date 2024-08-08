package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile profile;

    @OneToMany(mappedBy = "user")
    private Set<UserFoodLog> foodLogs;

    @OneToMany(mappedBy = "user")
    private Set<BMIRecord> bmiRecords;

    @OneToMany(mappedBy = "user")
    private Set<UserExerciseLog> exerciseLogs;
}

