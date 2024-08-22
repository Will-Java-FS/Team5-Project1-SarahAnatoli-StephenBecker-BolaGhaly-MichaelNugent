package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "food_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer calories;

    @CreationTimestamp
    private LocalDate logDate;

    private String servingSize;
    private Double protein;
    private Double carbs;
    private Double fat;

    @OneToMany(mappedBy = "foodItem")
    @JsonIgnore
    private Set<UserFoodLog> foodLogs;
}
