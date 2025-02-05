package gov.iti.jets.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AddFriendCellController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label friendName;

    @FXML
    private ImageView imageIcon;

    @FXML
    void initialize() {
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'addFriendCell.fxml'.";
        assert imageIcon != null : "fx:id=\"imageIcon\" was not injected: check your FXML file 'addFriendCell.fxml'.";

    }

}
