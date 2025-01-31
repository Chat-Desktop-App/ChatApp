package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.services.interfaces.LoadHome;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoadHomeImp extends UnicastRemoteObject implements LoadHome {
    public LoadHomeImp() throws RemoteException {
    }

    @Override
    public List<ContactUser> getMyContact(String phoneNumber) {
        ContactDaoImpl contactDao = new ContactDaoImpl();

        return contactDao.getFriendsContacts(phoneNumber);
    }
}
