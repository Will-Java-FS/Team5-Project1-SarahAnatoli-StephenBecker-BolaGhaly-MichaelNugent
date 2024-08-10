package com.revature.calorietracker.models;

import com.revature.calorietracker.models.auth.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"foodLogs","bmiRecords", "exerciseLogs"}) //ignore lazily loaded fields because they may not be available
public class User implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Integer dailyCalorieGoal;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private Set<UserFoodLog> foodLogs;

    @OneToMany(mappedBy = "user")
    private Set<BMIRecord> bmiRecords;

    @OneToMany(mappedBy = "user")
    private Set<UserExerciseLog> exerciseLogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }
}

