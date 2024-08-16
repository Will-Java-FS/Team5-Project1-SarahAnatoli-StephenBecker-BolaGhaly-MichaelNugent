package com.revature.calorietracker.integration;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Clean up database before each test
        userRepo.deleteAll();

        // Create a test user in the database
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("passwordHash");
        testUser.setAge(25);
        testUser.setDailyCalorieGoal(2000);
        testUser.setHeight(1.75);
        testUser.setWeight(70.0);
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        testUser.setGender("Male");

        userRepo.save(testUser);
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void getByUsername_ShouldReturnUserDTO() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void updateByUsername_ShouldUpdateAndReturnUserDTO() throws Exception {
        // Arrange
        String updateRequest = """
                {
                    "username": "testuser",
                    "email": "updated@example.com",
                    "age": 26,
                    "dailyCalorieGoal": 2200,
                    "height": 1.80,
                    "weight": 72.0,
                    "firstName": "Updated",
                    "lastName": "User",
                    "gender": "Male"
                }
                """;

        // Act & Assert
        mockMvc.perform(patch("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("updated@example.com"))
                .andExpect(jsonPath("$.age").value(26))
                .andExpect(jsonPath("$.dailyCalorieGoal").value(2200));
    }

    @Test
    @WithMockUser(username = "nonexistentuser", roles = {"USER"})
    void getByUsername_ShouldReturnNotFoundForNonExistentUser() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void updateByUsername_ShouldReturnNotFoundForNonExistentUser() throws Exception {
        // Arrange
        userRepo.deleteAll();  // Delete all users to simulate non-existence

        String updateRequest = """
                {
                    "username": "testuser",
                    "email": "updated@example.com",
                    "age": 26,
                    "dailyCalorieGoal": 2200,
                    "height": 1.80,
                    "weight": 72.0,
                    "firstName": "Updated",
                    "lastName": "User",
                    "gender": "Male"
                }
                """;

        // Act & Assert
        mockMvc.perform(patch("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequest))
                .andExpect(status().isNotFound());
    }
}
