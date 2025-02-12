package gov.iti.jets.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;


import gov.iti.jets.services.impls.MessagingServiceImpl;
import gov.iti.jets.services.interfaces.MessagingService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private Stage stage;
    private GridPane gridPaneAnnouncement;
    private GridPane gridPaneStats;
    private GridPane gridPaneStatus;
    private GridPane gridPaneUserTable;
    private Pane gridPaneSignOut;


    @FXML
    private void goToUsers() {
        loadFxml(gridPaneUserTable);
    }
    @FXML
    private void goToStats() {
        loadFxml(gridPaneStats);

    }
    @FXML
    private void goToStatus() {
        loadFxml(gridPaneStatus);
    }
    @FXML
    private void goToAnnouncement() {
        loadFxml(gridPaneAnnouncement);
    }
    @FXML
    private void goToSignOut() {
        loadFxml(gridPaneSignOut);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loaderAnnouncement = new FXMLLoader(getClass().getResource("/fxml/server-announcement.fxml"));
        try {
            gridPaneAnnouncement = loaderAnnouncement.load();
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
            gridPaneStats = loaderStats.load();
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
            gridPaneUserTable = loaderUserTable.load();
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
}
