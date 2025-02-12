package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.Register;
import gov.iti.jets.view.SignUpController;

import java.rmi.RemoteException;

public class RegisterServiceController {
    private SignUpController view;
    private Register registerService;

    public RegisterServiceController(SignUpController view){
        registerService = (Register) RMIConnector.getRmiConnector().getRegisterService();
        this.view = view;
        // add callbacks later


    }

    public boolean signUp(User user){
        try {
            if(registerService.SignUp(user) == null){
                return false;
            }

            System.out.println("User: "+user.getFname()+" added successfully");

        } catch (RemoteException e) {
            registerService = RMIConnector.rmiReconnect().getRegisterService();
            throw new RuntimeException(e);
        }

        return true;
    }



}
