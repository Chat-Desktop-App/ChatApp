 
package gov.iti.jets.view;

import gov.iti.jets.model.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;


public class ProfileController {

    @FXML
    private TextField bio;

    @FXML
    private Button edit;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private ComboBox<Status> status;

    @FXML
    void handleEditButton(ActionEvent event) {

    }
    @FXML
    public void initialize() {

        Status online = new Status("ONLINE", "/gov/iti/jets/images/online.png");
        Status offline = new Status("OFFLINE", "/gov/iti/jets/images/offline.png");
        Status idle = new Status("IDLE", "/gov/iti/jets/images/Idle.png");

        status.setItems(FXCollections.observableArrayList(online, offline, idle));

        status.setCellFactory(new Callback<ListView<Status>, ListCell<Status>>() {
            @Override
            public ListCell<Status> call(ListView<Status> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Status item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            imageView.setImage(new Image(getClass().getResourceAsStream(item.getIconPath())));
                            imageView.setFitWidth(20);
                            imageView.setFitHeight(20);
                            setText(item.getName());
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        status.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(getClass().getResourceAsStream(item.getIconPath())));
                    imageView.setFitWidth(16);
                    imageView.setFitHeight(16);
                    setText(item.getName());
                    setGraphic(imageView);
                }
            }
        });

        status.getSelectionModel().selectFirst();
    }
}

