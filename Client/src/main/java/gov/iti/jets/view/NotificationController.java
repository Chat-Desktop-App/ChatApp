package gov.iti.jets.view;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.NotificationServiceController;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class NotificationController {
    @FXML
    private ListView<AnchorPane> listOFNotifications;

    @FXML
    void initialize() {
        assert listOFNotifications != null : "fx:id=\"listOFNotifications\" was not injected: check your FXML file 'notification.fxml'.";

        // Load actual notifications
        listOFNotifications.setItems(NotificationServiceController.getNotifications(HomeServiceController.getUser().getPhoneNumber()));

        // Customize the ListView appearance
        listOFNotifications.setStyle("-fx-background-color: transparent;");
        listOFNotifications.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setGraphic(item);
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });
    }
}