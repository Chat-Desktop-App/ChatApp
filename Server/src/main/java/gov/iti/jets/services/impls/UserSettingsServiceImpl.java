package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.UserSettingsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSettingsServiceImpl extends UnicastRemoteObject implements UserSettingsService {
    private UserDao user;
    private ContactDaoImpl contacts;
    private static final Map<String, ChatClient> onlineClients = new ConcurrentHashMap<>();

    public UserSettingsServiceImpl() throws RemoteException {
        super();
        this.user = new UserDaoImpl();
        this.contacts = new ContactDaoImpl();
    }
    @Override
    public void UpdatePicture(String phoneNumber, String picturepath) throws RemoteException {
        try {
            this.user.updatePicture(phoneNumber, picturepath);
            // Get user's contacts and notify them
            List<ContactUser> contactPhoneNumbers = contacts.getAllContacts(phoneNumber);
            for (ContactUser contactPhone : contactPhoneNumbers) {
                ChatClient client = onlineClients.get(contactPhone.getPhoneNumber());
                if (client != null) {
                    client.notifyProfilePictureUpdated(phoneNumber, picturepath);
                }
            }
            // Notify the user themselves
            ChatClient selfClient = onlineClients.get(phoneNumber);
            if (selfClient != null) {
                selfClient.notifyProfilePictureUpdated(phoneNumber, picturepath);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdateBio(String phoneNumber, String bio) throws RemoteException {
        try {
            this.user.updateBio(phoneNumber,bio);
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

            this.user.updateFName(phoneNumber,firstName);
            this.user.updateLName(phoneNumber,lastName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateStatus(String phoneNumber, Status status) throws RemoteException {
        try {
            this.user.updateStatus(phoneNumber, status);
            // Get user's contacts and notify them
            List<ContactUser> contactPhoneNumbers = contacts.getAllContacts(phoneNumber);
            for (ContactUser contactPhone : contactPhoneNumbers) {
                ChatClient client = onlineClients.get(contactPhone.getPhoneNumber());
                if (client != null) {
                    client.notifyStatusUpdated(phoneNumber, status);
                }
            }
            // Notify the user themselves
            ChatClient selfClient = onlineClients.get(phoneNumber);
            if (selfClient != null) {
                selfClient.notifyStatusUpdated(phoneNumber, status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void UpdateEmail(String phoneNumber, String email) throws RemoteException {
        try {
            this.user.updateEmail(phoneNumber,email);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void registerClient(ChatClient client) throws RemoteException {
        // Store the client callback
        String phoneNumber = client.getPhoneNumber();
        onlineClients.put(phoneNumber, client);
    }
}
