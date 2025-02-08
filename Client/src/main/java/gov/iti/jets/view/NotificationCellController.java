package gov.iti.jets.view;

import gov.iti.jets.controller.NotificationServiceController;
import gov.iti.jets.model.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class NotificationCellController {
    @FXML
    private AnchorPane root;

    @FXML
    private Text contentOfNotification;

    @FXML
    private Button deleteButton;

    @FXML
    private Label friendName;

    @FXML
    private Button moreButton;

    private Notifications notificationData;

    @FXML
    void initialize(){
        moreButton.setVisible(true);
    }

    @FXML
    void handleDeleteButton(ActionEvent event) {
        NotificationServiceController.deleteNotification(notificationData.getNotificationId(), root);
    }

    @FXML
    void handleMoreButton(ActionEvent event) {
        deleteButton.setVisible(true);
    }

    public void setNotificationData(Notifications notification) {
        this.notificationData = notification;
        contentOfNotification.setText(notification.getMessage());

        // Set friend name based on notification type
        if (notification.getNotificationType() == null) {
            contentOfNotification.setText("Unknown Type");
        } else {
            switch (notification.getNotificationType()) {
                case FRIENDREQUEST:
                    contentOfNotification.setText("you have a Friend Request");
                    break;
                case MESSAGE:
                    contentOfNotification.setText("sent a new Message");
                    break;
                case ANNOUNCEMENT:
                    contentOfNotification.setText("you have an Announcement from the server");
                    break;
                case ADDTOGROUP:
                    contentOfNotification.setText("you have a Group Invitation");
                    break;

            }
        }
    }
}