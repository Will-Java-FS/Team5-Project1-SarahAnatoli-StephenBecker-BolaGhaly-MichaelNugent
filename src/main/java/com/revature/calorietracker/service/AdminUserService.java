package com.revature.calorietracker.service;

import com.revature.calorietracker.models.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public class AdminUserService {

    //Service class methods.  Move there later.
    //ADMIN methods should return the entire User objects.
    //ADMIN methods should go into another class devoted to admin functionality
    @PreAuthorize("hasRole('ROLE_ADMIN')") //Must be ADMIN to access all users.
    public List<User> getAll() {
        return null;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') and #id!=authentication.principal.id")//Must be ADMIN to modify user privileges, and cannot modify self.
    public User updateRoleById(long id){
        //Role value must be valid option of Role enumeration (USER, MANAGER, ADMIN)
        return null;
    }
    //Create User
    //Delete User
}
