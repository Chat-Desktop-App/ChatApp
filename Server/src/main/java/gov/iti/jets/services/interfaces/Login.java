package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.User;
import gov.iti.jets.model.UserSession;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Login extends Remote {
    public User logIn(String phoneNumber, String password, ChatClient client ) throws RemoteException;
    public boolean logOut(String phoneNumber) throws RemoteException;
    public boolean userExists(String phoneNumber) throws RemoteException;
    public UserSession createSession(String phoneNumber) throws RemoteException;
    public void exit(String phoneNumber) throws RemoteException;
    public boolean validateSession(UserSession session) throws RemoteException;
    public void skipLogin(UserSession session) throws RemoteException;

}
