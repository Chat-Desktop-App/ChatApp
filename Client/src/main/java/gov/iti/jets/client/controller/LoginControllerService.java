package gov.iti.jets.client.controller;

import gov.iti.jets.client.RMIConnector;
import gov.iti.jets.client.model.User;
import gov.iti.jets.client.services.impls.ChatClientImpl;
import gov.iti.jets.client.services.interfaces.Login;
import gov.iti.jets.client.view.LoginController;

import java.rmi.RemoteException;

public class LoginControllerService {
    private LoginController view;
    private Login loginService;
    private ChatClientImpl client;

    public LoginControllerService(LoginController view){
        try {
            loginService = (Login) RMIConnector.getRmiConnector().getLoginService();
            client = new ChatClientImpl();
            this.view = view;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String phoneNumber, String password){
        try {
            User user =loginService.logIn(phoneNumber, password, client);
            System.out.println("user "+ user.getFname() +" has logged in successfully");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
