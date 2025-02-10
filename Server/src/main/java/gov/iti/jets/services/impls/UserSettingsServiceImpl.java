package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.UserSettingsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UserSettingsServiceImpl extends UnicastRemoteObject implements UserSettingsService {
    private UserDao user;
    private User updatedUser;

    public UserSettingsServiceImpl() throws RemoteException {
        super();
        this.user = new UserDaoImpl();
    }
    @Override
    public void UpdatePicture(String phoneNumber, byte[] picture) throws RemoteException {
        try {
            this.updatedUser = user.getUser(phoneNumber);
            this.updatedUser.setPicture(picture);
            this.user.update(updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateBio(String phoneNumber, String bio) throws RemoteException {
        try {
            this.updatedUser = user.getUser(phoneNumber);
            this.updatedUser.setBio(bio);
            this.user.update(updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateFullName(String phoneNumber, String fullName) throws RemoteException {
        try {
            this.updatedUser = user.getUser(phoneNumber);
            this.updatedUser.setFullname(fullName);
            this.user.update(updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateStatus(String phoneNumber, Status status) throws RemoteException {
        try {
            this.updatedUser = user.getUser(phoneNumber);
            this.updatedUser.setStatus(status);
            this.user.update(updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateEmail(String phoneNumber, String email) throws RemoteException {
        try {
            this.updatedUser = user.getUser(phoneNumber);
            this.updatedUser.setEmail(email);
            this.user.update(updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
