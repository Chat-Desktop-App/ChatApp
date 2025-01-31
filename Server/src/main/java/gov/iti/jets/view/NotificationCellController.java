package gov.iti.jets.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class NotificationCellController {

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
        moreButton.setVisible(true);

    }

    @FXML
    void handleMoreButton(ActionEvent event) {

    }

}
