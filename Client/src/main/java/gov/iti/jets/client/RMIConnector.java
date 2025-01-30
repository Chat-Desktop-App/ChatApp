package gov.iti.jets.client;



import gov.iti.jets.client.services.interfaces.Login;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private Login loginService;
    private static  RMIConnector rmiConnector;

    private RMIConnector(){
        try {
            Registry reg = LocateRegistry.getRegistry(1099);
            loginService = (Login) reg.lookup("LogIn");


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static RMIConnector getRmiConnector(){
        if(rmiConnector != null)
            return rmiConnector;
        else return new RMIConnector();
    }

    public Login getLoginService() {
        return loginService;
    }
}
