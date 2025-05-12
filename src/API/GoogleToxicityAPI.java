//Package
package api;
//Import
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.*;
//Import dei nostri pacchetti
import dictionary.*;

//DEBUG
//NON SERVE PIU' COMPILARE COSI': bisogna usare Git Bash e il file .sh, e' tutto scritto la' dentro, lo lascio solo come Debug
//Compilare con: javac -cp ".;json-20250107.jar" GoogleToxicityAPI.java
//Eseguire con: java -cp ".;json-20250107.jar" GoogleToxicityAPI.java

public class GoogleToxicityAPI {
    public static void main(String[] args) throws Exception {
        // //Variabili
        // ArrayList<MyNoun> nouns = new ArrayList<>();
        // ArrayList<MyVerb> verbs = new ArrayList<>();
        // ArrayList<MyAdjective> adj = new ArrayList<>();

        // //API Key
        // String apiKey = "AIzaSyCnUvmTiz84QCIpInKTtlufK7TXMzL2rZg"; //Chiave Fede
        
        // //Endpoint dell'API
        // String url = "https://language.googleapis.com/v1/documents:analyzeSyntax?key=" + apiKey;
        
        // //Testo JSON per la richiesta
        // String requestBody = "{\n" +
        //         "  \"document\": {\n" +
        //         "    \"type\": \"PLAIN_TEXT\",\n" +
        //         "    \"content\": \"I am a pretty sentence and Giulia loves eating dogs\"\n" +
        //         "  }\n" +
        //         "}";
        
        // //Crea un client HTTP
        // HttpClient client = HttpClient.newHttpClient();

        // //Crea una richiesta HTTP POST
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(URI.create(url))
        //         .header("Content-Type", "application/json")
        //         .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
        //         .build();

        // //Invia la richiesta
        // HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // //Gestione degli errori
        // if (response.statusCode() != 200) {
        //     System.out.println("Error: " + response.statusCode());
        //     System.out.println("Details: " + response.body());
        // }

        // // //Stampa la risposta raw
        // // System.out.println("Response Code: " + response.statusCode());
        // // System.out.println("Response Body: " + response.body());

        // //La risposta JSON dell'api la metto in una stringa
        // String jsonResponse = response.body();

        // //Parsing del JSON
        // JSONObject jsonObject = new JSONObject(jsonResponse);
        // JSONArray tokens = jsonObject.getJSONArray("tokens");

        // //Estrapola i tokens
        // for (int i = 0; i < tokens.length(); i++) {
        //     JSONObject token = tokens.getJSONObject(i);
        //     String word = token.getJSONObject("text").getString("content");
        //     String posTag = token.getJSONObject("partOfSpeech").getString("tag");

        //     //Salva le parole in array
        //     switch (posTag) {
        //     case "PRON":
        //         nouns.add(word);
        //         break;
        //     case "NOUN":
        //         nouns.add(word);
        //         break;
        //     case "VERB":
        //         verbs.add(word);
        //         break;
        //     case "ADJ":
        //         adj.add(word);
        //         break;
        //     }
        // }


        // //Debug: Stampo gli array  
        // System.out.println("Nomi: " + nouns);
        // System.out.println("MyVerbi: " + verbs);
        // System.out.println("Aggettivi: " + adj);


    }
    

    
}

