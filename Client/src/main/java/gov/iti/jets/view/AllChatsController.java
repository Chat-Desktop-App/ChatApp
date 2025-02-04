package gov.iti.jets.view;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AllChatsController {
    ContactUser contactUser;
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
    private Circle status;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'allChats.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'allChats.fxml'.";
        assert messageCounter != null : "fx:id=\"messageCounter\" was not injected: check your FXML file 'allChats.fxml'.";

    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname() + " " + contactUser.getLname());
        switch (contactUser.getStatus()) {
            case AVAILABLE -> status.setFill(Color.LIGHTGREEN);
            case AWAY -> status.setFill(Color.GOLD);
            case BUSY -> status.setFill(Color.INDIANRED);
            case OFFLINE -> status.setFill(Color.GRAY);
        }
    }
}
