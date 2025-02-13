package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.LoginStatus;
import gov.iti.jets.model.User;
import gov.iti.jets.services.impls.ChatClientImpl;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class LogInServiceController {
    private static LoginController view;
    private static Login service = RMIConnector.getRmiConnector().getLoginService();
    private static ChatClient client;
    private static User user;

    static {
        try {
            client = new ChatClientImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LogInServiceController.user = user;
    }

    public static void setView(LoginController view) {
        LogInServiceController.view = view;
    }

    public static User logIn(String phoneNumber, String password) {

        try {
            user = service.logIn(phoneNumber, password, client);
            if (user == null) {
                return null;
            }

            // make xml for user config
            LoginStatus session = service.createSession(phoneNumber);
            System.out.println(session.getPhoneNumber() + " " + session.getSessionToken());

//                JAXBContext context = JAXBContext.newInstance(LoginStatus.class);
//                Marshaller marshaller = context.createMarshaller();
//
            String jarDirectory = System.getProperty("user.dir");        //check session to decide which scene to load

//                File file = new File(jarDirectory, "session.xml");
//                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////                marshaller.marshal(session, new FileWriter("session.xml"));
//                marshaller.marshal(session, new FileWriter(file));

            File file2 = new File(jarDirectory, "session.properties");

            Properties properties = new Properties();
            properties.setProperty("phoneNumber", session.getPhoneNumber());
            properties.setProperty("sessionToken", session.getSessionToken());
            properties.store(new FileWriter(file2), "session");


        } catch (RemoteException e) {
            System.out.println("..............");
            service = RMIConnector.rmiReconnect().getLoginService();
            logIn(phoneNumber, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static Boolean checkPhoneNumber(String phoneNumber) {
        try {
            return service.userExists(phoneNumber);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }

    public static Boolean validateSession(LoginStatus session) {
        try {
            return service.validateSession(session);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }

    public static boolean logOut(String phoneNumber) {
        try {
            return service.logOut(phoneNumber);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }

    public static void skipLogIn(LoginStatus status) {
        try {
            service.skipLogin(status, client);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }


    public static void reconnect() {
        try {
            client = new ChatClientImpl();
            service = RMIConnector.getRmiConnector().getLoginService();
            service.reconnect(Session.user.getPhoneNumber(), Session.user.getPasswordHashed(), client);


            // make xml for user config
            LoginStatus session = service.createSession(Session.user.getPhoneNumber());
            try {
//                JAXBContext context = JAXBContext.newInstance(LoginStatus.class);
//                Marshaller marshaller = context.createMarshaller();
//
//                String jarDirectory = System.getProperty("user.dir");        //check session to decide which scene to load
//                File file = new File(jarDirectory,"session.xml");
//
//                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////                marshaller.marshal(session, new FileWriter("session.xml"));
//                marshaller.marshal(session, new FileWriter(file));

                String jarDirectory = System.getProperty("user.dir");        //check session to decide which scene to load
                File file2 = new File(jarDirectory, "session.properties");
                Properties properties = new Properties();
                properties.setProperty("phoneNumber", session.getPhoneNumber());
                properties.setProperty("sessionToken", session.getSessionToken());
                properties.store(new FileWriter(file2), "session");

            } catch (IOException e) {
                service = RMIConnector.rmiReconnect().getLoginService();

                throw new RuntimeException(e);
            }


        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }


    }


}
