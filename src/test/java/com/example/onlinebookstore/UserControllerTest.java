package com.example.onlinebookstore;

import com.example.onlinebookstore.Controller.UserController;
import com.example.onlinebookstore.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.bookhaven.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private HttpSession sessionMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSuccessfulLogin() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("$2a$10$J5x0hVLEOqigH1bpAV0f9.8IJ.8sKL9bBhdyEJ6T4TJ5BcBEmvZUK");

        when(userRepositoryMock.findByUsername("testUser")).thenReturn(user);
        when(userRepositoryMock.existsByUsername("testUser")).thenReturn(true);
        when(userRepositoryMock.existsByEmail(anyString())).thenReturn(false);
        when(sessionMock.getAttribute("loggedIn")).thenReturn(false);
        when(passwordEncoderMock.matches("password", user.getPassword())).thenReturn(true);

        ResponseEntity<String> response = userController.loginUser("testUser", "password", sessionMock);

        assertEquals("User logged in successfully", response.getBody());
    }

    @Test
    public void testFailedLogin() {
        when(userRepositoryMock.findByUsername("nonexistentUser")).thenReturn(null);
        when(sessionMock.getAttribute("loggedIn")).thenReturn(false);

        ResponseEntity<String> response = userController.loginUser("nonexistentUser", "wrongPassword", sessionMock);

        assertEquals("Invalid username or password", response.getBody());
    }
}
