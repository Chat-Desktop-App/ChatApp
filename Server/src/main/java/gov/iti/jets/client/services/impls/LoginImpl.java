package gov.iti.jets.client.services.impls;

import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import gov.iti.jets.client.services.interfaces.ChatClient;
import gov.iti.jets.client.services.interfaces.Login;

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
            String hashedPass =dao.hashPass(password);
            if(!user.getPasswordHashed().equals(hashedPass) ){
                return null;
            }else{
                onlineClients.put(phoneNumber, client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public boolean logOut(String phoneNumber) throws RemoteException {
        onlineClients.remove(phoneNumber);
        return false;
    }
}
