package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContactService extends Remote {
    boolean sendFriendRequest(String userId, String contactId) throws RemoteException;

    boolean acceptFriendRequest(String userId, String contactId) throws RemoteException;

    boolean rejectFriendRequest(String userId, String contactId) throws RemoteException;

    boolean blockUser(String userId, String contactId) throws RemoteException;

    User findUserByPhoneNumber(String phoneNumber) throws RemoteException;
}
