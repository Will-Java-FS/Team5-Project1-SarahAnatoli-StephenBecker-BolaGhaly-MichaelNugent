package com.revature.calorietracker.integration;

import com.revature.calorietracker.dto.AuthenticationRequest;
import com.revature.calorietracker.dto.RegisterRequest;
import com.revature.calorietracker.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    void register_ShouldReturnToken() throws Exception {
        // Arrange
        String requestBody = """
                {
                    "username": "johndoe",
                    "email": "johndoe@example.com",
                    "password": "password"
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

//        // Clean-up
//        mockMvc.perform(post("/auth/logout"))
//                .andExpect(status().isOk());
    }

    @Test
    void authenticate_ShouldReturnToken() throws Exception {
        // Arrange
        String requestBody = """
                {
                    "username": "johndoe",
                    "password": "password"
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
