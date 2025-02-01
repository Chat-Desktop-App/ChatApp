package gov.iti.jets;

import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.services.interfaces.Register;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private Login loginService;
    private Register registerService;
    private static  RMIConnector rmiConnector;

    public Register getRegisterService() {
        return registerService;
    }

    private RMIConnector(){
        try {
            Registry reg = LocateRegistry.getRegistry(1099);
            loginService = (Login) reg.lookup("LogIn");
            registerService = (Register) reg.lookup("Register");


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
