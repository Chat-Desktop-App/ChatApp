package gov.iti.jets.view;

import gov.iti.jets.services.interfaces.MessagingService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.rmi.RemoteException;

public class AnnouncementController {
    @FXML
    private TextArea messageTextArea;

    @FXML
    private ImageView sendButton;

    private MessagingService messagingService;

    public void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }
    @FXML
    private void initialize() {
        sendButton.setOnMouseClicked(event -> sendAnnouncement());
    }
    private void sendAnnouncement() {
        String message = messageTextArea.getText().trim();

        if (message.isEmpty()) {
            System.out.println("Message is empty. Cannot send.");
            return;
        }

        try {
            if (messagingService != null) {
                messagingService.broadcastAnnouncement(message);
                messageTextArea.clear();
                System.out.println("Announcement sent successfully.");
            } else {
                System.out.println("Messaging service is not initialized.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
