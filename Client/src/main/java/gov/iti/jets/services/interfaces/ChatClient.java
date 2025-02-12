package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(Message message) throws RemoteException;
    boolean addToLastContactList(Chatable chatable) throws RemoteException;
    void receiveAnnouncement(String message, String timestamp) throws RemoteException;
}
