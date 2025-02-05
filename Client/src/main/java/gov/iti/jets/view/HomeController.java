package gov.iti.jets.view;

import gov.iti.jets.controller.HomeServiceController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button addFriendIcon;

    @FXML
    private Button addGroupIcon;

    @FXML
    private Button allButton;

    @FXML
    private Button blockedButton;

    @FXML
    private BorderPane chatsBorderPane;

    @FXML
    private ListView<AnchorPane> chatsTree;

    @FXML
    private BorderPane homeBorderPane;

    @FXML
    private Button homeIcon;

    @FXML
    private AnchorPane left_pane;

    @FXML
    private Button logOutIcon;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button notificationIcon;

    @FXML
    private Button onlineButton;

    @FXML
    private Button pendingButton;

    @FXML
    private ImageView pictureIcon;

    @FXML
    private TextField searchField;

    @FXML
    private Button settingsIcon;

    @FXML
    void handleAddFriendIcon(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/addFriend.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleAddGroupIcon(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/addGroup.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleHomeIcon(ActionEvent event) {

        mainBorderPane.setCenter(mainAnchorPane);
    }

    @FXML
    void handleLogOutIcon(ActionEvent event) {

    }

    @FXML
    void handleNotificationIcon(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/notification.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void allButtonHandling(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/all.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleOnlineButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/online.fxml";
        handleButtonAction(fxmlPath);

    }

    @FXML
    void handlePendingButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/pending.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handleBlockedButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/blocked.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void handlePictureIcon(MouseEvent event) {

    }

    @FXML
    void handleSearchField(ActionEvent event) {

    }

    @FXML
    void handlesettingsIcon(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/profile.fxml";
        handleButtonAction(fxmlPath);
    }

    @FXML
    void initialize() {
       // loadHome = RMIConnector.getRmiConnector().getLoadHome();
        HomeServiceController.setHomeController(this);
        loadChatsList();
    }

    private void loadChatsList() {
        ObservableList<AnchorPane> items = HomeServiceController.getLastContact();
        chatsTree.prefWidthProperty().bind(chatsBorderPane.widthProperty());
        chatsTree.prefHeightProperty().bind(chatsBorderPane.heightProperty());
        chatsTree.setItems(items);
        chatsTree.setStyle("-fx-background-color: white;");
        chatsTree.setSelectionModel(null);
        chatsTree.setFocusTraversable(false);
        chatsTree.setCellFactory(lv -> new ListCell<>() {
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

    }

    private void handleButtonAction(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Region region = loader.load();
            region.prefWidthProperty().bind(mainBorderPane.widthProperty());
            region.prefHeightProperty().bind(mainBorderPane.heightProperty());
            mainBorderPane.setCenter(region);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMainBorderPane(Region region) {
        region.prefWidthProperty().bind(mainBorderPane.widthProperty());
        region.prefHeightProperty().bind(mainBorderPane.heightProperty());
        mainBorderPane.setCenter(region);
    }

}
