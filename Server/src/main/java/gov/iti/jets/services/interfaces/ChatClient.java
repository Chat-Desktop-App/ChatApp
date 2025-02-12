package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.Status;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(Message message) throws RemoteException;
    
    boolean addToLastContactList(Chatable chatable) throws RemoteException;

    void updateStatus(String contactPhone , Status status) throws RemoteException;

    void receivedNotification(Notifications notifications) throws RemoteException;

}
