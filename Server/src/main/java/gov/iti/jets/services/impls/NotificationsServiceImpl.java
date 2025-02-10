package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.NotificationsDao;
import gov.iti.jets.database.dao.NotificationsDaoImpl;
import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.NotificationsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class NotificationsServiceImpl extends UnicastRemoteObject implements NotificationsService {
    private NotificationsDao notificationsDao;
    private  UserDao senderInfo ;

    public NotificationsServiceImpl() throws RemoteException {
        super();
        this.notificationsDao = new NotificationsDaoImpl();
        this.senderInfo = new UserDaoImpl();
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
    @Override
    public User getUserInfo(String phone) throws RemoteException {
        try {
            if (senderInfo == null) {
                throw new RemoteException("UserDao not properly initialized");
            }
            return senderInfo.getUser(phone);
        } catch (SQLException e) {
            // Log the error
            System.err.println("Error retrieving user info: " + e.getMessage());
            throw new RemoteException("Failed to retrieve user information", e);
        }
    }
}
