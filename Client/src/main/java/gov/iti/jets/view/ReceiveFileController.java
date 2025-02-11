package gov.iti.jets.view;

import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.model.FileMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class ReceiveFileController {
    private FileMessage fileMessage;

    @FXML
    private VBox contentVBox;

    @FXML
    private Button dowloadButton;

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
    void dowloadButtonHandle(ActionEvent event) {

        byte[] fileData = MessageServiceController.getFileData(fileMessage.getFileId());

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName(fileMessage.getFileName());
        Stage stage = (Stage) parentHBox.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileData);
                showSuccessAlert();
            } catch (IOException e) {
                showErrorAlert();
                e.printStackTrace();
            }
        }
    }

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

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File Save Status");
        alert.setHeaderText(null);
        alert.setContentText("File saved successfully!");
        alert.showAndWait();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Save Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to save the file. Please try again.");
        alert.showAndWait();
    }
}
