package gov.iti.jets.model;

import java.io.Serializable;

public record GroupMemberDTO(String firstName, String secondName, String phoneNumber, byte[] profile) implements Serializable {


}
