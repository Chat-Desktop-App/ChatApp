package gov.iti.jets.view;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.model.ContactUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class AllCardController {
    private ContactUser contactUser;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private ImageView inboxIcon;

    @FXML
    private Circle status;


    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'allCard.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'allCard.fxml'.";
        assert inboxIcon != null : "fx:id=\"inboxIcon\" was not injected: check your FXML file 'allCard.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'allCard.fxml'.";

    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname()+" " + contactUser.getLname());
        friendIcon.setImage(new Image(new ByteArrayInputStream(contactUser.getPicture())));
        switch (contactUser.getStatus()){
            case AVAILABLE -> status.setFill(Color.LIGHTGREEN);
            case AWAY -> status.setFill(Color.GOLD);
            case BUSY -> status.setFill(Color.INDIANRED);
            case OFFLINE -> status.setFill(Color.GRAY);

        }
    }
}
