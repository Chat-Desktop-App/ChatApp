package gov.iti.jets.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class NotificationController {

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Text contentOfNotification;

    @FXML
    private Button moreButton;

    @FXML
    private Button deleteButton;

    @FXML
    void handleDeleteButton(ActionEvent event) {

    }

    @FXML
    private void handleMoreButton(ActionEvent event) {
            // Make the hidden button visible
            deleteButton.setVisible(true);
        }
    
    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'notification.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'notification.fxml'.";
        assert contentOfNotification != null : "fx:id=\"contentOfNotification\" was not injected: check your FXML file 'notification.fxml'.";
        assert moreButton != null : "fx:id=\"moreButton\" was not injected: check your FXML file 'notification.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'notification.fxml'.";

    }
}
