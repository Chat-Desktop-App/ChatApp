package gov.iti.jets.view;

import gov.iti.jets.model.FileMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class SendFileController {
    private FileMessage fileMessage;

    @FXML
    private VBox contentVBox;

    @FXML
    private ImageView fileImage;

    @FXML
    private Label fileName;

    @FXML
    private Label fileSize;

    @FXML
    private HBox parentHBox;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {

    }

    public void setFileMessage(FileMessage fileMessage) {
        this.fileMessage = fileMessage;
        fileName.setText(fileMessage.getFileName());
        fileSize.setText(Math.ceil((fileMessage.getFileSize() / 10000.0)) / 100.0 + " MB");

        LocalDateTime dateTime = fileMessage.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);

        switch (fileMessage.getFileType()) {
            case FILE ->
                    fileImage.setImage(new Image(Objects.requireNonNull(ReceiveFileController.class.getResourceAsStream("/gov/iti/jets/images/fileIcon.png"))));
            case IMAGE ->
                    fileImage.setImage(new Image(Objects.requireNonNull(ReceiveFileController.class.getResourceAsStream("/gov/iti/jets/images/image-gallery.png"))));
            case MUSIC ->
                    fileImage.setImage(new Image(Objects.requireNonNull(ReceiveFileController.class.getResourceAsStream("/gov/iti/jets/images/Audio.png"))));

        }
    }
}
