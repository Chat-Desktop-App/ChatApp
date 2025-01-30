package gov.iti.jets.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private BorderPane chatsBorderPane;
    @FXML
    private ImageView addFriendIcon;

    @FXML
    private ImageView addGroupIcon;

    @FXML
    private Button allButton;

    @FXML
    private Button blockedButton;

    @FXML
    private ListView<Node> chatsTree;

    @FXML
    private BorderPane homeBorderPane;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ImageView homeIcon;

    @FXML
    private AnchorPane left_pane;

    @FXML
    private ImageView logOutIcon;

    @FXML
    private ImageView notificationIcon;

    @FXML
    private Button onlineButton;

    @FXML
    private Button pendingButton;

    @FXML
    private ImageView pictureIcon;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    void allButtonHandling(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/all.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleAddFriendIcon(MouseEvent event) {
        String fxmlPath ="/gov/iti/jets/client/fxml/addFriend.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleAddGroupIcon(MouseEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/addGroup.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleBlockedButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/blocked.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleHomeIcon(MouseEvent event) {
        mainBorderPane.setCenter(mainAnchorPane);
    }

    @FXML
    void handleLogOutIcon(MouseEvent event) {

    }

    @FXML
    void handleNotificationIcon(MouseEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/notification.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleOnlineButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/online.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handlePendingButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/client/fxml/pending.fxml";
        handleButtonAction(fxmlPath);

    }

    @FXML
    void handlePictureIcon(MouseEvent event) {

    }

    @FXML
    void handleSearchField(ActionEvent event) {

    }

    @FXML
    void handlesettingsIcon(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert addFriendIcon != null : "fx:id=\"addFriendIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert addGroupIcon != null : "fx:id=\"addGroupIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert allButton != null : "fx:id=\"allButton\" was not injected: check your FXML file 'home.fxml'.";
        assert blockedButton != null : "fx:id=\"blockedButton\" was not injected: check your FXML file 'home.fxml'.";
        assert chatsTree != null : "fx:id=\"chatsTree\" was not injected: check your FXML file 'home.fxml'.";
        assert homeBorderPane != null : "fx:id=\"homeBorderPane\" was not injected: check your FXML file 'home.fxml'.";
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert left_pane != null : "fx:id=\"left_pane\" was not injected: check your FXML file 'home.fxml'.";
        assert logOutIcon != null : "fx:id=\"logOutIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert notificationIcon != null: "fx:id=\"notificationIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert onlineButton != null : "fx:id=\"onlineButton\" was not injected: check your FXML file 'home.fxml'.";
        assert pendingButton != null : "fx:id=\"pendingButton\" was not injected: check your FXML file 'home.fxml'.";
        assert pictureIcon != null : "fx:id=\"pictureIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'home.fxml'.";
        assert settingsIcon != null : "fx:id=\"settingsIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert mainAnchorPane != null : "fx:id=\"mainAnchorPane\" was not injected: check your FXML file 'home.fxml'.";
        assert mainBorderPane != null : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'home.fxml'.";
        fillChats();
    }

    private void fillChats(){
        ObservableList<Node> observableList = loadFXMLIntoList("/gov/iti/jets/client/fxml/allChats.fxml", 20);
        ListView<Node> listView = createListView(observableList);
        listView.prefWidthProperty().bind(chatsBorderPane.widthProperty());
        listView.prefHeightProperty().bind(chatsBorderPane.heightProperty());
        chatsBorderPane.setCenter(listView);

    }


    private ObservableList<Node> loadFXMLIntoList(String fxmlPath, int count) {
        ObservableList<Node> list = FXCollections.observableArrayList();
        for (int i = 0; i < count; i++) {
            try {
                Node node = new FXMLLoader(getClass().getResource(fxmlPath)).load();
                list.add(node);
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
            }
        }
        return list;
    }

    private ListView<Node > createListView(ObservableList<Node> items) {
        ListView<Node> listView = new ListView<>(items);
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
            protected void updateItem(Node item, boolean empty) {
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

    private void handleButtonAction(String fxmlPath) {
        try {
            Region region = new FXMLLoader(getClass().getResource(fxmlPath)).load();
            region.prefWidthProperty().bind(mainBorderPane.widthProperty());
            region.prefHeightProperty().bind(mainBorderPane.heightProperty());
            mainBorderPane.setCenter(region);

        } catch (IOException e) {
            System.out.println("error at lode " + fxmlPath);
        }
    }

}
