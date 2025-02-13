package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.services.interfaces.ChatBot;

import java.io.IOException;

public class ChatBotController {
    private static ChatBot service = RMIConnector.getRmiConnector().getChatBot();
    private static boolean aiActivated = false;


    public static String chatWithAI(String userMessage) throws IOException {

        return service.chatWithAI(userMessage, Session.user.getFname());
    }

    public static void main(String[] args) {

        String incomingMessage = "Hello, how are you?";
        System.out.println("Client received: " + incomingMessage);

        //String botReply = chatWithAI(incomingMessage);
        //System.out.println("Bot Auto-Reply: " + botReply);


    }

    public static boolean isAiActivated() {
        return aiActivated;
    }

    public static void setAiActivated(boolean aiActivated) {
        ChatBotController.aiActivated = aiActivated;
    }
}
