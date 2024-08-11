package com.revature.calorietracker.service;

import com.revature.calorietracker.repos.BMIRecordRepo;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.calorietracker.models.User;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepo;


    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }


}
