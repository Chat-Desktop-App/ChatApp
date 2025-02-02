package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.NotificationsDao;
import gov.iti.jets.database.dao.NotificationsDaoImpl;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.services.interfaces.NotificationsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NotificationsServiceImpl extends UnicastRemoteObject implements NotificationsService {
    private NotificationsDao notificationsDao;
    public NotificationsServiceImpl() throws RemoteException {
        super();
        this.notificationsDao = new NotificationsDaoImpl();
    }

    @Override
    public void addNotification(Notifications notification) throws RemoteException {
        notificationsDao.addNotification(notification);
    }

    @Override
    public Notifications getNotificationById(int notificationId) throws RemoteException {
        return notificationsDao.getNotificationById(notificationId);
    }

    @Override
    public List<Notifications> getAllNotificationsByUserId(String userId) throws RemoteException {
        return notificationsDao.getAllNotificationById(userId);
    }

    @Override
    public void updateNotification(Notifications notification) throws RemoteException {
        notificationsDao.updateNotification(notification);
    }

    @Override
    public void deleteNotification(int notificationId) throws RemoteException {
        notificationsDao.deleteNotification(notificationId);
    }
}
