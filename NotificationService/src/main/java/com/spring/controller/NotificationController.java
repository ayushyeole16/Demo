package com.spring.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.spring.model.Notification;
import com.spring.service.NotificationService;
import com.spring.util.JwtUtil;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification addNotification(
            @RequestBody Notification notification,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkJwt(authHeader);
        return service.addNotification(
                notification.getUserId(),
                notification.getType(),
                notification.getMessage()
        );
    }

    @GetMapping("/{userId}")
    public List<Notification> getUnread(
            @PathVariable String userId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkJwt(authHeader);
        return service.getUnreadNotifications(userId);
    }

    @PostMapping("/{notificationId}/read")
    public void markRead(
            @PathVariable Long notificationId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkJwt(authHeader);
        service.markAsRead(notificationId);
    }

    // Optional: Add a new endpoint if you want to fetch all notifications including order notifications
    @GetMapping("/all/{userId}")
    public List<Notification> getAllNotifications(
            @PathVariable String userId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        checkJwt(authHeader);
        return service.getAllNotifications(userId);
    }

    private void checkJwt(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (JwtUtil.validateToken(token)) {
                String subject = JwtUtil.extractSubject(token);
                System.out.println("JWT validated, user = " + subject);
            } else {
                System.out.println("Invalid JWT provided");
            }
        } else {
            System.out.println("No JWT provided, skipping validation (simulation mode)");
        }
    }
}