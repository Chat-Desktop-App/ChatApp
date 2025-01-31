package gov.iti.jets;

import gov.iti.jets.services.impls.LoginImpl;
import gov.iti.jets.services.impls.RegisterImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public RMIConnector(){
        try {
            LoginImpl login  = new LoginImpl();
            RegisterImpl register = new RegisterImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("LogIn", login);
            registry.bind("Register", register);
            System.out.println("Server is running");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        RMIConnector rmi = new RMIConnector();
    }

}
