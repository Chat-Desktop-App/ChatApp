package gov.iti.jets.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Serializable{
    private String phoneNumber;
    private String fname;
    private String lname;
    private String email;
    private String passwordHashed;
    private Gender gender;
    private String country;
    private LocalDate dob;
    private String bio;
    private Status status = Status.AVAILABLE;
    private Long numberEnteries = 1L;
    private LocalDateTime lastSeen = LocalDateTime.now();
    private Boolean isAdmin = false;
    private transient String picturePath;
    private byte[] picture;




    public User(){}

    public User(String phoneNumber, String fname, String lname, String email, String passwordHashed, Gender gender, String country, LocalDate dob, String bio) {
        this.phoneNumber = phoneNumber;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.passwordHashed = passwordHashed;
        this.gender = gender;
        this.country = country;
        this.dob = dob;
        this.bio = bio;
        this.lastSeen = LocalDateTime.now();
    }

    public User(String phoneNumber, String fname, String lname) {
        this.phoneNumber = phoneNumber;
        this.fname = fname;
        this.lname = lname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNumberEnteries() {
        return numberEnteries;
    }

    public void setNumberEnteries(Long numberEnteries) {
        this.numberEnteries = numberEnteries;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
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
}
