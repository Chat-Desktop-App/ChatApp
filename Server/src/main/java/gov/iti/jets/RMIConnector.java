package gov.iti.jets;

import gov.iti.jets.services.impls.LoginImpl;
import gov.iti.jets.services.impls.MessagingServiceImpl;
import gov.iti.jets.services.impls.NotificationsServiceImpl;
import gov.iti.jets.services.impls.RegisterImpl;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.services.interfaces.NotificationsService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private static Registry registry;
    private static boolean isRunning;

    public static void startServer() {
        if(isRunning) {
            System.out.println("system is already running");
            return;
        }
        try{
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("LogIn", new LoginImpl());
            registry.bind("Register", new RegisterImpl());
            registry.bind("MessagingService", new MessagingServiceImpl());
            registry.bind("NotificationsService", new NotificationsServiceImpl());
            isRunning = true;
            System.out.println("server is up and running.");
        } catch(RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        if (!isRunning) {
            System.out.println("Server is already stopped.");
            return;
        }

        try {
            for (String service : registry.list()) {
                registry.unbind(service);
                System.out.println("Unbound service: " + service);
            }
            isRunning = false;
            System.out.println("Server is down.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isServerRunning() {
        return isRunning;
    }
    public static void main(String[] args) {
        startServer();
    }

}
