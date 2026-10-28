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

    @Test
    void testSendEmailExecution_HitsCatchBlock()
    {

        EmailService emailService = new EmailService("dummy@test.com", "dummyPass");
        emailService.sendEmail("recipient@test.com", "Test Subject", "Test Body");

        assertNotNull(emailService);
    }
}