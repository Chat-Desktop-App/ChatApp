package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDao;
import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ContactService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            // Cannot send a request to a blocked contact
            List<ContactUser> blockedContacts = contactDao.getBlockedContacts(userId);
            for (ContactUser blockedContact : blockedContacts) {
                if (blockedContact.getPhoneNumber().equals(contactId)) {
                    return false;
                }
            }

            // If previously rejected, change status to ACCEPTED
            List<ContactUser> rejectedContacts = contactDao.getRejectedContacts(userId);
            for (ContactUser rejectedContact : rejectedContacts) {
                if (rejectedContact.getPhoneNumber().equals(contactId)) {
                    return contactDao.updateContact(contactId, userId, ContactStatus.ACCEPTED);
                }
            }

            // Check if both users already have pending requests to each other
            boolean senderPending = false;
            boolean receiverPending = false;

            List<ContactUser> pendingContacts = contactDao.getPendingContacts(contactId);
            for (ContactUser pendingContact : pendingContacts) {
                if (pendingContact.getPhoneNumber().equals(userId)) {
                    receiverPending = true;
                    break;
                }
            }

            List<ContactUser> pendingRequests = contactDao.getPendingContacts(userId);
            for (ContactUser request : pendingRequests) {
                if (request.getPhoneNumber().equals(contactId)) {
                    senderPending = true;
                    break;
                }
            }

            // If both requests exist (mutual pending), update both to ACCEPTED
            if (senderPending && receiverPending) {
                return contactDao.updateContact(userId, contactId, ContactStatus.ACCEPTED)
                        && contactDao.updateContact(contactId, userId, ContactStatus.ACCEPTED);
            }

            // If already sent request, prevent duplicate request
            if (senderPending) {
                return false;
            }

            // If they are already friends, do nothing
            List<ContactUser> friendsContacts = contactDao.getFriendsContacts(userId);
            for (ContactUser friendContact : friendsContacts) {
                if (friendContact.getPhoneNumber().equals(contactId)) {
                    return false;
                }
            }

            // Otherwise, send a new friend request
            return contactDao.addContact(userId, contactId) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        /*try {
            // Check if there is a pending request from contactId to userId
            List<ContactUser> pendingRequests = contactDao.getPendingContacts(userId);
            for (ContactUser request : pendingRequests) {
                if (request.getPhoneNumber().equals(contactId)) {
                    // If contactId already sent a request to userId, accept the request
                    return contactDao.updateContact(userId, contactId, ContactStatus.ACCEPTED);
                }
            }

            // Check if user is blocked or rejected
            List<ContactUser> blockedContacts = contactDao.getBlockedContacts(userId);
            List<ContactUser> rejectedContacts = contactDao.getRejectedContacts(contactId);
            for (ContactUser blocked : blockedContacts) {
                if (blocked.getPhoneNumber().equals(contactId)) return false;
            }
            for (ContactUser rejected : rejectedContacts) {
                if (rejected.getPhoneNumber().equals(contactId)) return false;
            }

            // Check if a pending request already exists
            List<ContactUser> existingPendingRequests = contactDao.getPendingContacts(contactId);
            for (ContactUser pending : existingPendingRequests) {
                if (pending.getPhoneNumber().equals(userId)) return false;
            }

            // Check if they are already friends
            List<ContactUser> friends = contactDao.getFriendsContacts(userId);
            for (ContactUser friend : friends) {
                if (friend.getPhoneNumber().equals(contactId)) return false;
            }

            // Send a new friend request
            return contactDao.addContact(userId, contactId) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }*/
        /*List<ContactUser> pendingContacts = new ArrayList<>();
        List<ContactUser> blockedContacts = new ArrayList<>();
        List<ContactUser> rejectedContacts = new ArrayList<>();
        List<ContactUser> friendsContacts = new ArrayList<>();

        try {
            blockedContacts = contactDao.getBlockedContacts(userId);
            rejectedContacts = contactDao.getRejectedContacts(contactId);
            pendingContacts = contactDao.getPendingContacts(contactId);
            friendsContacts = contactDao.getFriendsContacts(userId);
            for (ContactUser blockedContact : blockedContacts) {
                if (blockedContact.getPhoneNumber().equals(contactId)) {
                    return false;
                }
            }
            for (ContactUser rejectedContact : rejectedContacts) {
                if (rejectedContact.getPhoneNumber().equals(contactId)) {
                    return false;
                }
            }
            for (ContactUser pendingContact : pendingContacts) {
                if (pendingContact.getPhoneNumber().equals(contactId)) return false;
            }

            for (ContactUser friendContact : friendsContacts) {
                if (friendContact.getPhoneNumber().equals(contactId)) return false;
            }
            return contactDao.addContact(userId, contactId) > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }*/
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
