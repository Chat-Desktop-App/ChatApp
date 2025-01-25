package gov.iti.jets.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField phoneField;

    @FXML
    private Button addFriend;

    @FXML
    private Button sendInvetation;

    @FXML
    void handleAddfriend(ActionEvent event) {

    }

    @FXML
    void handleSendInvetation(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert addFriend != null : "fx:id=\"addFriend\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert sendInvetation != null : "fx:id=\"sendInvetation\" was not injected: check your FXML file 'addFriend.fxml'.";

    }
}
