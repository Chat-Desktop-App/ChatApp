package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.CreateGroupDTO;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.GroupMemberDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AddGroup extends Remote {
    public List<GroupMemberDTO> getAllUsers(String phoneNumber) throws RemoteException;

    public Group createGroup(CreateGroupDTO group) throws RemoteException;


}
