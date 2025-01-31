package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.model.ContactUser;
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
    LoadHome loadHome;
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
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Region region = loader.load();
            AllController allController = loader.getController();
            allController.setLoadHome(loadHome);
            handleButtonAction1(region);
        } catch (IOException e) {
            System.out.println("error load all");
        }
    }

    @FXML
    void handleOnlineButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/online.fxml";
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Region region = loader.load();
            OnlineController onlineController = loader.getController();
            onlineController.setLoadHome(loadHome);
            handleButtonAction1(region);
        } catch (IOException e) {
            System.out.println("error load online");
        }

    }

    @FXML
    void handlePendingButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/pending.fxml";
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Region region = loader.load();
            PendingController pendingController = loader.getController();
            pendingController.setLoadHome(loadHome);
            handleButtonAction1(region);
        } catch (IOException e) {
            System.out.println("error load pending");
        }
    }

    @FXML
    void handleBlockedButton(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/blocked.fxml";
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Region region = loader.load();
            BlockedController blockedController = loader.getController();
            blockedController.setLoadHome(loadHome);
            handleButtonAction1(region);
        } catch (IOException e) {
            System.out.println("error load blocked");
        }
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
        this.loadHome = loadHome;

        try {
            ObservableList<Node> observableList = loadChatsList(loadHome.getMyContact("1234567890"));
            ListView<Node> listView = createListView(observableList);
            listView.prefWidthProperty().bind(chatsBorderPane.widthProperty());
            listView.prefHeightProperty().bind(chatsBorderPane.heightProperty());
            chatsBorderPane.setCenter(listView);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<Node> loadChatsList(List<ContactUser> myContact) {
        String fxmlPath = "/gov/iti/jets/fxml/allChats.fxml" ;
        ObservableList<Node> list = FXCollections.observableArrayList();
        for (ContactUser contactUser : myContact) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Node node = loader.load();
                AllChatsController controller = loader.getController();
                controller.setUser(contactUser);
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
            FXMLLoader loader =  new FXMLLoader(getClass().getResource(fxmlPath));
            Region region = loader.load();
            region.prefWidthProperty().bind(mainBorderPane.widthProperty());
            region.prefHeightProperty().bind(mainBorderPane.heightProperty());
            mainBorderPane.setCenter(region);
        } catch (IOException e) {
            System.out.println("error at lode " + fxmlPath);
        }
    }

    private void handleButtonAction1(Region region) {
            region.prefWidthProperty().bind(mainBorderPane.widthProperty());
            region.prefHeightProperty().bind(mainBorderPane.heightProperty());
            mainBorderPane.setCenter(region);
    }

}
