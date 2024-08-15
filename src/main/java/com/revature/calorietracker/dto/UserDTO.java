package com.revature.calorietracker.dto;


/**
 * @param height metric system? meters?
 * @param weight metric system? kilograms?
 */
public record UserDTO(String username, String email, String firstName, String lastName, Integer age, Double weight, Double height, String gender, Integer dailyCalorieGoal) {

}
