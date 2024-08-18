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
//    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    private Double height;
    private Double weight;

    private Double bmiValue;

    @Column(nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime recordedAt = LocalDateTime.now();

    public BMIRecord(Double height, Double weight) {
        System.out.println("Constructor of BMIRecord to calculate bmiValue");
        this.recordedAt = LocalDateTime.now();
        this.bmiValue = (height * height) / weight;
    }

    //@PrePersist
    //protected void onCreate() {
    //    this.recordedAt = LocalDateTime.now();
    //    this.bmiValue=calculateMetricBMI();
    //}

    //    public Double calculateMetricBMI(){
//        Double hsq=user.getHeight()*user.getHeight();
//        Double w=user.getWeight();
//        this.bmiValue = w/hsq;
//        return w/hsq;
//    }
    public void calculateAndSetBMIValue() {
//        Double hsq = this.height * this.height;
        this.bmiValue = this.weight / (this.height * this.height);

    }
}
