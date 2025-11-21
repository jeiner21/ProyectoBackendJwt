package com.project.java;

import com.project.java.model.Users;
import com.project.java.repository.Repository;
import com.project.java.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Repository userRepository;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {
        // Arrange
        Users user = new Users();
        user.setEmail("testuser");
        user.setPassword("password123");

        when(passwordEncoder.encode("password123")).thenReturn("encodedPass");
        when(userRepository.save(any())).thenReturn(user);

        // Act
        Users result = userService.save(user);

        // Assert
        assertEquals("testuser", result.getEmail());
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepository, times(1)).save(any());
    }
}
