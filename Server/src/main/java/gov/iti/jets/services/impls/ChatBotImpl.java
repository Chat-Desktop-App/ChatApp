package gov.iti.jets.services.impls;

import gov.iti.jets.services.interfaces.ChatBot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatBotImpl extends UnicastRemoteObject implements ChatBot {
    private static final String OPENROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String API_KEY = "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";
    private static final String MODEL_NAME = "mistralai/Mistral-7B-Instruct";
    public ChatBotImpl() throws RemoteException {
    }

    @Override
    public String chatWithAI(String userMessage, String fname) throws  RemoteException {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("model", MODEL_NAME);


        JSONArray messagesArray = new JSONArray();
        messagesArray.put(new JSONObject().put("role", "system").put("content",
                "You are "+fname+" , a real human chatting with your friends. Respond naturally and casually, as you would in a real conversation, keep it short."));

        messagesArray.put(new JSONObject().put("role", "user").put("content", userMessage));

        jsonRequest.put("messages", messagesArray);
        jsonRequest.put("max_tokens", 20);
        jsonRequest.put("temperature", 0.5);


        URL url = null;
        try {
            url = new URL(OPENROUTER_API_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);


        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonRequest.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder response = new StringBuilder();
        String responseLine;
        while (true) {
            try {
                if (!((responseLine = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(responseLine.trim());
        }

        // Parse JSON Response
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray choices = jsonResponse.getJSONArray("choices");

        if (choices.length() > 0) {
            return choices.getJSONObject(0).getJSONObject("message").getString("content");
        } else {
            return "No response from OpenRouter AI!";
        }
    }
}
