package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDao;
import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.GroupDao;
import gov.iti.jets.database.dao.GroupDaoImpl;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.CreateGroupDTO;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.GroupMemberDTO;
import gov.iti.jets.services.interfaces.AddGroup;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.utility.PictureUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AddGroupImpl extends UnicastRemoteObject implements AddGroup {
    private static ContactDao contactDao = new ContactDaoImpl();
    private static GroupDao groupDao = new GroupDaoImpl();



    public AddGroupImpl() throws RemoteException {

    }

    @Override
    public List<GroupMemberDTO> getAllUsers(String phoneNumber) throws RemoteException {

        try {
            List<ContactUser> users = contactDao.getFriendsContacts(phoneNumber);
             return users.stream().map(user -> new GroupMemberDTO(user.getFname(), user.getLname(), user.getPhoneNumber(), user.getPicture())).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Group createGroup(CreateGroupDTO groupDTO) throws RemoteException {

        Group group = new Group(groupDTO.groupName(), groupDTO.adminPhoneNumber(), groupDTO.groupPicture());
        Group resGroup = null;
        try {
            // return group id
             int group_id = groupDao.addGroup(group);
            if(groupDTO.groupPicture() != null && groupDTO.groupPicture().length > 0){
                groupDao.updateGroupPicture(group_id, groupDTO.groupPicture());
            }
            if(group_id != -1){
                resGroup = groupDao.getGroupByID(group_id);
                groupDao.addGroupMember(group_id, groupDTO.adminPhoneNumber());
                List<GroupMemberDTO> members = groupDTO.members();
                HashMap<String, ChatClient> online = LoginImpl.getOnlineClients();
                if(online.containsKey(groupDTO.adminPhoneNumber())){
                    System.out.println("admin is online");
                    ChatClient client = online.get(groupDTO.adminPhoneNumber());
                    client.addToLastContactList(resGroup);
                }
                for(GroupMemberDTO member: members){
                    // add callback here
                    if(online.containsKey(member.phoneNumber())){
                        ChatClient client = online.get(member.phoneNumber());
                        System.out.println("client "+ member.phoneNumber()+" is online");
                        client.addToLastContactList(resGroup);
                    }
                    groupDao.addGroupMember(group_id, member.phoneNumber());
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  resGroup;

    }
}
