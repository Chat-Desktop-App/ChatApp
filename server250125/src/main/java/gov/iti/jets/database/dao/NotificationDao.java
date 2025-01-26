package gov.iti.jets.database.dao;

import gov.iti.jets.model.Notification;

import java.util.List;

public interface NotificationDao {
    void addNotification(Notification notification);
    Notification getNotificationById(int notificationId);
    List<Notification> getAllNotificationById(int notificationId);
    void updateNotification(Notification notification);
    void deleteNotification(int notificationId);
}

