package gov.iti.jets.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
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
    private ImageView addFriendIcon;

    @FXML
    private ImageView addGroupIcon;

    @FXML
    private Button allButton;

    @FXML
    private Button blockedButton;

    @FXML
    private ListView<?> chatsTree;

    @FXML
    private BorderPane homeBorderPane;

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
    private StackPane stackPane;

    @FXML
    void allButtonHandling(ActionEvent event) {
               handleButtonAction("/gov/iti/jets/client/fxml/allFriends.fxml");
    }

    @FXML
    void handleAddFriendIcon(MouseEvent event) {
         try {
             StackPane addFStackPane = new FXMLLoader(getClass().getResource("/gov/iti/jets/client/fxml/addFriend.fxml")).load();
             stackPane.getChildren().clear();
             stackPane.getChildren().add(addFStackPane);
         } catch (IOException e) {
             System.out.println("cant Load addFriend");
         }
    }

    @FXML
    void handleAddGroupIcon(MouseEvent event) {

    }

    @FXML
    void handleBlockedButton(ActionEvent event) {
        handleButtonAction("/gov/iti/jets/client/fxml/blocked.fxml");

    }

    @FXML
    void handleHomeIcon(MouseEvent event) {

    }

    @FXML
    void handleLogOutIcon(MouseEvent event) {

    }

    @FXML
    void handleNotificationIcon(MouseEvent event) {
        

    }

    @FXML
    void handleOnlineButton(ActionEvent event) {
        handleButtonAction("/gov/iti/jets/client/fxml/online.fxml");
    }

    @FXML
    void handlePendingButton(ActionEvent event) {
        handleButtonAction("/gov/iti/jets/client/fxml/pending.fxml");

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
        assert notificationIcon != null
                : "fx:id=\"notificationIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert onlineButton != null : "fx:id=\"onlineButton\" was not injected: check your FXML file 'home.fxml'.";
        assert pendingButton != null : "fx:id=\"pendingButton\" was not injected: check your FXML file 'home.fxml'.";
        assert pictureIcon != null : "fx:id=\"pictureIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'home.fxml'.";
        assert settingsIcon != null : "fx:id=\"settingsIcon\" was not injected: check your FXML file 'home.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'home.fxml'.";

    }

    private void handleButtonAction(String fxmlPath) {
        ObservableList<AnchorPane> observableList = loadFXMLIntoList(fxmlPath, 20);
        ListView<AnchorPane> listView = createListView(observableList);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(listView);
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
