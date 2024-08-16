package com.revature.calorietracker.unit.service;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.exceptions.UserNotFoundException;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import com.revature.calorietracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByUsername_ReturnsUserDTO() {
        //Arrange
        String username = "johndoe";
        UserDTO mockUserDTO = new UserDTO("johndoe", "johndoe@example.com", "John", "Doe", 30, 70.75, 1.7,"Male",2000);
        when(userRepo.findUserDTOByUsername(username)).thenReturn(Optional.of(mockUserDTO));

        //Act
        UserDTO returnedUserDTO = userService.getByUsername(username);

        //Assert
        assertNotNull(returnedUserDTO);
        assertEquals("johndoe", returnedUserDTO.username());
        verify(userRepo, times(1)).findUserDTOByUsername(username);
    }
    @Test
    void getByUsername_ThrowsUserNotFoundException() {
        // Arrange
        String username = "nonexistentuser";
        when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getByUsername(username);
        });
        verify(userRepo, times(1)).findUserDTOByUsername(username);
    }

    @Test
    void updateByUsername_UpdatesAndReturnsUserDTO() {
        //Arrange
        String username = "johndoe";
        UserDTO updateUserDTO = new UserDTO("johndoe", "johndoe@example.com", "John", "Doe", 30, 70.75, 1.7,"Male",2000);
        User existingUser = new User();
        when(userRepo.findByUsername(username)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(User.class))).thenReturn(existingUser);

        //Act
        UserDTO returnedUserDTO = userService.updateByUsername(username, updateUserDTO);

        //Assert
        assertNotNull(returnedUserDTO);
        verify(userRepo, times(1)).findByUsername(username);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void updateByUsername_ThrowsUserNotFoundException() {
        // Arrange
        String username = "nonexistentuser";
        UserDTO updateUserDTO = new UserDTO("johndoe", "johndoe@example.com", "John", "Doe", 30, 70.75, 1.7,"Male",2000);
        when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.updateByUsername(username, updateUserDTO);
        });
        verify(userRepo, times(1)).findByUsername(username);
    }

}
