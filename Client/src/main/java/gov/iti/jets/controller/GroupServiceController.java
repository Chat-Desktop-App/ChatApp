package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.*;
import gov.iti.jets.services.interfaces.AddGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class GroupServiceController {
    private static AddGroup addGroupService = RMIConnector.getRmiConnector().getAddGroupService();
    private static User user;

    public static ObservableList<GroupMemberDTO> getUsersList() {
        try {

            user = HomeServiceController.getUser();
            System.out.println(user.getPhoneNumber());
            return FXCollections.observableArrayList(addGroupService.getAllUsers(user.getPhoneNumber()));

        } catch (RemoteException e) {
            addGroupService = RMIConnector.rmiReconnect().getAddGroupService();
            throw new RuntimeException(e);
        }
    }


    public static void createGroup(CreateGroupDTO groupDTO) {


        try {
            Group group = addGroupService.createGroup(groupDTO);
            List<GroupMemberDTO> members = groupDTO.members();
            for (GroupMemberDTO member : members) {
                Notifications notifications = new Notifications(member.phoneNumber(), group.getAdminId(), "You have been added to " + groupDTO.groupName() + " group", LocalDateTime.now(), false, Notification.ADDTOGROUP);
                NotificationServiceController.addNotification(notifications);
            }

        } catch (RemoteException e) {
            addGroupService = RMIConnector.rmiReconnect().getAddGroupService();
            throw new RuntimeException(e);
        }
    }
}
