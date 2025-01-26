package gov.iti.jets.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AddFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFriend;

    @FXML
    private ListView<AnchorPane> listViewOf_Friend_Request;

    @FXML
    private TextField phoneField;

    @FXML
    private Button sendInvetation;

    @FXML
    void handleAddfriend(ActionEvent event) {

    }

    @FXML
    void handleSendInvetation(ActionEvent event) {

    }

    @FXML
    void handlephoneField(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addFriend != null : "fx:id=\"addFriend\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert listViewOf_Friend_Request != null : "fx:id=\"listViewOf_Friend_Request\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert sendInvetation != null : "fx:id=\"sendInvetation\" was not injected: check your FXML file 'addFriend.fxml'.";

        ObservableList<AnchorPane> observableList = loadFXMLIntoList("/gov/iti/jets/client/fxml/addFriendCell.fxml", 20);
        listViewOf_Friend_Request.setItems(observableList);

        // Customize the ListView appearance
        listViewOf_Friend_Request.setStyle("-fx-background-color: white;");
        listViewOf_Friend_Request.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setGraphic(item);
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });

        listViewOf_Friend_Request.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listViewOf_Friend_Request.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });

    }

    private ObservableList<AnchorPane> loadFXMLIntoList(String fxmlPath, int count) {
        ObservableList<AnchorPane> list = FXCollections.observableArrayList();
        for (int i = 0; i < count; i++) {
            try {
                AnchorPane anchorPane = new FXMLLoader(getClass().getResource(fxmlPath)).load();
                list.add(anchorPane);
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
                //e.printStackTrace();
            }
        }
        return list;
    }

    private ListView<AnchorPane> createListView(ObservableList<AnchorPane> items) {
        ListView<AnchorPane> listView = new ListView<>(items);
        listView.setStyle("-fx-background-color: white;");
        listView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listView.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setGraphic(item);
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });
        return listView;
    }

}
