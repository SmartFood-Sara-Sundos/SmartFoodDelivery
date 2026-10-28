package com.najah.service;

public class CustomerNotifier implements OrderObserver
{
    private EmailService emailService;
    private String customerEmail;

    public CustomerNotifier(EmailService emailService, String customerEmail)
    {
        this.emailService = emailService;
        this.customerEmail = customerEmail;
    }

    @Override
    public void update(String message)
    {
        emailService.sendEmail(customerEmail, "Smart Food Delivery - Order Update", message);
    }
}