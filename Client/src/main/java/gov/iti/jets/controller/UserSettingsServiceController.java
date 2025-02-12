package gov.iti.jets.controller;



import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Status;
import gov.iti.jets.services.interfaces.UserSettingsService;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.rmi.RemoteException;

public class UserSettingsServiceController {
    private static UserSettingsService userSettingsService = RMIConnector.getRmiConnector().getUserSettingsService();



    public static boolean updateProfilePicture(byte [] pic){
        try {
            boolean flag = userSettingsService.UpdatePicture(Session.user.getPhoneNumber(),pic);
            HomeServiceController.getHomeController().setPictureIcon(new Image(new ByteArrayInputStream(pic)));
            Session.user.setPicture(pic);
            return flag;
        } catch (RemoteException e) {
            System.out.println("can't connect to server");
            e.printStackTrace();
        }
        return false;
    }
    public static void updateProfileStatus(Status status){
        try {
            userSettingsService.UpdateStatus(Session.user.getPhoneNumber(),status);
            Session.user.setStatus(status);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileEmail(String email){
        try {
            userSettingsService.UpdateEmail(Session.user.getPhoneNumber(),email);
            Session.user.setEmail(email);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileBio(String Bio){
        try {
            userSettingsService.UpdateBio(Session.user.getPhoneNumber(),Bio);
            Session.user.setBio(Bio);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileName(String fullName){
        try {
            userSettingsService.UpdateFullName(Session.user.getPhoneNumber(),fullName);
            String[] nameParts = fullName.trim().split("\\s+"); // Split by spaces

            String firstName = nameParts[0]; // First word
            String lastName = (nameParts.length > 1) ? nameParts[nameParts.length - 1] : "";
            Session.user.setFname(firstName);
            Session.user.setLname(lastName);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
