package com.jobtracker.jobapplicationtracker.auth;

import com.jobtracker.jobapplicationtracker.security.JwtService;
import com.jobtracker.jobapplicationtracker.user.User;
import com.jobtracker.jobapplicationtracker.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUserSuccessfully() {
        RegisterRequest registerRequest = new RegisterRequest("test@example.com", "password123", "Farzaneh");

        when(passwordEncoder.encode(any())).thenReturn("hashedPassword");
        when(jwtService.generateToken(any())).thenReturn("mocked-jwt-token");

        AuthenticationResponse response = authService.register(registerRequest);

        assertNotNull(response);
        verify(userRepository, times(1)).save(any(User.class));
    }
}