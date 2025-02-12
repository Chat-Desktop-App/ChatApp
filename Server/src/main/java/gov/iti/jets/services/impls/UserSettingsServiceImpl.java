package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.UserSettingsService;
import gov.iti.jets.utility.PictureUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class UserSettingsServiceImpl extends UnicastRemoteObject implements UserSettingsService {
    private UserDao userDao;
    private User updatedUser;

    public UserSettingsServiceImpl() throws RemoteException {
        super();
        this.userDao = new UserDaoImpl();
    }
    @Override
    public boolean UpdatePicture(String phoneNumber, byte [] picByte) throws RemoteException {
        try {
            String picturePath = PictureUtil.saveUserProfilePicture(picByte,userDao.getUser(phoneNumber));
            return userDao.updatePicture(phoneNumber, picturePath) > 0;
        } catch (SQLException e) {
            System.out.println("can't update picture at database");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void UpdateBio(String phoneNumber, String bio) throws RemoteException {
        try {
            this.userDao.updateBio(phoneNumber,bio);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateFullName(String phoneNumber, String fullName) throws RemoteException {
        try {
            String[] nameParts = fullName.trim().split("\\s+"); // Split by spaces

            String firstName = nameParts[0]; // First word
            String lastName = (nameParts.length > 1) ? nameParts[nameParts.length - 1] : ""; // Last word

            this.userDao.updateFName(phoneNumber,firstName);
            this.userDao.updateLName(phoneNumber,lastName);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateStatus(String phoneNumber, Status status) throws RemoteException {
        try {
            this.userDao.updateStatus(phoneNumber, status);

            List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(phoneNumber);
            for (ContactUser contactUser : contacts) {
                ChatClient chatClient = LoginImpl.getOnlineClients().get(contactUser.getPhoneNumber());
                if(chatClient != null){
                    chatClient.updateStatus(phoneNumber,status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateEmail(String phoneNumber, String email) throws RemoteException {
        try {
            this.userDao.updateEmail(phoneNumber,email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
