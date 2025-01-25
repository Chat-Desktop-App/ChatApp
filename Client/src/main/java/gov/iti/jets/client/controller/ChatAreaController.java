package gov.iti.jets.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ChatAreaController {
    @FXML
    private ImageView send;
    @FXML
    private TextField messageField;
    @FXML
    private ImageView attachment;
    @FXML
    private ImageView mic;
    @FXML
    private ImageView emoji;
    @FXML
    private ImageView camera;
    @FXML
    private ImageView user;
    @FXML
    private ImageView call;
    @FXML
    private ImageView video;

    @FXML
    public void sendMessage(MouseEvent mouseEvent) {

    }

    @FXML
    public void recordVoice(MouseEvent mouseEvent) {
    }

    @FXML
    public void sendMessageByKey(KeyEvent keyEvent) {
    }

    @FXML
    public void sendEmoji(MouseEvent mouseEvent) {
    }

    @FXML
    public void sendVideo(MouseEvent mouseEvent) {
    }

    @FXML
    public void sendAttachment(MouseEvent mouseEvent) {
    }

    @FXML
    public void showUserInfo(MouseEvent mouseEvent) {
    }
    @FXML
    public void voiceCall(MouseEvent mouseEvent) {
    }
    @FXML
    public void videoCall(MouseEvent mouseEvent) {
    }
}
