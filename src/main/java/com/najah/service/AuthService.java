package com.najah.service;

import com.najah.domain.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    // In-memory Database
    private Map<String, User> usersDatabase = new HashMap<>();

    public void registerUser(User user) {
        usersDatabase.put(user.getUsername(), user);
    }

    public boolean login(String username, String password) {
        User user = usersDatabase.get(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login success!");
            return true;
        }

        System.out.println("Error: Invalid credentials!");
        return false;
    }
}