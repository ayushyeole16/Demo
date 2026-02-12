

package com.onlineshop.user.service;

import com.onlineshop.user.model.User;
import com.onlineshop.user.model.Role;
import com.onlineshop.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }
        User savedUser = userRepository.save(user);

        // Construct styled HTML email content
        String emailContent = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; font-size: 14px; color: #333; }" +
            ".header { background-color: #f6f6f6; padding: 10px; text-align: center; font-size: 20px; font-weight: bold; color: #FF6347; }" +
            ".content { padding: 20px; }" +
            "table { border-collapse: collapse; width: 100%; }" +
            "td { border: 1px solid #ddd; padding: 8px; }" +
            ".footer { font-size: 12px; color: #999; margin-top: 20px; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='header'>Welcome to UrbanWearCo!</div>" +
            "<div class='content'>" +
            "<p>Dear <b>" + savedUser.getName() + "</b>,</p>" +
            "<p>Thank you for registering with us. Your account has been successfully created.</p>" +
            "<p>Here are your registration details:</p>" +
            "<table>" +
            "<tr><td>Name</td><td>" + savedUser.getName() + "</td></tr>" +
            "<tr><td>Address</td><td>" + savedUser.getAddress() + "</td></tr>" +
            
            "</table>" +
            "<p style='margin-top:20px;'>We look forward to serving you.</p>" +
            "<p>Happy Shopping!</p>" +
            "<p style='color: #888; font-size: 12px;'>Best regards,<br>Your Support Team</p>" +
            "</div>" +
            "<div class='footer'> " +
            "<p>This is an automatically generated email, please do not reply.</p>" +
            "<p>&copy; 2026 UrabanWearCo. All rights reserved.</p>" +
            "<p><a href='https://yourdomain.com/unsubscribe' style='color:#999;'>Unsubscribe</a></p>" +
            "</div>" +
            "</body>" +
            "</html>";

        String subject = "Welcome to UrabanWearCo - Your Registration Details";

        // Send the styled email
        emailService.sendEmail(savedUser.getEmail(), subject, emailContent);
        return savedUser;
    }

    // existing methods...


    public User login(String email, String password) {
        System.out.println("Email: " + email + ", Password: " + password);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(User user, String newName, String newAddress, String newPassword) {
        user.setName(newName);
        user.setAddress(newAddress);
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
        return userRepository.save(user);
    }
    // add login, update profile, get profile methods here...
}




