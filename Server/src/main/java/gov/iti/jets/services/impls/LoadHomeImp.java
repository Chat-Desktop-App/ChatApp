package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.services.interfaces.LoadHome;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.SQLException;
import java.util.List;

public class LoadHomeImp extends UnicastRemoteObject implements LoadHome {
    private final ContactDaoImpl contactDao;
    public LoadHomeImp() throws RemoteException {
        contactDao = new ContactDaoImpl();
    }

    @Override
    public List<ContactUser> getMyContact(String phoneNumber) {
        try {
            return contactDao.getFriendsContacts(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ContactUser> getPendingContacts(String phoneNumber) throws RemoteException {
        try {
            return contactDao.getPendingContacts(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ContactUser> getBlockedContacts(String phoneNumber) throws RemoteException {
        try {
            return contactDao.getBlockedContacts(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ContactUser> getAllContacts(String phoneNumber) throws RemoteException {
        try {
            return contactDao.getAllContacts(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ContactUser> getOnlineContacts(String phoneNumber) throws RemoteException {
        try {
            return contactDao.getOnlineContacts(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
