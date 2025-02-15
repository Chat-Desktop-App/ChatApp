package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(Message message) throws RemoteException;

    boolean addToLastContactList(Chatable chatable) throws RemoteException;

    void receiveAnnouncement(String message, String timestamp) throws RemoteException;

    void updateStatus(String contactPhone, Status status) throws RemoteException;

    void receivedNotification(Notifications notifications) throws RemoteException;

    void updateContactList(ContactUser contactUser) throws RemoteException;

}
