package gov.iti.jets.model;

public class UserSession {

    private static UserSession instance;


    private String phoneNumber;
    private boolean isLoggedIn;


    private UserSession() {

        this.phoneNumber = "-1"; // indicates no user is logged in
        this.isLoggedIn = false;
    }

    // the singleton instance
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void clearSession() {
        this.phoneNumber = "-1";
        this.isLoggedIn = false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}