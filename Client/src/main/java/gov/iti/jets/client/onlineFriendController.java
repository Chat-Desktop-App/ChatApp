package gov.iti.jets.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class onlineFriendController {

    @FXML
    private ImageView friendIcon;

    @FXML
    private Circle status;

    @FXML
    private Label friendName;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'pending.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'pending.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'pending.fxml'.";

    }
}
