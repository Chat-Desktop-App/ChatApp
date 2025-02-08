package gov.iti.jets;

import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.services.interfaces.Register;
import javafx.scene.Scene;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class RMIConnector {
    private static  RMIConnector rmiConnector;
    private Login loginService;
    private Register registerService;
    private LoadHome loadHome;
    private MessagingService messagingService;


    private RMIConnector(){
        while (true){
            try {
                Registry reg = LocateRegistry.getRegistry(1099);
                loginService = (Login) reg.lookup("LogIn");
                registerService = (Register) reg.lookup("Register");
                loadHome = (LoadHome) reg.lookup("LoadHome");
                messagingService = (MessagingService) reg.lookup("MessagingService");
                break;
            } catch (RemoteException | NotBoundException e) {
                System.out.println("connection to services failed: "+e.getMessage());

                try {
                    sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }

    }

    public static RMIConnector getRmiConnector() {
        if (rmiConnector == null) {
            rmiConnector = new RMIConnector();
        }
        return rmiConnector;
    }
    public static RMIConnector rmiReconnect() {

        Scene saveScene = RunHome.primaryStage.getScene();
        RunHome.primaryStage.setScene(RunHome.loadingScene);
            rmiConnector = new RMIConnector();
        RunHome.primaryStage.setScene(saveScene);
        return rmiConnector;
    }

    public Login getLoginService() {
        return loginService;
    }

    public Register getRegisterService() {
        return registerService;
    }

    public LoadHome getLoadHome() {return loadHome;}

    public MessagingService getMessagingService() {
        return messagingService;
    }

    public void shutdown() {
        System.out.println("Shutting down RMI connection...");

        loginService = null;
        registerService = null;
        loadHome = null;
        messagingService = null;
        rmiConnector = null;
    }

}
