package gov.iti.jets.view;

import gov.iti.jets.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFriendCellController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label friendName;

    @FXML
    private ImageView imageIcon;
    private User user;

    @FXML
    void initialize() {
        rootPane.setUserData(this);
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'addFriendCell.fxml'.";
        assert imageIcon != null : "fx:id=\"imageIcon\" was not injected: check your FXML file 'addFriendCell.fxml'.";

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        friendName.setText(user.getFname());
    }

}
