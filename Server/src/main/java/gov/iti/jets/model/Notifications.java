package gov.iti.jets.model;

import java.time.LocalDateTime;

import java.io.Serializable;

public class Notifications implements Serializable {
    private static final long serialVersionUID = 1L;
    private int notificationId;
    private String userId;
    private String message;
    private LocalDateTime sentAt;
    private boolean isRead;
    private Notification notificationType;
    public Notifications() {
    }

    public Notifications(int notificationId, String userId, String message, LocalDateTime sentAt, boolean isRead, Notification type) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.sentAt = sentAt;
        this.isRead = isRead;
        notificationType = type;
    }



    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Notification getNotificationType() {
        return notificationType;
    }

    public void setNotificationtype(Notification notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", sentAt=" + sentAt +
                ", isRead=" + isRead +
                '}';
    }
}
