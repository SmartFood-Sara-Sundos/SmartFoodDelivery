package com.najah.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testUserCreation() {
        User user = new User("sara", "pass123", "Manager");
        assertEquals("sara", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals("Manager", user.getRole());
    }
}