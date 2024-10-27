package com.hackathon.bankingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.bankingapp.dto.request.LoginRequest;
import com.hackathon.bankingapp.dto.request.RegisterRequest;
import com.hackathon.bankingapp.dto.response.AuthResponse;
import com.hackathon.bankingapp.models.ERole;
import com.hackathon.bankingapp.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        AuthResponse authResponse = new AuthResponse("token", ERole.USER);

        when(authService.login(any(LoginRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value(ERole.USER.toString()));

        verify(authService, times(1)).login(any(LoginRequest.class));
    }

    @Test
    public void testRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("username", "email@example.com", "password", ERole.USER);
        AuthResponse authResponse = new AuthResponse("token", ERole.USER);

        when(authService.register(any(RegisterRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value(ERole.USER.toString()));

        verify(authService, times(1)).register(any(RegisterRequest.class));
    }
  
}