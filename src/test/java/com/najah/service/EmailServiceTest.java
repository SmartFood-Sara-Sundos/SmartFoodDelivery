package com.najah.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest
{
    @Test
    void testEmailServiceInitializationWithMockData()
    {

        EmailService emailService = new EmailService("test@test.com", "12345");
        assertNotNull(emailService);
    }

    @Test
    void testEmailServiceDefaultInitialization()
    {

        EmailService emailService = new EmailService();
        assertNotNull(emailService);
    }
}