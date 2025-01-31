package gov.iti.jets.client.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    public void receive(String msg) throws RemoteException;
}
