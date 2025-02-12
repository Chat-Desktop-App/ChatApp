package gov.iti.jets.view;

import gov.iti.jets.controller.NotificationServiceController;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Notification;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;


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

    @FXML
    private ImageView friendPic;

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
        deleteButton.setVisible(!deleteButton.isVisible());
    }

    public void setNotificationData(Notifications notification) {
        this.notificationData = notification;
        contentOfNotification.setText(notification.getMessage());
            // Set notification type message
            if (notification.getNotificationType() == null) {
                contentOfNotification.setText("Unknown Notification");
            } else {
                switch (notification.getNotificationType()) {
                    case FRIENDREQUEST:
                        contentOfNotification.setText("sent you a Friend Request");
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
        try {
            if (notification.getSenderId() != null) {
                ContactUser sender = NotificationServiceController.getUser(notification.getSenderId());
                if (sender != null) {

                    if(notification.getNotificationType() == Notification.ANNOUNCEMENT){
                        Image image = new Image(getClass().getResource("/gov/iti/jets/images/orca.png").toExternalForm());
                        friendPic.setImage(image);
                        friendName.setText("ORCA");

                    }else {
                        friendName.setText(sender.getName());
                        byte[] pic = sender.getPicture();
                        if (pic != null) {
                            friendPic.setImage(new Image(new ByteArrayInputStream(pic)));
                        } else {
                            friendPic = friendPic;
                        }
                    }
                } else {
                    friendName.setText("Unknown User");
                    friendPic = friendPic;
                }
            }
        } catch (RuntimeException e) {
            System.err.println("Error loading user data: " + e.getMessage());
            friendName.setText("Error loading user");
        }
    }
}