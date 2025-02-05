package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.User;
import gov.iti.jets.services.impls.ChatClientImpl;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;

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

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public Boolean checkPhoneNumber(String phoneNumber){
        try {
            return service.userExists(phoneNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
