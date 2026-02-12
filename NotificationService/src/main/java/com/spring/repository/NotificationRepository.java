package com.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdAndReadFalse(String userId);
  
    // New method to fetch all notifications for a user
    List<Notification> findByUserId(String userId);
}