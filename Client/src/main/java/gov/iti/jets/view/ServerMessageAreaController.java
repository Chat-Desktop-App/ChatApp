package gov.iti.jets.view;

import gov.iti.jets.model.Announcements;
import gov.iti.jets.services.interfaces.MessagingService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ServerMessageAreaController {
    private final ObservableList<HBox> messages = FXCollections.observableArrayList();
    @FXML
    private AnchorPane chatAnchorPane;
    @FXML
    private ListView<HBox> serverMessageListView;

    @FXML
    public void initialize() {
        serverMessageListView.maxWidthProperty().bind(chatAnchorPane.widthProperty());
        serverMessageListView.maxHeightProperty().bind(chatAnchorPane.heightProperty());
        serverMessageListView.setItems(messages);

        loadAnnouncementsFromDatabase();
    }

    public void addAnnouncement(String message, String timestamp) {
        Platform.runLater(() -> {
            System.out.println("Adding Announcement: " + message + " at " + timestamp);
            // Create a UI element to show the announcement
            HBox announcementBox = createAnnouncementBox(message, timestamp);
            messages.add(announcementBox);
            if (serverMessageListView != null) serverMessageListView.scrollTo(messages.size() - 1);
        });
    }

    private HBox createAnnouncementBox(String message, String timestamp) {
        VBox vbox = new VBox();
        vbox.getChildren().add(new javafx.scene.control.Label(timestamp));
        vbox.getChildren().add(new javafx.scene.control.Label(message));

        HBox hbox = new HBox(vbox);
        hbox.setStyle("-fx-background-color: #ffcc00; -fx-padding: 10px; -fx-border-radius: 5px; -fx-border-color: black;");
        return hbox;
    }

    private void loadAnnouncementsFromDatabase() {
        new Thread(() -> { // Run in a background thread to prevent UI freezing
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                MessagingService messagingService = (MessagingService) registry.lookup("MessagingService");
                List<Announcements> announcements = messagingService.getAnnouncements();
                Platform.runLater(() -> {
                    for (Announcements announcement : announcements) {
                        addAnnouncement(announcement.getMessage(), announcement.getTimestamp().toString());
                    }
                });

            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
                Platform.runLater(() -> System.out.println("Error loading announcements from database."));
            }
        }).start();
    }
}


