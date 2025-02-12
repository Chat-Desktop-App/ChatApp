package gov.iti.jets;

import gov.iti.jets.services.impls.*;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    private static Registry registry;
    private static boolean isRunning;

    public static void startServer() {
        if (isRunning) {
            System.out.println("Server is already running.");
            return; // If the server is already running, return early
        }

        try {
            // Check if registry is already created
            if (registry == null) {
                registry = LocateRegistry.createRegistry(1099); // Create registry only if it doesn't exist
            } else {
                System.out.println("Registry already exists.");
            }

            // Unbind the services if they already exist before rebinding
            for (String service : registry.list()) {
                registry.unbind(service);
                System.out.println("Unbound service: " + service);
            }

            // Bind the services
            registry.bind("LogIn", new LoginImpl());
            registry.bind("Register", new RegisterImpl());
            registry.bind("MessagingService", new MessagingServiceImpl());
            registry.bind("NotificationsService", new NotificationsServiceImpl());
            registry.bind("LoadHome", new LoadHomeImp());
            registry.bind("ContactService", new ContactServiceImpl());
            registry.bind("AddGroup", new AddGroupImpl());
            registry.bind("UserSettingsService", new UserSettingsServiceImpl());

            isRunning = true; // Mark the server as running
            System.out.println("Server is up and running.");
        } catch (RemoteException | AlreadyBoundException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        if (!isRunning) {
            System.out.println("Server is already stopped.");
            return; // If the server is already stopped, return early
        }

        try {
            // Unbind the services
            for (String service : registry.list()) {
                registry.unbind(service);
                System.out.println("Unbound service: " + service);
            }

            isRunning = false; // Mark the server as stopped
            System.out.println("Server is down.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isServerRunning() {
        return isRunning;
    }
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (isRunning) {
                stopServer();
            }
        }));
        startServer();
    }
}
