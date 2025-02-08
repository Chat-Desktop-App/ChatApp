package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.GroupDaoImpl;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.LastChatable;
import gov.iti.jets.services.interfaces.LoadHome;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadHomeImp extends UnicastRemoteObject implements LoadHome {
    private final ContactDaoImpl contactDao;
    private final GroupDaoImpl groupDao;

    public LoadHomeImp() throws RemoteException {
        contactDao = new ContactDaoImpl();
        groupDao = new GroupDaoImpl();
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
    public List<ContactUser> getLastContact(String phoneNumber) throws RemoteException {
        try {
            return contactDao.getLastContact(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> getLastGroup(String phoneNumber) throws RemoteException {
        try {
            return groupDao.getLastGroups(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LastChatable> getLastChats(String phoneNumber) throws RemoteException {
        try {
            List<ContactUser> contacts = contactDao.getLastContact(phoneNumber);
            List<Group> groups = groupDao.getLastGroups(phoneNumber);
            List<LastChatable> lastChatables = new ArrayList<>(contacts);
            lastChatables.addAll(groups);
            lastChatables.sort((o1, o2) -> o2.getLastChatAt().compareTo(o1.getLastChatAt()));
            return lastChatables;   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    @Override
    public boolean updateContact(String u1, String u2, ContactStatus status) throws RemoteException {
        try {
            return contactDao.updateContact(u1,u2,status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
