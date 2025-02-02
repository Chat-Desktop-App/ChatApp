package gov.iti.jets;

import gov.iti.jets.services.impls.*;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.services.interfaces.NotificationsService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public RMIConnector(){
        try {
            LoginImpl login  = new LoginImpl();
            RegisterImpl register = new RegisterImpl();
            MessagingService messagingService = new MessagingServiceImpl();
            NotificationsService notificationsService = new NotificationsServiceImpl();
            LoadHomeImp loadHomeImp = new LoadHomeImp();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("LogIn", login);
            registry.bind("Register", register);
            registry.bind("MessagingService", messagingService);
            registry.bind("NotificationsService", notificationsService);
            registry.bind("LoadHome" , loadHomeImp);
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
