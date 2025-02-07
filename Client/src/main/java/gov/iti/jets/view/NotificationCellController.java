package gov.iti.jets.view;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Notification;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.services.interfaces.NotificationsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;

public class NotificationCellController {
    private Notifications notification;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, HH:mm");

    @FXML
    private Text contentOfNotification;

    @FXML
    private Button deleteButton;

    @FXML
    private Label friendName;

    @FXML
    private Button moreButton;

    @FXML
    void handleDeleteButton(ActionEvent event) {
        try {
            NotificationsService notificationsService = RMIConnector.getRmiConnector().getNotificationService();
            notificationsService.deleteNotification(notification.getNotificationId());
            ((AnchorPane) deleteButton.getParent()).setVisible(false);
        } catch (RemoteException e) {
            System.err.println("Failed to delete notification: " + e.getMessage());
        }
    }

    @FXML
    void handleMoreButton(ActionEvent event) {
        deleteButton.setVisible(true);
        if (!notification.isRead()) {
            try {
                notification.setRead(true);
                NotificationsService notificationsService = RMIConnector.getRmiConnector().getNotificationService();
                notificationsService.updateNotification(notification);
            } catch (RemoteException e) {
                System.err.println("Failed to mark notification as read: " + e.getMessage());
            }
        }
    }

    public void setNotification(Notifications notification) {
        this.notification = notification;
        contentOfNotification.setText(notification.getMessage());
        String time = notification.getSentAt().format(formatter);
        friendName.setText(time);
        
        if (notification.isRead()) {
            contentOfNotification.setStyle("-fx-fill: gray;");
            friendName.setStyle("-fx-text-fill: gray;");
        }
    }
}