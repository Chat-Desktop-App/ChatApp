package gov.iti.jets.view;

import gov.iti.jets.ClientApp;
import javafx.application.Platform;
import gov.iti.jets.controller.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HomeController {

    @FXML
    private ToggleButton aiIcon;

    @FXML
    private Button serverIcon;
    @FXML
    private ImageView newNotifiction;

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
        MessageServiceController.setActiveChat(null);
    }

    @FXML
    void handleLogOutIcon(ActionEvent event) {
        LogInServiceController.logOut(HomeServiceController.getUser().getPhoneNumber());
        FXMLLoader loader = new FXMLLoader(ClientApp.class.getResource("fxml/Login.fxml"));
        try {
            Parent root = loader.load();
            LoginController view = loader.getController();
            view.setPhoneNumber(HomeServiceController.getUser().getPhoneNumber());
            view.setRemmberMe(true);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            Session.getInstance().clearSession();
            //Session.clearSession();
            MessageServiceController.setActiveChat(null);


        } catch (IOException e) {
            System.out.println("can't load login");
            e.printStackTrace();
        }


    }

    @FXML
    void handleNotificationIcon(ActionEvent event) {
        String fxmlPath = "/gov/iti/jets/fxml/notification.fxml";
        handleButtonAction(fxmlPath);
        newNotifiction.setVisible(false);
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
        HomeServiceController.setHomeController(this);
        byte [] pic = Session.user.getPicture();
        if (pic != null){
            pictureIcon.setImage(new Image(new ByteArrayInputStream(pic)));
        }
        loadChatsList();
        if(!NotificationServiceController.getNotifications(HomeServiceController.getUser().getPhoneNumber()).isEmpty()){
            newNotifiction.setVisible(true);
        }



    }
    private void loadChatsList() {
        ObservableList<AnchorPane> items = HomeServiceController.getLast();
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
            setMainBorderPane(region);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMainBorderPane(Region region) {
        region.prefWidthProperty().bind(mainBorderPane.widthProperty());
        region.prefHeightProperty().bind(mainBorderPane.heightProperty());
        mainBorderPane.setCenter(region);
        MessageServiceController.setActiveChat(null);

    }
    @FXML
    void handleAi(ActionEvent actionEvent) {

            if (aiIcon.isSelected()) {
                ChatBotController.setAiActivated(!ChatBotController.isAiActivated());
                String imagePath = ClientApp.class.getResource("images/ai_selected.png").toExternalForm();
                aiIcon.setStyle("-fx-background-image: url("+imagePath+");\n" +
                        "         -fx-background-size: 100% 100%;\n" +
                        "         -fx-background-repeat: no-repeat;\n" +
                        "         -fx-background-color: transparent;");

            } else {
                ChatBotController.setAiActivated(!ChatBotController.isAiActivated());
                String imagePath = ClientApp.class.getResource("images/AI.png").toExternalForm();
                aiIcon.setStyle("-fx-background-image: url("+imagePath+");\n" +
                        "         -fx-background-size: 100% 100%;\n" +
                        "         -fx-background-repeat: no-repeat;\n" +
                        "         -fx-background-color: transparent;");
            }
    }


    public void goToAnnouncement(ActionEvent mouseEvent) {
        String fxmlPath = "/gov/iti/jets/fxml/server-message-area.fxml";
        handleButtonAction(fxmlPath);
    }
    public void setPictureIcon(Image picture) {
        Platform.runLater(()->{
            this.pictureIcon.setImage(picture);
        });

    }

    public void setNewNotifiction(boolean flag) {
        Platform.runLater( ()->
                newNotifiction.setVisible(flag)
        );

    }
}
