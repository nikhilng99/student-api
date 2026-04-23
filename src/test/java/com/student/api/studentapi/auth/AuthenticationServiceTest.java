package com.student.api.studentapi.auth;

import com.student.api.studentapi.config.JwtService;
import com.student.api.studentapi.repository.UserRepository;
import com.student.api.studentapi.user.Role;
import com.student.api.studentapi.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .firstname("Nikhil")
                .lastname("NG")
                .email("nikhil@test.com")
                .password("1234")
                .build();
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        when(passwordEncoder.encode("1234")).thenReturn("encodedPass");

        User savedUser = User.builder()
                .email("nikhil@test.com")
                .password("encodedPass")
                .role(Role.USER)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt-token");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());

        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService).generateToken(any(User.class));
    }

    @Test
    void shouldAuthenticateUserSuccessfully() {
        AuthenticationRequest request = new AuthenticationRequest("nikhil@test.com", "1234");

        User user = User.builder()
                .email("nikhil@test.com")
                .password("encodedPass")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail("nikhil@test.com"))
                .thenReturn(Optional.of(user));

        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertEquals("jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any());
    }
}
