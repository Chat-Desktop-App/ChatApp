package gov.iti.jets.services.impls;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.controller.NotificationServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.Status;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.view.ServerMessageAreaController;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient  {
    private ServerMessageAreaController serverMessageAreaController;
    public ChatClientImpl() throws RemoteException {
    }
    public ChatClientImpl(ServerMessageAreaController controller) throws RemoteException {
        this.serverMessageAreaController = controller;
    }

    @Override
    public void receive(Message message) throws RemoteException {
        MessageServiceController.receiveMessage(message);
    }

    @Override
    public boolean addToLastContactList(Chatable chatable) throws RemoteException {
        return HomeServiceController.addToLastContactList(chatable);
    }
    @Override
    // each client listens for announcements on another thread
    public void receiveAnnouncement(String message, String timestamp) throws RemoteException {
        System.out.println("Received Announcement: " + message + " at " + timestamp);
        Platform.runLater(() -> {
            serverMessageAreaController.addAnnouncement(message, timestamp);
        });
    }
    @Override
    public void updateStatus(String contactPhone, Status status) throws RemoteException {
        System.out.println( contactPhone + " : " +status);
        Session.updateStatus(contactPhone , status);
    }

    @Override
    public void receivedNotification(Notifications notifications) throws RemoteException {
        NotificationServiceController.receivedNotification(notifications);
    }
}
