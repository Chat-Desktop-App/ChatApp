package gov.iti.jets.model;

public class Chats {
    private int id;
    private String senderNumber;
    private String receievrNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getReceievrNumber() {
        return receievrNumber;
    }

    public void setReceievrNumber(String receievrNumber) {
        this.receievrNumber = receievrNumber;
    }
}
