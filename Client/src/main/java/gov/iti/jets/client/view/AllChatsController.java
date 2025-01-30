package gov.iti.jets.client.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AllChatsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Label messageCounter;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'allChats.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'allChats.fxml'.";
        assert messageCounter != null : "fx:id=\"messageCounter\" was not injected: check your FXML file 'allChats.fxml'.";

    }

}
