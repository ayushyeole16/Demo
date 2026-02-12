package com.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.spring.model.Notification;
import com.spring.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }


    public Notification addNotification(String userId, String type, String message) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setType(type);
        n.setMessage(message);
        n.setCreatedAt(LocalDateTime.now());

        Notification saved = repo.save(n);

        return saved;
    }

    public List<Notification> getUnreadNotifications(String userId) {
        return repo.findByUserIdAndReadFalse(userId);
    }
    
 // New method to get all notifications (read and unread)
    public List<Notification> getAllNotifications(String userId) {
        return repo.findByUserId(userId);
    }


    public void markAsRead(Long notificationId) {
        repo.findById(notificationId).ifPresent(n -> {
            n.setRead(true);
            repo.save(n);
        });
    }
}
