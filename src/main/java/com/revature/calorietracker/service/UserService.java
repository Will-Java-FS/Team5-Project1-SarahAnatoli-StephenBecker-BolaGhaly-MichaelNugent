package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.dto.UserMapper;
import com.revature.calorietracker.dto.UserSecurityDTO;
import com.revature.calorietracker.exceptions.UserNotFoundException;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserFoodLog;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.UserExerciseLog;

import com.revature.calorietracker.repos.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers () {
        return userRepo.findAll();
    }

    public User getUserByUserId (Long id) {
        if (userRepo.findById(id).isPresent()) return userRepo.findById(id).get();
        return null;
    }

    public User updateUserPassword (String username, String newPassword) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found in database."));
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepo.save(user);
    }

    public List<UserFoodLog> getUserFoodLogsByUserId (Long id) {
        if (userRepo.findById(id).isPresent()) return userRepo.findById(id).get().getFoodLogs();
        return null;
    }

    public List<BMIRecord> getUserBMIRecordsByUserId (Long id) {
        if (userRepo.findById(id).isPresent()) return userRepo.findById(id).get().getBmiRecords();
        return null;
    }

    public List<UserExerciseLog> getUserExerciseLogsByUserId (Long id) {
        if (userRepo.findById(id).isPresent()) return userRepo.findById(id).get().getExerciseLogs();
        return null;
    }

    public UserDTO getByUsername(String username) {
        return userRepo.findUserDTOByUsername(username).orElseThrow(()->new UserNotFoundException("User not found by username."));
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

    public UserSecurityDTO getUserSecurityDTOById(Long id) {
        return userRepo.findUserSecurityDTOById(id).orElseThrow(() -> new UsernameNotFoundException("UserSecurityDTO not found in database."));
    }
}