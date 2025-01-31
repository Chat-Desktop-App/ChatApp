package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.controller.HomeCon;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.LoadHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class HomeController {
    private HomeCon homeCon;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ListView<Node> chatsTree;

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
    void allButtonHandling(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/all.fxml";
        handleButtonAction(fxmlPath);
    }

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
    void handleBlockedButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/blocked.fxml";
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

    }
    public void setLoadHome(LoadHome loadHome)  {
        homeCon = new HomeCon(loadHome);

        try {
            ObservableList<Node> observableList = homeCon.loadFXMLIntoList(loadHome.getMyContact("9876543210"));
            ListView<Node> listView = createListView(observableList);
            listView.prefWidthProperty().bind(chatsBorderPane.widthProperty());
            listView.prefHeightProperty().bind(chatsBorderPane.heightProperty());
            chatsBorderPane.setCenter(listView);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

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
