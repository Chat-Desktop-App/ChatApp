package gov.iti.jets.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    public void receive(String msg) throws RemoteException;
}
