package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.User;
import gov.iti.jets.model.LoginStatus;
import gov.iti.jets.services.impls.ChatClientImpl;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

public class LogInServiceController {
    private static LoginController view;
    private static Login service =  (Login) RMIConnector.getRmiConnector().getLoginService();;
    private static ChatClient client;
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LogInServiceController.user = user;
    }

    static {
        try {
            client = new ChatClientImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setView(LoginController view) {
        LogInServiceController.view = view;
    }

    public static User logIn(String phoneNumber, String password){

        try {
              user = service.logIn(phoneNumber, password, client);
             if(user == null){
                 return null;
             }

             // make xml for user config
             LoginStatus session = service.createSession(phoneNumber);
            try {
                JAXBContext context = JAXBContext.newInstance(LoginStatus.class);
                Marshaller marshaller = context.createMarshaller();


                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(session, new FileWriter("session.xml"));


            } catch (JAXBException e) {
                service = RMIConnector.rmiReconnect().getLoginService();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static Boolean checkPhoneNumber(String phoneNumber){
        try {
            return service.userExists(phoneNumber);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }

    public  static Boolean validateSession(LoginStatus session){
        try {
            return  service.validateSession(session);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }
    public static boolean logOut(String phoneNumber){
        try {
            return service.logOut(phoneNumber);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }
    }
}
