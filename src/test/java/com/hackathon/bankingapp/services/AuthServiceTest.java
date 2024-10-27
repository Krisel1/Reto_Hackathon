package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.dto.request.LoginRequest;
import com.hackathon.bankingapp.dto.request.RegisterRequest;
import com.hackathon.bankingapp.dto.response.AuthResponse;
import com.hackathon.bankingapp.models.ERole;
import com.hackathon.bankingapp.models.User;
import com.hackathon.bankingapp.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtService jwtService;

    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void test_Login_userNotFound() {
        String username = "unknownuser";
        String password = "password123";

        LoginRequest loginRequest = new LoginRequest(username, password);

        when(iUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));

        verify(authenticationManager, never()).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).getTokenService(any(UserDetails.class));
    }

    @Test
    public void test_Register_successful() {

        String username = "vicky";
        String email = "vicky@example.com";
        String password = "password123";
        String encodedPassword = "encodedPassword123";
        String token = "new_generated_token";

        RegisterRequest registerRequest = new RegisterRequest(username, email, password, ERole.USER);

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(jwtService.getTokenService(any(UserDetails.class))).thenReturn(token);

        AuthResponse response = authService.register(registerRequest);

        assertNotNull(response);
        assertEquals(token, response.getToken());
        assertEquals(ERole.USER, response.getRole());

        verify(iUserRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).getTokenService(any(UserDetails.class));
    }

    @Test
    public void test_Register_userAlreadyExists() {

        String username = "valen";
        String email = "valen@example.com";
        String password = "password123";

        RegisterRequest registerRequest = new RegisterRequest(username, email, password, ERole.USER);

        when(iUserRepository.findByUsername(username)).thenReturn(Optional.of(new User(1L, ERole.USER, "password1", "user1@example.com", "Krisel")));

        assertThrows(RuntimeException.class, () -> authService.register(registerRequest));

        verify(iUserRepository, never()).save(any(User.class));
        verify(jwtService, never()).getTokenService(any(UserDetails.class));
    }

}