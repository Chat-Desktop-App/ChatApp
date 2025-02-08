package gov.iti.jets.view;

import gov.iti.jets.model.Message;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SendMessageController {


    Message message;

    @FXML
    private HBox parentHBox;

    @FXML
    private VBox contentVBox;

    @FXML
    private Text text;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {

    }

    public void setMessage(Message message) {
        this.message = message;
        text.setText(message.getContent());

        LocalDateTime dateTime = message.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);
        timeStamp.setText(message.getTimestamp().toLocalDateTime().toString());
        setTextFormat();
    }

    private void setTextFormat(){
        StringBuilder builder = new StringBuilder();
        if (message.isBold()) { builder.append("-fx-font-weight: bold;\n");}
        if (message.isItalic()){builder.append("-fx-font-style: italic;\n");}
        if (message.isUnderLine()){builder.append("-fx-underline: " ).append(message.isUnderLine()).append(";\n");}
        if (message.getFontSize() != 0){builder.append("-fx-font-size: ").append(message.getFontSize()).append("px;\n");}
        if (message.getFontStyle() != null){builder.append("-fx-font-family: '").append(message.getFontStyle()).append("';\n");}

        text.setStyle(builder.toString());

        text.setFill(Paint.valueOf(message.getFontColour()));
        contentVBox.setStyle("-fx-background-color:" + message.getTextBackGroundColour()+ ";");
        System.out.println(message.getTextBackGroundColour());
    }

}
