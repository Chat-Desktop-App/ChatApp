package gov.iti.jets.view;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.services.interfaces.NotificationsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class NotificationController {
    private static NotificationsService notificationsService = RMIConnector.getRmiConnector().getNotificationService();
    private static final String phoneNumber = "1234567890";
    
    @FXML
    private ListView<AnchorPane> listOFNotifications;

    @FXML
    void initialize() {
        loadNotifications();
        setupListView();
    }

    private void loadNotifications() {
        try {
            List<Notifications> notifications = notificationsService.getAllNotificationsByUserId(phoneNumber);
            ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
            
            for (Notifications notification : notifications) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gov/iti/jets/fxml/notificationCell.fxml"));
                AnchorPane anchorPane = loader.load();
                NotificationCellController controller = loader.getController();
                controller.setNotification(notification);
                observableList.add(anchorPane);
            }
            
            listOFNotifications.setItems(observableList);
        } catch (RemoteException e) {
            notificationsService = RMIConnector.rmiReconnect().getNotificationService();
            loadNotifications();
        } catch (IOException e) {
            System.err.println("Error loading notification cell: " + e.getMessage());
        }
    }

    private void setupListView() {
        listOFNotifications.setStyle("-fx-background-color: white;");
        listOFNotifications.setSelectionModel(null);
        listOFNotifications.setFocusTraversable(false);
        
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

        hideScrollBars();
    }

    private void hideScrollBars() {
        listOFNotifications.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listOFNotifications.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });
    }
}