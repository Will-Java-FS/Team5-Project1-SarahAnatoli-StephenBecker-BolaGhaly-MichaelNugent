package com.revature.calorietracker.service;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@org.springframework.transaction.annotation.Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Must be ADMIN to access all users.
    public List<User> getAll() {
        return null;
    }
    @PreAuthorize("#id==authentication.principal.id or hasRole('ROLE_ADMIN')") //USER privileges means requester must own data.  However, ADMIN requester can always access.
    public User getById(int id) {
        return null;
    }

    @PreAuthorize("#id==authentication.principal.id or hasRole('ROLE_ADMIN')") //USER privileges means requester must own data.  However, ADMIN requester can always access.
    public User updateById(long id) {

//        System.out.println("UserService.updateById(), id: " + id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("UserService.updateById(), authentication object: " + authentication);
//        User user = userRepo.findById(id).orElse(null);
//        System.out.println("UserService.updateById(), user: " + user);

        return null;
    }
}
