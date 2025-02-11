package gov.iti.jets.model;

import java.io.Serializable;
import java.util.List;

public record CreateGroupDTO (
        String groupName,
        String adminPhoneNumber,
        byte[] groupPicture,
        List<GroupMemberDTO> members
) implements Serializable {}
