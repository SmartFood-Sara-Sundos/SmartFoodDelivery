package com.najah.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.logging.Logger;

public class EmailService
{
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    private String username;
    private String password;


    public EmailService(String mockUser, String mockPass)
    {
        this.username = mockUser;
        this.password = mockPass;
    }


    public EmailService()
    {
        try
        {
            Dotenv dotenv = Dotenv.load();
            this.username = dotenv.get("EMAIL_USERNAME");
            this.password = dotenv.get("EMAIL_PASSWORD");
        }
        catch (Exception e)
        {
            logger.warning("Environment file (.env) not found! Using default values.");
            this.username = "default@test.com";
            this.password = "defaultPass";
        }
    }

    public void sendEmail(String to, String subject, String body)
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            logger.info("Email sent successfully to " + to);

        } catch (MessagingException e) {
            logger.warning("Failed to send email: " + e.getMessage());
        }
    }
}