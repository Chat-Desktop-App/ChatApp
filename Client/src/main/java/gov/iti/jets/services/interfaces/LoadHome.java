package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.ContactUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoadHome extends Remote {
    public List<ContactUser> getMyContact(String phoneNumber) throws RemoteException;
}
