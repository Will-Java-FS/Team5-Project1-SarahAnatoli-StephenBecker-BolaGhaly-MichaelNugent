package com.revature.calorietracker.models;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bmi_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BMIRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private Double bmiValue;

    @Column(nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime recordedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        this.recordedAt = LocalDateTime.now();
        this.bmiValue=calculateMetricBMI();
    }

    Double calculateMetricBMI(){
        Double hsq=user.getHeight()*user.getHeight();
        Double w=user.getWeight();
        return w/hsq;
    }
}
