package com.revature.calorietracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_actions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    private String action;

    @ManyToOne
    @JoinColumn(name = "target_user_id")
    private User targetUser;
}
