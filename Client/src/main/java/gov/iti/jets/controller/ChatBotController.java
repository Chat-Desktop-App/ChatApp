package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.services.interfaces.ChatBot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ChatBotController {
   private static ChatBot service = RMIConnector.getRmiConnector().getChatBot();

    public static String chatWithAI(String userMessage) throws IOException {

        return service.chatWithAI(userMessage, Session.user.getFname());
    }

    public static void main(String[] args) {
        try {
            String incomingMessage = "Hello, how are you?";
            System.out.println("Client received: " + incomingMessage);

            String botReply = chatWithAI(incomingMessage);
            System.out.println("Bot Auto-Reply: " + botReply);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
