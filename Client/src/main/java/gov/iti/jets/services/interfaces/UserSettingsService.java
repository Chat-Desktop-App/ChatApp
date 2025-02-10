package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Status;

import java.rmi.RemoteException;

public interface UserSettingsService {

    void UpdatePicture(String phoneNumber,byte[] picture) throws RemoteException;
    void UpdateBio(String phoneNumber,String bio) throws RemoteException;
    void UpdateFullName(String phoneNumber,String fullName) throws RemoteException;
    void UpdateStatus(String phoneNumber, Status status) throws RemoteException;
    void UpdateEmail(String phoneNumber,String email) throws RemoteException;


}
