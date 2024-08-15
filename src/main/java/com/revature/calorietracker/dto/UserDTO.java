package com.revature.calorietracker.dto;

public record UserDTO(String username, String email, Integer age, Integer dailyCalorieGoal, Double height,
                      Double weight, String firstName, String gender, String lastName) {
}
