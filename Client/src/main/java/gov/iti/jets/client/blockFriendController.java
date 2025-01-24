package gov.iti.jets.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class blockedFriendController {


    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private ImageView blockedIcon;

    @FXML
    private Button unblockButton;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'blocked.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'blocked.fxml'.";
        assert blockedIcon != null : "fx:id=\"blockedIcon\" was not injected: check your FXML file 'blocked.fxml'.";
        assert unblockButton != null : "fx:id=\"unblockButton\" was not injected: check your FXML file 'blocked.fxml'.";

    }
}
