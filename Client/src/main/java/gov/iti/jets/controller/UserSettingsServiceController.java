package gov.iti.jets.controller;



import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Status;
import gov.iti.jets.services.interfaces.UserSettingsService;

import java.rmi.RemoteException;

public class UserSettingsServiceController {
    private static UserSettingsService userSettingsService = (UserSettingsService) RMIConnector.getRmiConnector().getUserSettingsService();



    public static void updateProfilePicture(String PicturePath){
        try {
            userSettingsService.UpdatePicture(Session.user.getPhoneNumber(),PicturePath);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileStatus(Status status){
        try {
            userSettingsService.UpdateStatus(Session.user.getPhoneNumber(),status);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileEmail(String email){
        try {
            userSettingsService.UpdateEmail(Session.user.getPhoneNumber(),email);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileBio(String Bio){
        try {
            userSettingsService.UpdateBio(Session.user.getPhoneNumber(),Bio);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateProfileName(String fullName){
        try {
            userSettingsService.UpdateFullName(Session.user.getPhoneNumber(),fullName);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
