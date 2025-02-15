package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.services.interfaces.ChatBot;

import java.io.IOException;

public class ChatBotController {
    private static final ChatBot service = RMIConnector.getRmiConnector().getChatBot();
    private static boolean aiActivated = false;


    public static String chatWithAI(String userMessage) throws IOException {

        return service.chatWithAI(userMessage, Session.user.getFname());
    }

    public static boolean isAiActivated() {
        return aiActivated;
    }

    public static void setAiActivated(boolean aiActivated) {
        ChatBotController.aiActivated = aiActivated;
    }
}
