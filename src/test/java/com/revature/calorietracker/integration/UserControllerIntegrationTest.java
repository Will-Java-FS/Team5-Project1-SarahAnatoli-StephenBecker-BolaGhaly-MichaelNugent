package com.revature.calorietracker.integration;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerIntegrationTest {
    @Autowired
    public UserControllerIntegrationTest(MockMvc mockMvc, UserRepo userRepo) {
        this.mockMvc = mockMvc;
        this.userRepo = userRepo;
    }

    private final MockMvc mockMvc;
    private final UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Clean up database before each test
        userRepo.deleteAll();

        // Create a test user in the database
        User testUser = new User();
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
