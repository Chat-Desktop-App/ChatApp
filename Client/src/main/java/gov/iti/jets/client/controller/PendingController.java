package gov.iti.jets.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PendingController {

    @FXML
    private Label friendName;

    @FXML
    private ImageView acceptIcon;

    @FXML
    private ImageView rejectIcon;

    @FXML
    private ImageView friendIcon;

    @FXML
    void handleAcceptInvitation(MouseEvent event) {

    }

    @FXML
    void handleRejectInvetation(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'pending.fxml'.";
        assert acceptIcon != null : "fx:id=\"acceptIcon\" was not injected: check your FXML file 'pending.fxml'.";
        assert rejectIcon != null : "fx:id=\"rejectIcon\" was not injected: check your FXML file 'pending.fxml'.";
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'pending.fxml'.";

    }
}
