package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.model.UserSession;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.utility.SessionTokenUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginImpl extends UnicastRemoteObject implements Login {
    private static UserDaoImpl dao = new UserDaoImpl();
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        onlineClients.remove(phoneNumber);
        // remove from session
        SessionTokenUtil.removeSession(phoneNumber);

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
    public UserSession createSession(String phoneNumber) throws RemoteException {
        return  SessionTokenUtil.addSession(phoneNumber);
    }

    @Override
    public void exit(String phoneNumber) throws RemoteException {
        try {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setStatus(Status.OFFLINE);

            dao.update(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        onlineClients.remove(phoneNumber);

    }

    @Override
    public boolean validateSession(UserSession session) throws RemoteException {
        return SessionTokenUtil.validateSession(session);
    }

    @Override
    public void skipLogin(UserSession session) throws RemoteException {
        User user = new User();
        user.setPhoneNumber(session.getPhoneNumber());
        user.setStatus(Status.AVAILABLE);
        try {
            dao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
