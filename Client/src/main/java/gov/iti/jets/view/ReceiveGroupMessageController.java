package gov.iti.jets.view;


import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import gov.iti.jets.model.GroupMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class ReceiveGroupMessageController {

    private GroupMessage message;

    @FXML
    private Text messageContent;

    @FXML
    private Text name;

    @FXML
    private HBox parentHBox;

    @FXML
    private ImageView profilePic;

    @FXML
    private VBox contentVBox;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {

    }

    public void setMessage(GroupMessage message) {
        this.message = message;
        messageContent.setText(message.getContent());
        LocalDateTime dateTime = message.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);
        name.setText(message.getName());
        byte [] pic = message.getProfilePicture();
        if (pic != null){
            profilePic.setImage(new Image(new ByteArrayInputStream(pic)));
        }

        setTextFormat();
    }

    private void setTextFormat(){
        StringBuilder builder = new StringBuilder();
        if (message.isBold()) { builder.append("-fx-font-weight: bold;\n");}
        if (message.isItalic()){builder.append("-fx-font-style: italic;\n");}
        if (message.isUnderLine()){builder.append("-fx-underline: " ).append(message.isUnderLine()).append(";\n");}
        if (message.getFontSize() != 0){builder.append("-fx-font-size: ").append(message.getFontSize()).append("px;\n");}
        if (message.getFontStyle() != null){builder.append("-fx-font-family: '").append(message.getFontStyle()).append("';\n");}

        messageContent.setStyle(builder.toString());

        messageContent.setFill(Paint.valueOf(message.getFontColour()));
        contentVBox.setStyle("-fx-background-color:" + message.getTextBackGroundColour()+ ";");
    }
}
