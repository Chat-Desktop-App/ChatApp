package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.services.interfaces.NotificationsService;
import gov.iti.jets.view.NotificationCellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class NotificationServiceController {
    private static NotificationsService notificationsService = RMIConnector.getRmiConnector().getNotificationService();
    private static ObservableList<AnchorPane> myNotificationsList = FXCollections.observableArrayList();

    public static ObservableList<AnchorPane> getNotifications(String phoneNumber) {
        String fxmlPath = "/gov/iti/jets/fxml/notificationCell.fxml";
        try {
            List<Notifications> list = notificationsService.getAllNotificationsByUserId(phoneNumber);
            // for debugging
            System.out.println("Received notifications: " + list.size());
            for (Notifications n : list) {
                System.out.println("Notification: " + n.getMessage() +
                        ", Type: " + n.getNotificationType());
            }
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
}