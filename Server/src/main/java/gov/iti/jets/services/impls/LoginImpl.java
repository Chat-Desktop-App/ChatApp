package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.AnnouncementDao;
import gov.iti.jets.database.dao.AnnouncementDaoImpl;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.Announcements;
import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.model.LoginStatus;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.utility.LoginTokenUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LoginImpl extends UnicastRemoteObject implements Login {
    private static UserDaoImpl dao = new UserDaoImpl();
    private AnnouncementDao announcementDao = new AnnouncementDaoImpl();

    private static HashMap<String, ChatClient> onlineClients = new HashMap<>();


    public LoginImpl() throws RemoteException {

    }



    @Override
    public User logIn(String phoneNumber, String password, ChatClient client) throws RemoteException {
        User user = null;

        try{
            user = dao.getUser(phoneNumber);
            if (user == null) {
                return null;
            }
            String hashedPass =dao.hashPass(password);
            if(!user.getPasswordHashed().equals(hashedPass) ){
                return null;
            }else{
                onlineClients.put(phoneNumber, client);
                User updateStatusUser = new User();
                updateStatusUser.setPhoneNumber(phoneNumber);
                updateStatusUser.setStatus(Status.AVAILABLE);
                dao.update(updateStatusUser);
                sendPendingAnnouncements(phoneNumber, client);

                List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(phoneNumber);
                for (ContactUser contactUser : contacts) {
                    ChatClient chatClient = onlineClients.get(contactUser.getPhoneNumber());
                    if(chatClient != null){
                        chatClient.updateStatus(phoneNumber,Status.AVAILABLE);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }



    @Override
    public boolean logOut(String phoneNumber) throws RemoteException {
        try {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setStatus(Status.OFFLINE);
            dao.update(user);
            List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(phoneNumber);
            for (ContactUser contactUser : contacts) {
                ChatClient chatClient = onlineClients.get(contactUser.getPhoneNumber());
                if(chatClient != null){
                    chatClient.updateStatus(phoneNumber,Status.OFFLINE);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        onlineClients.remove(phoneNumber);
        LoginTokenUtil.removeSession(phoneNumber);
        return true;
    }

    @Override
    public boolean userExists(String phoneNumber) throws RemoteException {
        User user = null;
        try {
            user = dao.getUser(phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public LoginStatus createSession(String phoneNumber) throws RemoteException {
        return  LoginTokenUtil.addSession(phoneNumber);
    }

    @Override
    public void exit(String phoneNumber) throws RemoteException {
        try {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setStatus(Status.OFFLINE);

            dao.update(user);

            List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(phoneNumber);
            for (ContactUser contactUser : contacts) {
                ChatClient chatClient = onlineClients.get(contactUser.getPhoneNumber());
                if(chatClient != null){
                    chatClient.updateStatus(phoneNumber,Status.OFFLINE);
                }
            }

            System.out.println(phoneNumber + " exited");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateSession(LoginStatus session) throws RemoteException {
        return LoginTokenUtil.validateSession(session);
    }

    @Override
    public void skipLogin(LoginStatus session, ChatClient client) throws RemoteException {
        User user = new User();
        user.setPhoneNumber(session.getPhoneNumber());
        user.setStatus(Status.AVAILABLE);

        try {
            dao.update(user);
            List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(session.getPhoneNumber());
            for (ContactUser contactUser : contacts) {
                ChatClient chatClient = onlineClients.get(contactUser.getPhoneNumber());
                if(chatClient != null){
                    chatClient.updateStatus(session.getPhoneNumber(),Status.OFFLINE);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String userPhone) throws RemoteException {
        try {
            return dao.getUser(userPhone);
        } catch (SQLException e) {
            System.out.println("can't find user: "  + userPhone);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendPendingAnnouncements(String userId, ChatClient client) {
        try {
            List<Announcements> announcements = announcementDao.getAllAnnouncements();
            for (Announcements announcement : announcements) {
                String timestamp = announcement.getTimestamp().toString();
                client.receiveAnnouncement(announcement.getMessage(), timestamp);
            }
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User reconnect(String phoneNumber, String password, ChatClient client) throws RemoteException {
        User user = null;

        try{
            user = dao.getUser(phoneNumber);
            if (user == null) {
                return null;
            }

            if(!user.getPasswordHashed().equals(password) ){
                return null;
            }else{
                onlineClients.put(phoneNumber, client);
                User updateStatusUser = new User();
                updateStatusUser.setPhoneNumber(phoneNumber);
                updateStatusUser.setStatus(Status.AVAILABLE);
                dao.update(updateStatusUser);
                sendPendingAnnouncements(phoneNumber, client);

                List<ContactUser> contacts  = new ContactDaoImpl().getFriendsContacts(phoneNumber);
                for (ContactUser contactUser : contacts) {
                    ChatClient chatClient = onlineClients.get(contactUser.getPhoneNumber());
                    if(chatClient != null){
                        chatClient.updateStatus(phoneNumber,Status.AVAILABLE);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static HashMap<String, ChatClient> getOnlineClients() {
        return onlineClients;
    }

}
