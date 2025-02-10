package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.*;
import gov.iti.jets.services.interfaces.AddGroup;
import gov.iti.jets.view.ChatsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;

import static gov.iti.jets.controller.Session.chatsControllerMap;

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
                Group group = addGroupService.createGroup(groupDTO);

                HomeServiceController.addToLastContactList(group);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            // add it to the session
            //session.mylastChat
            // send notification to all memember of the group





    }





}
