import java.util.ArrayList;
import org.json.*;
//Compilare con: javac -cp ".;json-20250107.jar" test_response.java
//Eseguire con: java -cp ".;json-20250107.jar" test_response.java

public class test_response {
    public static void main(String[] args) throws Exception {
        //Variabili
        ArrayList<String> nouns = new ArrayList<>();
        ArrayList<String> verbs = new ArrayList<>();
        ArrayList<String> adj = new ArrayList<>();

        // Risposta JSON simulata della API
        String jsonResponse = "{\n" +
                            "  \"sentences\": [\n" +
                            "    {\n" +
                            "      \"text\": {\n" +
                            "        \"content\": \"I am a pretty sentence.\",\n" +
                            "        \"beginOffset\": -1\n" +
                            "      }\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"tokens\": [\n" +
                            "    {\n" +
                            "      \"text\": { \"content\": \"I\", \"beginOffset\": -1 },\n" +
                            "      \"partOfSpeech\": { \"tag\": \"PRON\", \"case\": \"NOMINATIVE\", \"number\": \"SINGULAR\", \"person\": \"FIRST\" },\n" +
                            "      \"dependencyEdge\": { \"headTokenIndex\": 1, \"label\": \"NSUBJ\" },\n" +
                            "      \"lemma\": \"I\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"text\": { \"content\": \"am\", \"beginOffset\": -1 },\n" +
                            "      \"partOfSpeech\": { \"tag\": \"VERB\", \"mood\": \"INDICATIVE\", \"number\": \"SINGULAR\", \"person\": \"FIRST\", \"tense\": \"PRESENT\" },\n" +
                            "      \"dependencyEdge\": { \"headTokenIndex\": 1, \"label\": \"ROOT\" },\n" +
                            "      \"lemma\": \"be\"\n" +
                            "    },\n" +
                            "    { \"text\": { \"content\": \"a\", \"beginOffset\": -1 }, \"partOfSpeech\": { \"tag\": \"DET\" }, \"dependencyEdge\": { \"headTokenIndex\": 4, \"label\": \"DET\" }, \"lemma\": \"a\" },\n" +
                            "    { \"text\": { \"content\": \"pretty\", \"beginOffset\": -1 }, \"partOfSpeech\": { \"tag\": \"ADJ\" }, \"dependencyEdge\": { \"headTokenIndex\": 4, \"label\": \"AMOD\" }, \"lemma\": \"pretty\" },\n" +
                            "    { \"text\": { \"content\": \"sentence\", \"beginOffset\": -1 }, \"partOfSpeech\": { \"tag\": \"NOUN\", \"number\": \"SINGULAR\" }, \"dependencyEdge\": { \"headTokenIndex\": 1, \"label\": \"ATTR\" }, \"lemma\": \"sentence\" },\n" +
                            "    { \"text\": { \"content\": \".\", \"beginOffset\": -1 }, \"partOfSpeech\": { \"tag\": \"PUNCT\" }, \"dependencyEdge\": { \"headTokenIndex\": 1, \"label\": \"P\" }, \"lemma\": \".\" }\n" +
                            "  ],\n" +
                            "  \"language\": \"en\"\n" +
                            "}";


        // Parsing del JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray tokens = jsonObject.getJSONArray("tokens");

        // Cicla sui token
        for (int i = 0; i < tokens.length(); i++) {
            JSONObject token = tokens.getJSONObject(i);
            String word = token.getJSONObject("text").getString("content");
            String posTag = token.getJSONObject("partOfSpeech").getString("tag");

            // // Stampa la parola e il suo tipo di parte del discorso
            // System.out.println("Word: " + word + " | Part of Speech: " + posTag);

            //Salva le parole in array
            switch (posTag) {
            case "PRON":
                nouns.add(word);
                break;
            case "NOUN":
                nouns.add(word);
                break;
            case "VERB":
                verbs.add(word);
                break;
            case "ADJ":
                adj.add(word);
                break;
            }
        }


        //Debug: Stampo gli array  
        System.out.println("Nomi: " + nouns);
        System.out.println("Verbi: " + verbs);
        System.out.println("Aggettivi: " + adj);


    }
}
