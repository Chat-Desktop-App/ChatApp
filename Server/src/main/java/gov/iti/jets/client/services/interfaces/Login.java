package gov.iti.jets.client.services.interfaces;

import gov.iti.jets.client.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Login extends Remote {
    public User logIn(String phoneNumber, String password, ChatClient client ) throws RemoteException;
    public boolean logOut(String phoneNumber) throws RemoteException;

}
