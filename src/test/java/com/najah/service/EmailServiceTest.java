package com.najah.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest
{
    @Test
    void testEmailServiceInitialization()
    {
        EmailService emailService = new EmailService();
        assertNotNull(emailService);
    }
}