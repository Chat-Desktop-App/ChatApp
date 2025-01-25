package gov.iti.jets.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button blockedButton;

    @FXML
    private ListView<?> chatsListView;

    @FXML
    private BorderPane homeBorderPane;

    @FXML
    private ImageView homeIcon;

    @FXML
    private AnchorPane left_pane;

    @FXML
    private Button onlineButton;

    @FXML
    private Button pendingButton;

    @FXML
    private TextField searchField;

    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView userImage;

    @FXML
    void allButtonHandling(ActionEvent event) {

    }

    @FXML
    void handleHomeIcon(MouseEvent event) {

    }

    @FXML
    void handleNotification(MouseEvent event) {

    }

    @FXML
    void handleOnlineButton(ActionEvent event) {

    }

    @FXML
    void handleSearchField(ActionEvent event) {

    }

    @FXML
    void handleUserIcon(MouseEvent event) {

    }

    @FXML
    void handleblockedButton(ActionEvent event) {

    }

    @FXML
    void handlependingButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert allButton != null : "fx:id=\"allButton\" was not injected: check your FXML file 'home.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'home.fxml'.";
        assert blockedButton != null : "fx:id=\"blockedButton\" was not injected: check your FXML file 'home.fxml'.";
        assert chatsListView != null : "fx:id=\"chatsListView\" was not injected: check your FXML file 'home.fxml'.";
        assert homeBorderPane != null : "fx:id=\"homeBorderPane\" was not injected: check your FXML file 'home.fxml'.";
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert left_pane != null : "fx:id=\"left_pane\" was not injected: check your FXML file 'home.fxml'.";
        assert onlineButton != null : "fx:id=\"onlineButton\" was not injected: check your FXML file 'home.fxml'.";
        assert pendingButton != null : "fx:id=\"pendingButton\" was not injected: check your FXML file 'home.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'home.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'home.fxml'.";
        assert userImage != null : "fx:id=\"userImage\" was not injected: check your FXML file 'home.fxml'.";

    }

}
