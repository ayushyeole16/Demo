package com.spring.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;  

    private String userId;       
    private String type;
    private String message;
    
    @Column(name = "is_read")
    private boolean read = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Notification() {
        super();
    }

    public Notification(Long notificationId, String userId, String type, String message,
                        boolean read, LocalDateTime createdAt) {
        super();
        this.notificationId = notificationId;
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.read = read;
        this.createdAt = createdAt;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Notification [notificationId=" + notificationId + ", userId=" + userId
                + ", type=" + type + ", message=" + message
                + ", read=" + read + ", createdAt=" + createdAt + "]";
    }
}
