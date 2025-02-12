package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Status;

import java.rmi.RemoteException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSettingsService extends Remote {

    boolean UpdatePicture(String phoneNumber,byte [] pic) throws RemoteException;
    void UpdateBio(String phoneNumber,String bio) throws RemoteException;
    void UpdateFullName(String phoneNumber,String fullName) throws RemoteException;
    void UpdateStatus(String phoneNumber, Status status) throws RemoteException;
    void UpdateEmail(String phoneNumber,String email) throws RemoteException;


}
