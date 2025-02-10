package gov.iti.jets.model;

import javafx.beans.value.ChangeListener;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Chatable extends Serializable {

    LocalDateTime getLastChatAt();
    void setLastChatAt(LocalDateTime lastChatAt);
    byte[] getPicture();
    void setPicture(byte[] picture);
    String getName();
    void setName(String groupName);

    void setLastChatAtListener(ChangeListener<LocalDateTime> changeListener);
}
