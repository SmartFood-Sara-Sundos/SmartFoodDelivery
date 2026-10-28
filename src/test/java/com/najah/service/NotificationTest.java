package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.Order;
import com.najah.domain.User;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.mockito.Mockito.*;

class NotificationTest
{
    @Test
    void testEmailNotification_AssignedDriver()
    {
        EmailService mockEmailService = mock(EmailService.class);
        CustomerNotifier notifier = new CustomerNotifier(mockEmailService, "sara@example.com");

        Order order = new Order("O-001", null, null, Collections.emptyList(), "Nablus");
        order.addObserver(notifier);

        Driver driver = new Driver("D1", "Omar", true, 5.0, 15.0);
        order.setAssignedDriver(driver);


        verify(mockEmailService, times(1)).sendEmail(
                eq("sara@example.com"),
                eq("Smart Food Delivery - Order Update"),
                contains("Omar has been assigned")
        );
    }

    @Test
    void testEmailNotification_OutForDelivery()
    {
        EmailService mockEmailService = mock(EmailService.class);
        CustomerNotifier notifier = new CustomerNotifier(mockEmailService, "sara@example.com");

        Order order = new Order("O-002", null, null, Collections.emptyList(), "Nablus");
        order.addObserver(notifier);

        order.setStatus("Out for Delivery");


        verify(mockEmailService, times(1)).sendEmail(
                eq("sara@example.com"),
                eq("Smart Food Delivery - Order Update"),
                contains("Out for Delivery")
        );
    }

    @Test
    void testEmailNotification_Delivered()
    {
        EmailService mockEmailService = mock(EmailService.class);
        CustomerNotifier notifier = new CustomerNotifier(mockEmailService, "sara@example.com");

        Order order = new Order("O-003", null, null, Collections.emptyList(), "Nablus");
        order.addObserver(notifier);

        order.setStatus("Delivered");

        verify(mockEmailService, times(1)).sendEmail(
                eq("sara@example.com"),
                eq("Smart Food Delivery - Order Update"),
                contains("Delivered")
        );
    }
}