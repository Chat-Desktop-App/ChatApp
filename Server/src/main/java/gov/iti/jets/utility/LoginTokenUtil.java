package gov.iti.jets.utility;

import gov.iti.jets.model.LoginStatus;

import java.util.HashMap;
import java.util.UUID;

public class LoginTokenUtil {
    private static HashMap<String, String> sessions = new HashMap<>();
    private static String generate(){
        return UUID.randomUUID().toString();
    }

    public static LoginStatus addSession(String phoneNumber){
        String sessionToken = generate();
        LoginStatus session = new LoginStatus(sessionToken, phoneNumber);
        sessions.put(phoneNumber, sessionToken);

        return  session;
    }

    public static boolean validateSession(LoginStatus session){
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
