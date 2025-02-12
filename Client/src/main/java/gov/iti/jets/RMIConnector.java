package gov.iti.jets;

import gov.iti.jets.services.interfaces.*;
import javafx.scene.Scene;

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
                Registry reg = LocateRegistry.getRegistry(1099);
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
                    sleep(1000); // Retry after a short delay
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
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
        rmiConnector = new RMIConnector();  // Recreate the instance to reconnect

        //Scene saveScene = RunHome.primaryStage.getScene();
        //RunHome.primaryStage.setScene(RunHome.loadingScene);
            rmiConnector = new RMIConnector();
        //RunHome.primaryStage.setScene(saveScene);
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

}
