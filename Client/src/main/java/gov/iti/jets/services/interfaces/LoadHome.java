package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.ContactUser;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface LoadHome extends Remote {
    public List<ContactUser> getMyContact(String phoneNumber) throws RemoteException;
    public List<ContactUser> getPendingContacts(String phoneNumber) throws RemoteException;
    public List<ContactUser> getBlockedContacts(String phoneNumber) throws  RemoteException;
    public List<ContactUser> getAllContacts(String phoneNumber) throws  RemoteException;
    public List<ContactUser> getOnlineContacts(String phoneNumber) throws RemoteException;

}
