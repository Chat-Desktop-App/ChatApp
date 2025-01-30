package gov.iti.jets.database.dao;

import gov.iti.jets.client.model.Notifications;

import java.util.List;

public interface NotificationsDao {
    void addNotification(Notifications notifications);
    Notifications getNotificationById(int notificationId);
    List<Notifications> getAllNotificationById(String userId);
    void updateNotification(Notifications notifications);
    void deleteNotification(int notificationId);
}

