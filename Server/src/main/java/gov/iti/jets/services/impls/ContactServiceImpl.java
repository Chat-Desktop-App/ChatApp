package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDao;
import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ContactService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private final ContactDao contactDao;
    private final UserDao userDao;
    public ContactServiceImpl() throws RemoteException {
        super();
        this.userDao = new UserDaoImpl();
        this.contactDao = new ContactDaoImpl();
    }

    @Override
    public boolean sendFriendRequest(String userId, String contactId) throws RemoteException {
        try {
            return contactDao.addContact(userId, contactId) > 0;

        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean acceptFriendRequest(String userId, String contactId) throws RemoteException {
        try {
            return contactDao.updateContact(userId, contactId, ContactStatus.ACCEPTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectFriendRequest(String userId, String contactId) throws RemoteException {
        try {
            return contactDao.updateContact(userId, contactId, ContactStatus.REJECTED);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean blockUser(String userId, String contactId) throws RemoteException {
        try {
            return contactDao.updateContact(userId, contactId, ContactStatus.BLOCKED);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) throws RemoteException {
        try {
            return userDao.getUser(phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
