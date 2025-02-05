package gov.iti.jets.model;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ContactUser implements  Chatable {
    @Serial
    private static final long serialVersionUID = 5677958496409793424L;
    private String phoneNumber;
    private String name;
    private String fname;
    private String lname;
    private Status status; // available, busy, away
    private transient String picturePath;
    private byte[] picture;
    private LocalDateTime lastChatAt;



    public ContactUser(String phoneNumber, String fname, String lname, Status status, String picturePath) {
        this.phoneNumber = phoneNumber;
        this.fname = fname;
        this.lname = lname;
        name = fname + " " + lname;
        this.status = status;
        this.picturePath = picturePath;
    }

    @Override
    public LocalDateTime getLastChatAt() {
        return lastChatAt;
    }

    public void setLastChatAt(LocalDateTime lastChatAt) {
        this.lastChatAt = lastChatAt;
    }

    public ContactUser(String phoneNumber, String fname, String lname, Status status, LocalDateTime lastChatAt) {
        this.phoneNumber = phoneNumber;
        this.fname = fname;
        this.lname = lname;
        name = fname + " " + lname;
        this.status = status;
        this.lastChatAt = lastChatAt;
    }

    public ContactUser() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContactUser{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", status=" + status +
                '}';
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
