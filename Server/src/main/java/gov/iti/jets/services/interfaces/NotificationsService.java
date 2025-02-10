package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NotificationsService extends Remote {
    void addNotification(Notifications notification) throws RemoteException;
    Notifications getNotificationById(int notificationId) throws RemoteException;
    List<Notifications> getAllNotificationsByUserId(String userId) throws RemoteException;
    void updateNotification(Notifications notification) throws RemoteException;
    void deleteNotification(int notificationId) throws RemoteException;

    User getUserInfo(String phone) throws RemoteException;
}
