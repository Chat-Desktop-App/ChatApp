package gov.iti.jets.view;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import gov.iti.jets.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class receiveMessageController {
    Message message;

    @FXML
    private VBox contentVBox;
    @FXML
    private HBox parentHBox;

    @FXML
    private Text messageContent;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {

    }

    public void setMessage(Message message) {
        this.message = message;
        messageContent.setText(message.getContent());

        LocalDateTime dateTime = message.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);
        timeStamp.setText(message.getTimestamp().toLocalDateTime().toString());
    }

}
