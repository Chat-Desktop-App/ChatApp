package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


import gov.iti.jets.services.impls.MessagingServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private Stage stage;
    private VBox Announcement;
    private VBox Stats;
    private GridPane gridPaneStatus;
    private VBox UserTable;
    private Pane gridPaneSignOut;
    private Parent root;


    @FXML
    private void goToUsers() {
        loadFxml(UserTable);
    }
    @FXML
    private void goToStats() {
        loadFxml(Stats);

    }
    @FXML
    private void goToStatus() {
        loadFxml(gridPaneStatus);
    }
    @FXML
    private void goToAnnouncement() {
        loadFxml(Announcement);
    }
    @FXML
    private void goToSignOut(MouseEvent event) {
        loadScene(event,"/fxml/server-signIn.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loaderAnnouncement = new FXMLLoader(getClass().getResource("/fxml/server-announcement.fxml"));
        try {
            Announcement = loaderAnnouncement.load();
            AnnouncementController announcementController = loaderAnnouncement.getController();

            try {
                //Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                //MessagingService messagingService = (MessagingService) registry.lookup("MessagingService");
                MessagingServiceImpl messagingService = new MessagingServiceImpl();
                announcementController.setMessagingService(messagingService);
            } catch (RemoteException e) {
                e.printStackTrace();
                System.out.println("Error retrieving MessagingService from RMI.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/fxml/server-stats.fxml"));
        try {
            Stats = loaderStats.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader loaderStatus = new FXMLLoader(getClass().getResource("/fxml/server-status.fxml"));
        try {
            gridPaneStatus = loaderStatus.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader loaderUserTable = new FXMLLoader(getClass().getResource("/fxml/server-userTable.fxml"));
        try {
            UserTable = loaderUserTable.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader loaderSignOut= new FXMLLoader(getClass().getResource("/fxml/server-signIn.fxml"));
        try {
            gridPaneSignOut = loaderSignOut.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadFxml (Node pane) {

            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(pane);
            AnchorPane.setTopAnchor(pane, 0.0 );
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
    }

    private void loadScene(MouseEvent event, String fxmlPath) {
        try {
            FXMLLoader loaderSignIn = new FXMLLoader(getClass().getResource(fxmlPath));
            root = loaderSignIn.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
