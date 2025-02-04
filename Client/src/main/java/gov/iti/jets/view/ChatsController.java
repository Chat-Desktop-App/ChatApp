package gov.iti.jets.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.model.ContactUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ChatsController {
    ContactUser contactUser;
    @FXML
    private AnchorPane chat;

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

    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname() + " " + contactUser.getLname());
        friendIcon.setImage(new Image(new ByteArrayInputStream(contactUser.getPicture())));
        switch (contactUser.getStatus()) {
            case AVAILABLE -> status.setFill(Color.LIGHTGREEN);
            case AWAY -> status.setFill(Color.GOLD);
            case BUSY -> status.setFill(Color.INDIANRED);
            case OFFLINE -> status.setFill(Color.GRAY);
        }
    }

    public void handleChatClick(MouseEvent mouseEvent) throws IOException {
        String fxmlPath = "/gov/iti/jets/fxml/chat-area.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Region region = loader.load();
        ChatAreaController controller = loader.getController();
        controller.setContact(contactUser);
        HomeServiceController.getHomeController().setMainBorderPane(region);
    }

}
