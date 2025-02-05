package gov.iti.jets.view;

import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.ContactUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.ByteArrayInputStream;

public class ChatAreaController {
    Chatable chatable;
    @FXML
    private HBox AttachmentHBOX;

    @FXML
    private Button attachment;

    @FXML
    private Button block;

    @FXML
    private Button call;

    @FXML
    private Button camera;

    @FXML
    private ListView<?> chatListView;

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
        chatListView.setFocusTraversable(false);
        chatListView.setSelectionModel(null);
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
    }


}
