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
            addGroupService.createGroup(groupDTO);
            // add it to the session
            //session.mylastChat
            // send notification to all memember of the group

            try {
                String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
                FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                ChatsController controller = loader.getController();

                //chatsControllerMap.put(String.valueOf(((Group)chatable).getGroupId()), controller);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //add to chatable map
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }





}
