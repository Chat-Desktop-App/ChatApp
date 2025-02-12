package gov.iti.jets;

import gov.iti.jets.controller.LogInServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.services.interfaces.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class RMIConnector {
    private static RMIConnector rmiConnector;
    private Login loginService;
    private Register registerService;
    private LoadHome loadHome;
    private MessagingService messagingService;
    private NotificationsService notificationsService;
    private ContactService contactService;
    private AddGroup addGroupService;
    private UserSettingsService userSettingsService;
    private ChatBot chatBot;

    // Private constructor to handle the connection to services
    private RMIConnector() {
        while (true) {
            try {
                // Get the registry and lookup each service
                //String ip = "10.145.19.131";
                Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                contactService = (ContactService) reg.lookup("ContactService");
                loginService = (Login) reg.lookup("LogIn");
                registerService = (Register) reg.lookup("Register");
                loadHome = (LoadHome) reg.lookup("LoadHome");
                messagingService = (MessagingService) reg.lookup("MessagingService");
                notificationsService = (NotificationsService) reg.lookup("NotificationsService");
                addGroupService = (AddGroup) reg.lookup("AddGroup");

                userSettingsService = (UserSettingsService) reg.lookup("UserSettingsService");

                chatBot = (ChatBot) reg.lookup("ChatBot");
                break;
            } catch (RemoteException | NotBoundException e) {
                System.out.println("Connection to services failed: " + e.getMessage());
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    System.out.println("can't sleep");
                }
//                showAlert(Alert.AlertType.CONFIRMATION, "Connection Error", "Failed to connect to server."); // Retry after a short dela
            }
        }

    }

    // Singleton pattern to ensure only one instance of RMIConnector
    public static RMIConnector getRmiConnector() {
        if (rmiConnector == null) {
            rmiConnector = new RMIConnector();
        }
        return rmiConnector;
    }

    // Method to reconnect if needed
    public static RMIConnector rmiReconnect() {

        rmiConnector = new RMIConnector();
        LogInServiceController.reconnect();
        System.out.println("Connected successfully");

        return rmiConnector;
    }



    // Getters for the services
    public Login getLoginService() {
        return loginService;
    }

    public Register getRegisterService() {
        return registerService;
    }

    public LoadHome getLoadHome() {
        return loadHome;
    }

    public MessagingService getMessagingService() {
        return messagingService;
    }

    public NotificationsService getNotificationService() {
        return notificationsService;
    }

    public AddGroup getAddGroupService() {
        return addGroupService;
    }

    public ChatBot getChatBot() {
        return chatBot;
    }

    public UserSettingsService getUserSettingsService() {
        return userSettingsService;
    }

    public void shutdown() {
        System.out.println("Shutting down RMI connection...");

        loginService = null;
        registerService = null;
        loadHome = null;
        messagingService = null;
        rmiConnector = null;
        notificationsService = null;
        addGroupService = null;
        userSettingsService = null;
        chatBot = null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        ButtonType buttonTypeOK = new ButtonType("try again");  // Custom OK button label
        ButtonType buttonTypeCancel = new ButtonType("exit");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeOK) {
                System.out.println("Proceed clicked");
            } else if (response == buttonTypeCancel) {
                Platform.exit();
            }
        });
    }

}