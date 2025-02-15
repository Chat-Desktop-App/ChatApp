package gov.iti.jets.view;

import gov.iti.jets.controller.NotificationServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.Notification;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ContactService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AddFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFriend;

    @FXML
    private ListView<AnchorPane> listViewOf_Friend_Request;

    @FXML
    private TextField phoneField;

    @FXML
    private Button sendInvitation;
    private ContactService contactService;
    private ObservableList<AnchorPane> friendList = FXCollections.observableArrayList();
    private Set<String> addedPhoneNumbers = new HashSet<>();

    @FXML
    void handleAddFriend(ActionEvent event) {
        String phoneNumber = phoneField.getText().trim();
        if (phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "missing credentials", "you must enter a phone number");
            return;
        }
        if (addedPhoneNumbers.contains(phoneNumber)) {
            showAlert(Alert.AlertType.WARNING, "Duplicate Entry", "This phone number is already added");
            return;
        }
        try {
            User user = contactService.findUserByPhoneNumber(phoneNumber);
            if (user != null) {
                addUserToList(user);
                phoneField.clear();
            } else {
                showAlert(Alert.AlertType.ERROR, "User not found", "No user is found with this phone number");
            }
        } catch (RemoteException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch user data");
            e.printStackTrace();
        }
    }


    @FXML
    void handleSendInvitation(ActionEvent event) {
        if (friendList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Users", "No users to send request to");
            return;
        }
        try {
            for (AnchorPane cell : friendList) {
                AddFriendCellController controller = (AddFriendCellController) cell.getUserData();
                String receiverPhone = controller.getUser().getPhoneNumber();
                String senderPhone = Session.getInstance().getPhoneNumber();
                boolean requestSent = contactService.sendFriendRequest(senderPhone, receiverPhone);
                if (!requestSent) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to send Request to " + receiverPhone);
                } else {
                    Notifications notifications = new Notifications(receiverPhone,senderPhone,"You have a pending friend request ", LocalDateTime.now(),false, Notification.FRIENDREQUEST);
                    NotificationServiceController.addNotification(notifications);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Friend requests sent successfully.");
                }
            }
            friendList.clear();
            addedPhoneNumbers.clear();
        } catch (RemoteException exception) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send requests");
            exception.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        try {
            contactService = (ContactService) Naming.lookup("rmi://localhost:1099/ContactService");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Connection Error", "Failed to connect to server.");
        }

        listViewOf_Friend_Request.setItems(friendList);
    }

    private void addUserToList(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gov/iti/jets/fxml/addFriendCell.fxml"));
            AnchorPane cell = loader.load();
            AddFriendCellController controller = loader.getController();
            controller.setUser(user);
            friendList.add(cell);
            addedPhoneNumbers.add(user.getPhoneNumber());
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user cell");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
