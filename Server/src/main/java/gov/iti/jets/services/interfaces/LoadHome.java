package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.Chatable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoadHome extends Remote {
     List<ContactUser> getMyContact(String phoneNumber) throws RemoteException;
     List<ContactUser> getLastContact(String phoneNumber)throws RemoteException;
     List<Group> getLastGroup(String phoneNumber)throws RemoteException;
     List<Chatable> getLastChats(String phoneNumber) throws RemoteException;
     List<ContactUser> getPendingContacts(String phoneNumber) throws RemoteException;
     List<ContactUser> getBlockedContacts(String phoneNumber) throws  RemoteException;
     List<ContactUser> getAllContacts(String phoneNumber) throws  RemoteException;
     List<ContactUser> getOnlineContacts(String phoneNumber) throws RemoteException;
     boolean updateContact(String u1, String u2, ContactStatus status) throws RemoteException;
     public ContactUser refreshUserData(String phoneNumber) throws RemoteException ;
}
