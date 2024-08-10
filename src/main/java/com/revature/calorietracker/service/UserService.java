package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.dto.UserMapper;
import com.revature.calorietracker.exceptions.UserNotFoundException;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@org.springframework.transaction.annotation.Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDTO getByUsername(String username) {
        return userRepo.findUserDTOByUsername(username).orElse(null);
    }

    public UserDTO updateByUsername(String username, UserDTO userDTO) {

        //Get original user information from repo
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found in database."));

        //Compare repoRetrieved data to this method's userDTO argument
        UserMapper.updateEntityWithNonNullDTOValues(user, userDTO);

        //Save updated User object to repo
        User savedUser = userRepo.save(user);

        return UserMapper.toDTO(savedUser);
    }
}