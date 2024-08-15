package com.revature.calorietracker.repos;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.dto.UserSecurityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<UserDTO> findUserDTOByUsername(String username);
    Optional<User> findByUsername(String username);

    Optional<UserSecurityDTO> findUserSecurityDTOByUsername(String username);
}
