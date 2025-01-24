package gov.iti.jets.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class OnlineFriendController {

    @FXML
    private ImageView friendIcon;

    @FXML
    private Circle status;

    @FXML
    private Label friendName;

    @FXML
    private ImageView inboxIcon;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'onlineFriend.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'onlineFriend.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'onlineFriend.fxml'.";
        assert inboxIcon != null : "fx:id=\"inboxIcon\" was not injected: check your FXML file 'onlineFriend.fxml'.";

    }
}
