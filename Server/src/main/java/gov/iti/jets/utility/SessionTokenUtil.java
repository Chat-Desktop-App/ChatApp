package gov.iti.jets.utility;

import gov.iti.jets.model.UserSession;

import java.util.HashMap;
import java.util.UUID;

public class SessionTokenUtil {
    private static HashMap<String, String> sessions = new HashMap<>();
    private static String generate(){
        return UUID.randomUUID().toString();
    }

    public static UserSession addSession(String phoneNumber){
        String sessionToken = generate();
        UserSession session = new UserSession(sessionToken, phoneNumber);
        sessions.put(phoneNumber, sessionToken);

        return  session;
    }

    public static boolean validateSession(UserSession session){
        if(sessions.containsKey(session.getPhoneNumber())){
            if(sessions.get(session.getPhoneNumber()).equals(session.getSessionToken())){
                return true;
            }
        }
        return false;
    }

    public static void removeSession(String phoneNumber){
        sessions.remove(phoneNumber);
    }


}
