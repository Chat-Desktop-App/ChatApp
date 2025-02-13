package gov.iti.jets.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"phoneNumber", "sessionToken"})
public class LoginStatus implements Serializable {
    String sessionToken;
    String phoneNumber;


    public LoginStatus() {
    }

    public LoginStatus(String sessionToken, String phoneNumber) {
        this.sessionToken = sessionToken;
        this.phoneNumber = phoneNumber;

    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}