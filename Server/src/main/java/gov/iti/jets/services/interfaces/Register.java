package gov.iti.jets.services.interfaces;


import gov.iti.jets.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Register extends Remote {
    public User SignUp(User user) throws RemoteException;
}
