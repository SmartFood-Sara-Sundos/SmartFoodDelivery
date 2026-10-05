package com.najah.service;

import com.najah.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void testValidLogin() {

        AuthService authService = new AuthService();
        User testUser = new User("sara", "12345", "Customer");
        authService.registerUser(testUser);

        boolean result = authService.login("sara", "12345");

        assertTrue(result);
    }

    @Test
    void testInvalidLogin() {

        AuthService authService = new AuthService();
        User testUser = new User("sara", "12345", "Customer");
        authService.registerUser(testUser);

        boolean result = authService.login("sara", "wrong_password");

        assertFalse(result);
    }
}