package gov.iti.jets.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatAreaController {

    @FXML
    private Button attachment;

    @FXML
    private Button block;

    @FXML
    private Button call;

    @FXML
    private Button camera;

    @FXML
    private ListView<VBox> chatListView;

    @FXML
    private Button emoji;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private TextField messageField;

    @FXML
    private Button mic;

    @FXML
    private Button send;

    @FXML
    private Button video;

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
    void micAction(ActionEvent event) {

    }

    @FXML
    void recordVoice(MouseEvent event) {

    }

    @FXML
    void sendAction(ActionEvent event) {
        sendMessage();
    }

/**********************************************************************8 */

    private void sendMessage() {
        String messageText = messageField.getText().trim();
        if (!messageText.isEmpty()) {
            try {
                // Load the sent message FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gov/iti/jets/fxml/send-message.fxml"));
                VBox sentMessage = loader.load();

                // Set the message text
                TextFlow sentMesgContent = (TextFlow) sentMessage.lookup("#sentMesgContent");
                ((Text) sentMesgContent.getChildren().get(0)).setText(messageText);

                // Add the message to the chat area
                chatListView.getItems().add(sentMessage);

                // Clear the input field
                messageField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/**********************************************************************8 */
    @FXML
    void sendAttachment(MouseEvent event) {

    }

    @FXML
    void sendEmoji(MouseEvent event) {

    }

    @FXML
    void sendEmojiAction(ActionEvent event) {

    }

    @FXML
    void sendFiles(ActionEvent event) {

    }

    @FXML
    void sendMessage(MouseEvent event) {
       

    }

    @FXML
    void sendMessageByKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
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
