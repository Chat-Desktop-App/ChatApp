package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.User;
import gov.iti.jets.model.UserSession;
import gov.iti.jets.services.impls.ChatClientImpl;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.xml.sax.SAXException;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

public class LogInServiceController {
    private LoginController view;
    private Login service;
    private ChatClient client;

    public LogInServiceController(LoginController view){
        service = (Login) RMIConnector.getRmiConnector().getLoginService();
        this.view = view;
        try {
            client = new ChatClientImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }


    public User logIn(String phoneNumber, String password){
        User user = null;
        try {
              user = service.logIn(phoneNumber, password, client);
             if(user == null){
                 return null;
             }

             // make xml for user config
             UserSession session = service.createSession(phoneNumber);
            try {
                JAXBContext context = JAXBContext.newInstance(UserSession.class);
                Marshaller marshaller = context.createMarshaller();


                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(session, new FileWriter("session.xml"));


            } catch (JAXBException e) {
                service = RMIConnector.rmiReconnector().getLoginService();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public Boolean checkPhoneNumber(String phoneNumber){
        try {
            return service.userExists(phoneNumber);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnector().getLoginService();
            throw new RuntimeException(e);
        }
    }

    public  Boolean validateSession(UserSession session){
        try {
            return  service.validateSession(session);
        } catch (RemoteException e) {
            service = RMIConnector.rmiReconnector().getLoginService();
            throw new RuntimeException(e);
        }
    }
}
