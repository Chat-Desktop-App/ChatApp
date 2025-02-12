package gov.iti.jets.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Announcements implements Serializable {
    private String message;
    private Timestamp timestamp;

    public Announcements(String message, Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
