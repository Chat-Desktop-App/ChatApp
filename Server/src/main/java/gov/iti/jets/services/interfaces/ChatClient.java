package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Status;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(Message message) throws RemoteException;
    boolean addToLastContactList(Chatable chatable) throws RemoteException;

    void notifyProfilePictureUpdated(String phoneNumber, String picturePath) throws RemoteException;
    void notifyStatusUpdated(String phoneNumber, Status status) throws RemoteException;
    String getPhoneNumber() throws RemoteException;

}
