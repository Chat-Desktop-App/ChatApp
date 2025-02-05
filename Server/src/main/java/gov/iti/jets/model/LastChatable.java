package gov.iti.jets.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface LastChatable extends Serializable {

    LocalDateTime getLastChatAt();
    void setLastChatAt(LocalDateTime lastChatAt);
    byte[] getPicture();
    void setPicture(byte[] picture);
    String getName();
    void setName(String groupName);
}
