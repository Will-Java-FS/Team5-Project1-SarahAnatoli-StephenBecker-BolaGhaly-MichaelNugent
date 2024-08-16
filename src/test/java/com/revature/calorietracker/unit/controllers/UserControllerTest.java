package com.revature.calorietracker.unit.controllers;

import com.revature.calorietracker.controllers.UserController;
import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("johndoe");
    }

    @Test
    void getByUsername_ReturnsUserDTO() {
        // Arrange
        UserDTO mockUserDTO = new UserDTO("johndoe", "johndoe@example.com", "John", "Doe", 30, 70.75, 1.7,"Male",2000);
        when(userService.getByUsername("johndoe")).thenReturn(mockUserDTO);

        // Act
        UserDTO returnedUserDTO = userController.getByUsername();

        // Assert
        assertNotNull(returnedUserDTO);
        assertEquals("johndoe", returnedUserDTO.username());
        verify(userService, times(1)).getByUsername("johndoe");
    }

    @Test
    void updateByUsername_UpdatesAndReturnsUserDTO() {
        // Arrange
        UserDTO updateUserDTO = new UserDTO("johndoe", "johndoe@example.com", "John", "Doe", 31, 70.75, 1.7,"Male",2000);
        when(userService.updateByUsername(eq("johndoe"), any(UserDTO.class))).thenReturn(updateUserDTO);

        // Act
        UserDTO returnedUserDTO = userController.updateByUsername(updateUserDTO);

        // Assert
        assertNotNull(returnedUserDTO);
        assertEquals(31, returnedUserDTO.age());
        verify(userService, times(1)).updateByUsername(eq("johndoe"), any(UserDTO.class));
    }
}
