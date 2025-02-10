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
import java.time.LocalDateTime;
import java.util.List;

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
                // create in db
                Group group = addGroupService.createGroup(groupDTO);
                // add it to the session
                HomeServiceController.addToLastContactList(group);
                // send notification to all memember of the group
                List<GroupMemberDTO> members = groupDTO.members();
                for(GroupMemberDTO member : members){
                    Notifications notifications = new Notifications(member.phoneNumber(), group.getAdminId(), "You have been added to "+groupDTO.groupName()+" group", LocalDateTime.now(),false,Notification.ADDTOGROUP);
                    NotificationServiceController.addNotification(notifications);
                }



            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }








    }





}
