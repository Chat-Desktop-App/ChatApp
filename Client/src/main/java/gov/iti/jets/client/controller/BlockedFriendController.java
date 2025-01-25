package gov.iti.jets.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BlockedFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView blockedIcon;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Button unblockButton;

    @FXML
    void initialize() {
        assert blockedIcon != null : "fx:id=\"blockedIcon\" was not injected: check your FXML file 'blocked.fxml'.";
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'blocked.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'blocked.fxml'.";
        assert unblockButton != null : "fx:id=\"unblockButton\" was not injected: check your FXML file 'blocked.fxml'.";

    }

}
