package gov.iti.jets.view;

import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Group;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.control.ListView;


import java.lang.reflect.Field;

import java.io.ByteArrayInputStream;

public class ChatAreaController {
    private Chatable chatable;
    private ContactUser contactUser;
    private Group group;
    private boolean isContact = false;

    @FXML
    private HBox AttachmentHBOX;
    @FXML
    private AnchorPane chatAnchorPane;

    @FXML
    private Button attachment;

    @FXML
    private Button block;

    @FXML
    private Button call;

    @FXML
    private Button camera;

    @FXML
    private ListView<HBox> chatListView;

    @FXML
    private Button document;

    @FXML
    private Button emoji;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Button image;

    @FXML
    private TextField messageField;

    @FXML
    private Button mic;

    @FXML
    private Button music;

    @FXML
    private Button send;

    @FXML
    private Button video;

    @FXML
    public void initialize() {
        // Hide the HBox when the application starts
        AttachmentHBOX.setVisible(false);

        // Bind the ListView's width and height to the AnchorPane
        chatListView.maxWidthProperty().bind(chatAnchorPane.widthProperty());
        chatListView.maxHeightProperty().bind(chatAnchorPane.heightProperty());

        // Set the cell factory for the ListView to bind cell width dynamically
        chatListView.setCellFactory(lv -> new ListCell<HBox>() {
            @Override
            protected void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    ((VBox) item.getChildren().get(item.getChildren().size() - 1)).maxWidthProperty().bind(chatListView.widthProperty().multiply(0.75));
                }

                // Set background style for the cell
                setStyle("-fx-background-color: transparent;");
            }
        });

    }

    @FXML
    void BlockFriendAction(ActionEvent event) {

    }

    @FXML
    void callAction(ActionEvent event) {

    }

    @FXML
    void camerAction(ActionEvent event) {

    }

    @FXML
    void handleAttachmentButton(ActionEvent event) {
        AttachmentHBOX.setVisible(true);
    }

    @FXML
    void handleShareDoc(ActionEvent event) {

    }

    @FXML
    void handleShareImage(ActionEvent event) {

    }

    @FXML
    void handleshareMusic(ActionEvent event) {

    }

    @FXML
    void micAction(ActionEvent event) {

    }

    @FXML
    void recordVoice(MouseEvent event) {

    }

    @FXML
    void sendAction(ActionEvent event) {

    }

    @FXML
    void sendEmoji(MouseEvent event) {

    }

    @FXML
    void sendEmojiAction(ActionEvent event) {

    }

    @FXML
    void sendMessage(MouseEvent event) {

    }

    @FXML
    void sendMessageByKey(KeyEvent event) {

    }

    @FXML
    void sendVideo(MouseEvent event) {

    }

    @FXML
    void showUserInfo(MouseEvent event) {

    }

    @FXML
    void videoCall(MouseEvent event) {

    }

    @FXML
    void viodeoAction(ActionEvent event) {

    }

    @FXML
    void voiceCall(MouseEvent event) {

    }

    public void setChat(Chatable chatable) {
        this.chatable = chatable;
        friendName.setText(chatable.getName());
        byte [] pic = chatable.getPicture();
        if(pic != null){
            friendIcon.setImage(new Image(new ByteArrayInputStream(pic)));
        }
        if(chatable instanceof ContactUser m){
            contactUser = m;
            isContact = true;
        }else {
            group = (Group) chatable;
            isContact = false;
        }

        ObservableList<HBox> messages = loadMessages();
        chatListView.setItems(messages);
        if (messages != null && !messages.isEmpty()) {
            chatListView.scrollTo(messages.size() - 1);
        }
    }


    private ObservableList<HBox> loadMessages() {
        if (isContact){
            return MessageServiceController.getDirectMessages(contactUser.getPhoneNumber());
        }else {
           return MessageServiceController.getGroupMessages(group.getGroupId());

        }
    }
}
