package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.AdminUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    AdminUserService adminUserService;
    //ADMIN routes: "/admin/users/**" (as opposed to USER's singular "/user/**" routes).
    @GetMapping("/{id}")
    public User getById(@PathVariable int id){return null;}
    //Get all users.  Need role authentication.
    @GetMapping("/all")
    public List<User> getAll(){return adminUserService.getAll();}
    @PatchMapping("/{id}/role")
    public User updateRoleById(@PathVariable long id){
        return adminUserService.updateRoleById(id);
    }
    //Create User
    //Delete User
}

//    @PreAuthorize("#id==authentication.principal.id or hasRole('ROLE_ADMIN')") //USER privileges means requester must own data.  However, ADMIN requester can always access.
//@PreAuthorize("#id==authentication.principal.id or hasRole('ROLE_ADMIN')") //USER privileges means requester must own data.  However, ADMIN requester can always access.
