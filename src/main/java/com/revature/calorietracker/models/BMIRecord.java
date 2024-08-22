package com.revature.calorietracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "bmi_records")
@Data
@NoArgsConstructor
public class BMIRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;
    private Double height;
    private Double weight;

    private Double bmiValue;

    @Column(nullable = false)
    private LocalDateTime recordedAt = LocalDateTime.now();

    public BMIRecord(Double height, Double weight) {
        System.out.println("Constructor of BMIRecord to calculate bmiValue");
        this.recordedAt = LocalDateTime.now();
        this.bmiValue = (height * height) / weight;
    }

    public void calculateAndSetBMIValue() {
        double n= (double) Math.round(this.weight / (this.height * this.height)*703);
        this.bmiValue = n;

    }
}
