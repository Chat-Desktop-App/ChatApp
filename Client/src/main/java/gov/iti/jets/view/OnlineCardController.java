package gov.iti.jets.view;

import java.io.ByteArrayInputStream;
import gov.iti.jets.model.ContactUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OnlineCardController {

    ContactUser contactUser;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private ImageView inboxIcon;

    @FXML
    private Circle status;

    @FXML
    void handleInboxIcon(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname()+" " + contactUser.getLname());
        byte [] pic = contactUser.getPicture();
        if(pic != null){
            friendIcon.setImage(new Image(new ByteArrayInputStream(pic)));
        }
        switch (contactUser.getStatus()) {
            case AVAILABLE -> status.setFill(Color.LIGHTGREEN);
            case AWAY -> status.setFill(Color.GOLD);
            case BUSY -> status.setFill(Color.INDIANRED);
            case OFFLINE -> status.setFill(Color.GRAY);
        }
    }
}
