package gov.iti.jets.services.impls;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.controller.UserSettingsServiceController;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Status;
import gov.iti.jets.view.HomeController;
import gov.iti.jets.view.ProfileController;
import javafx.application.Platform;
import gov.iti.jets.services.interfaces.ChatClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient  {

    private String phoneNumber;

    public ChatClientImpl() throws RemoteException {
    }
    public ChatClientImpl(String phoneNumber) throws RemoteException {
        this.phoneNumber = phoneNumber;
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
    public String getPhoneNumber() throws RemoteException {
        return phoneNumber;
    }

    @Override
    public void notifyProfilePictureUpdated(String phoneNumber, String picturePath) throws RemoteException {
        Platform.runLater(() -> {
            ProfileController profileController = ProfileController.getInstance();
            if (profileController != null) {
                profileController.updateProfilePicture(phoneNumber, picturePath);
            }
            // Also update the home view if it's the current user's picture
            if (phoneNumber.equals(this.phoneNumber)) {
                HomeController homeController = HomeServiceController.getHomeController();
                if (homeController != null) {
                    homeController.updateProfilePicture(picturePath);
                }
            }
        });
    }

    @Override
    public void notifyStatusUpdated(String phoneNumber, Status status) throws RemoteException {
        Platform.runLater(() -> {
            ProfileController profileController = ProfileController.getInstance();
            if (profileController != null) {
                profileController.updateProfileStatus(phoneNumber, status);
            }

        });
    }
}
