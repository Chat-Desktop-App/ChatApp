package gov.iti.jets.model;

public class ContactUser {
    private String phoneNumber;
    private String fname;
    private String lname;
    private Status status;


    public ContactUser(String phoneNumber, String fname, String lname, Status status) {
        this.phoneNumber = phoneNumber;
        this.fname = fname;
        this.lname = lname;
        this.status = status;
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

}
