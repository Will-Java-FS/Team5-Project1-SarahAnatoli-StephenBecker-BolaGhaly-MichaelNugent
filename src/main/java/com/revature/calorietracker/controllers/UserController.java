package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    //Get all users.  Admin only feature.  Need role authentication.
    @GetMapping
    public List<User> getAll(){return userService.getAll();}

    //Get a user by its id.  Authorization required.  Only data owner and ADMIN accounts can access user's data.
    @GetMapping("/{id}")
    public User getById(@PathVariable int id){return userService.getById(id);}

    //Update a user by its id.  Authorization required.  Only data owner and ADMIN accounts can modify a user's data.
    //Expects a complete User object
    @PutMapping("/{id}")
    public User updateById(@PathVariable long id){
        System.out.println("UserController.updateById(), id:" + id);
        return userService.updateById(id);}

}
