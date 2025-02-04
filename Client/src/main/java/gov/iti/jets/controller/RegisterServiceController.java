package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.Register;
import gov.iti.jets.view.SignUpController;

import java.rmi.RemoteException;

public class RegisterServiceController {
    private final SignUpController view;
    private final Register registerService;

    public RegisterServiceController(SignUpController view) {
        registerService = RMIConnector.getRmiConnector().getRegisterService();
        this.view = view;
        // add callbacks later


    }

    public void signUp(User user, byte[] selectedImageBytes) {
        try {
            registerService.SignUp(user, selectedImageBytes);
            System.out.println("User: " + user.getFname() + " added successfully");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }


}
