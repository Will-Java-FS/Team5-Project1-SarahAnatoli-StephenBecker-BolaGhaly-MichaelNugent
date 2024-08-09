package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_food_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFoodLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItem foodItem;

    private Double quantity;
    private Integer calories;

    @Column(nullable = false)
    private LocalDate logDate;
}
