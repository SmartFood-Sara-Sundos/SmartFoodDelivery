package com.najah.service;

import com.najah.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class responsible for user registration and authentication.
 * Manages user credentials using an in-memory database.
 *
 * @author Sara-Abdalaziz
 * @version 1.0
 */
public class AuthService
{
    /**
     * Logger instance used to record authentication events and warnings.
     */
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    /**
     * In-memory storage mapping usernames to their corresponding User objects.
     */
    private Map<String, User> usersDatabase = new HashMap<>();

    /**
     * Constructs a new AuthService with an empty in-memory user database.
     */
    public AuthService()
    {
    }

    /**
     * Registers a new user into the authentication database.
     *
     * @param user the {@link User} object containing credentials and role to register
     */
    public void registerUser(User user)
    {
        usersDatabase.put(user.getUsername(), user);
    }

    /**
     * Authenticates a user by verifying the provided username and password.
     *
     * @param username the username of the user attempting to log in
     * @param password the password provided by the user
     * @return {@code true} if the user exists and the password matches, {@code false} otherwise
     */
    public boolean login(String username, String password)
    {
        User user = usersDatabase.get(username);

        if (user != null && user.getPassword().equals(password))
        {
            logger.info("Login success!");
            return true;
        }

        logger.warning("Error: Invalid credentials!");
        return false;
    }
}