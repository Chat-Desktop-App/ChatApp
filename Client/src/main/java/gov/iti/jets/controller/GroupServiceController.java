package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.CreateGroupDTO;
import gov.iti.jets.model.GroupMemberDTO;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.AddGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;

public class GroupServiceController {
    // all users friends to select from to create a group
   // private static final ObservableList<AddGroup.GroupMemberDTO> usersList = FXCollections.observableArrayList();
    private static AddGroup addGroupService = RMIConnector.getRmiConnector().getAddGroupService();
    private static User user ;

    public static ObservableList<GroupMemberDTO> getUsersList(){
        try {

            user = HomeServiceController.getUser();
            System.out.println(user.getPhoneNumber());
            return FXCollections.observableArrayList(addGroupService.getAllUsers(user.getPhoneNumber()));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createGroup(CreateGroupDTO groupDTO){
        try {
            addGroupService.createGroup(groupDTO);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }





}
