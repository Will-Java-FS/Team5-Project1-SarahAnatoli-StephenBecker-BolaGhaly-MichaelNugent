package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bmi_records")
@Getter
@Setter
public class BMIRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double bmiValue;

    @Column(nullable = false)
    private LocalDateTime recordedAt;
}
