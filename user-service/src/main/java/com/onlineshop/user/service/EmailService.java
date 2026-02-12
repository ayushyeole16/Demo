

package com.onlineshop.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  
    @Autowired
    private JavaMailSender mailSender;
  
    public void sendEmail(String toEmail, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply@urbanwearco.com");
            helper.setReplyTo("no-reply@urbanwearco.com"); // set your no-reply email
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true); // enable HTML content
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}