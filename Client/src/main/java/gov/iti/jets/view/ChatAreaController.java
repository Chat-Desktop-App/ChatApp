package gov.iti.jets.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ChatAreaController {

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

}
