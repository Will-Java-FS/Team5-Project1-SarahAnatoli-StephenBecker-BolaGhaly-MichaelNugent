package com.revature.calorietracker.dto;

import com.revature.calorietracker.models.User;

import java.util.Optional;

public class UserMapper {
    public static void updateEntityWithNonNullDTOValues(User user, UserDTO userDTO){

        //Trivial field updates
        Optional.ofNullable(userDTO.firstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDTO.lastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDTO.age()).ifPresent(user::setAge);
        Optional.ofNullable(userDTO.dailyCalorieGoal()).ifPresent(user::setDailyCalorieGoal);
        Optional.ofNullable(userDTO.gender()).ifPresent(user::setGender);
        Optional.ofNullable(userDTO.height()).ifPresent(user::setHeight);
        Optional.ofNullable(userDTO.weight()).ifPresent(user::setWeight);

        //Critical field updates (might be bad practice to allow)
        Optional.ofNullable(userDTO.username()).ifPresent(user::setUsername);
        Optional.ofNullable(userDTO.gender()).ifPresent(user::setGender);
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getAge(),
                user.getDailyCalorieGoal(),
                user.getHeight(),
                user.getWeight(),
                user.getFirstName(),
                user.getGender(),
                user.getLastName()
        );
    }
}
