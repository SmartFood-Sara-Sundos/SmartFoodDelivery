package com.najah.service;

import com.najah.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AuthService
{

    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    private Map<String, User> usersDatabase = new HashMap<>();

    public void registerUser(User user)
    {
        usersDatabase.put(user.getUsername(), user);
    }

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