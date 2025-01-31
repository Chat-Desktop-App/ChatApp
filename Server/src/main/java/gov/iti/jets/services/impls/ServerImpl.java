package gov.iti.jets.services.impls;

import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.services.interfaces.Register;
import gov.iti.jets.services.interfaces.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
    private static final Login loginImpl;
    private static final Register registerImpl;

    static {
        try {
            loginImpl = new LoginImpl();
            registerImpl = new RegisterImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public ServerImpl() throws RemoteException {
    }

    @Override
    public User logIn(String phoneNumber, String password, ChatClient client) throws RemoteException {

        return loginImpl.logIn(phoneNumber, password, client);
    }

    @Override
    public boolean logOut(String phoneNumber) throws RemoteException {
        return false;
    }

    @Override
    public User SignUp(User user, byte[] profilePicture) throws RemoteException {
        return registerImpl.SignUp(user, profilePicture);
    }
}
