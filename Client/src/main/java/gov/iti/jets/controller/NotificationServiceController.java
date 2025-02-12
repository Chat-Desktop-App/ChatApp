package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.NotificationsService;
import gov.iti.jets.view.NotificationCellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class NotificationServiceController {
    private static NotificationsService notificationsService = RMIConnector.getRmiConnector().getNotificationService();
    private static ObservableList<AnchorPane> myNotificationsList = FXCollections.observableArrayList();

    public static ObservableList<AnchorPane> getNotifications(String phoneNumber) {
        String fxmlPath = "/gov/iti/jets/fxml/notificationCell.fxml";
        try {
            List<Notifications> list = notificationsService.getAllNotificationsByUserId(phoneNumber);
            myNotificationsList.clear();
            loadNotifications(fxmlPath, list);
        } catch (RemoteException e) {
            System.out.println("Error getting notifications: " + e.getMessage());
            notificationsService = RMIConnector.rmiReconnect().getNotificationService();
            try {
                List<Notifications> list = notificationsService.getAllNotificationsByUserId(phoneNumber);
                myNotificationsList.clear();
                loadNotifications(fxmlPath, list);
            } catch (RemoteException ex) {
                notificationsService = RMIConnector.rmiReconnect().getNotificationService();
                System.out.println("Failed to reconnect: " + ex.getMessage());
            }
        }
        return myNotificationsList;
    }

    private static void loadNotifications(String fxmlPath, List<Notifications> notificationsList) {
        try {
            for (Notifications notification : notificationsList) {
                FXMLLoader loader = new FXMLLoader(NotificationServiceController.class.getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                NotificationCellController controller = loader.getController();
                controller.setNotificationData(notification);
                myNotificationsList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
    }

    public static void deleteNotification(int notificationId, AnchorPane notificationCell) {
        try {
            notificationsService.deleteNotification(notificationId);
            myNotificationsList.remove(notificationCell);
        } catch (RemoteException e) {
            System.out.println("Error deleting notification: " + e.getMessage());
            notificationsService = RMIConnector.rmiReconnect().getNotificationService();
            try {
                notificationsService.deleteNotification(notificationId);
                myNotificationsList.remove(notificationCell);
            } catch (RemoteException ex) {
                System.out.println("Failed to delete notification: " + ex.getMessage());
            }
        }
    }
    public static void addNotification(Notifications notifications) {
        try {
            notificationsService.addNotification(notifications);
        } catch (RemoteException e) {
            System.out.println("Error Adding notification: " + e.getMessage());
            notificationsService = RMIConnector.rmiReconnect().getNotificationService();
            addNotification(notifications);
        }
    }
    public static ContactUser getUser(String phoneNum) {
        if (phoneNum == null) {
            System.err.println("Phone number is null");
            return null;
        }
        try {
            ContactUser user = notificationsService.getUserInfo(phoneNum);
            if (user == null) {
                System.err.println("No user found for phone number: " + phoneNum);
            }
            return user;
        } catch (RemoteException e) {
            System.err.println("Error getting user info: " + e.getMessage());
            // Try to reconnect
            notificationsService = RMIConnector.rmiReconnect().getNotificationService();
            try {
                return notificationsService.getUserInfo(phoneNum);
            } catch (RemoteException ex) {
                System.err.println("Failed to get user info after reconnect: " + ex.getMessage());
                return null;
            }
        }
    }

    public static void receivedNotification(Notifications notification){
        try {
        String fxmlPath = "/gov/iti/jets/fxml/notificationCell.fxml";
        FXMLLoader loader = new FXMLLoader(NotificationServiceController.class.getResource(fxmlPath));
        AnchorPane  anchorPane = loader.load();
        NotificationCellController controller = loader.getController();
        controller.setNotificationData(notification);
        myNotificationsList.add(anchorPane);
        HomeServiceController.getHomeController().setNewNotifiction(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}