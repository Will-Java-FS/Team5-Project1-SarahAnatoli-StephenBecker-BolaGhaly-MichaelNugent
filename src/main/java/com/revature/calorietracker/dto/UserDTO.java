package com.revature.calorietracker.dto;


/**
 * @param height metric system? meters?
 * @param weight metric system? kilograms?
 */
public record UserDTO(String username, String email, Integer age, Integer dailyCalorieGoal, Double height,
                      Double weight, String firstName, String gender, String lastName) {
}
