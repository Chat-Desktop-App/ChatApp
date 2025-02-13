
package gov.iti.jets;

import gov.iti.jets.services.impls.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RMIConnector {
    private static Registry registry;
    private static boolean isRunning;
    private static String serverIp;
    private static int serverPort;
    static {
        loadConfig();
    }
    private static void loadConfig() {
        Properties properties = new Properties();

        String jarDirectory = System.getProperty("user.dir"); // This points to the directory where the JAR is being run
        File propertiesFile = new File(jarDirectory, "config.properties");

        try (FileInputStream fis = new FileInputStream(propertiesFile)) {
            properties.load(fis);
            serverIp = properties.getProperty("ip","127.0.0.1"); // Default to localhost if not found
            System.out.println(serverIp);
            serverPort = Integer.parseInt(properties.getProperty("port", "1099"));
            System.setProperty("java.rmi.server.hostname", serverIp); // Set RMI hostname
        } catch (IOException e) {
            e.printStackTrace();
            serverIp = "127.0.0.1"; // Fallback to localhost if file reading fails
            serverPort = 1099;
        }
    }

    public static void startServer() {
        if (isRunning) {
            System.out.println("Server is already running.");
            return;
        }

        try {

            if (registry == null) {
                //System.setProperty("java.rmi.server.hostname", ip);
                registry = LocateRegistry.createRegistry(serverPort);
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
            registry.bind("ChatBot", new ChatBotImpl());

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
