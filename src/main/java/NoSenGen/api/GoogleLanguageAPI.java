//Package
package NoSenGen.api;
//Import
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//Import dei nostri pacchetti
import NoSenGen.myDictionary.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleLanguageAPI {

    //Variabili
    private static ArrayList<MyNoun> nouns = new ArrayList<>();
    private static ArrayList<MyVerb> verbs = new ArrayList<>();
    private static ArrayList<MyVerb> verbs_thirdperson = new ArrayList<>();
    private static ArrayList<MyVerb> verbs_past = new ArrayList<>();
    private static ArrayList<MyAdjective> adj = new ArrayList<>();

    public static void languageApi(String sentence, String apiKey) throws Exception {
        // Reinizializza le liste prima di ogni nuova analisi
        nouns.clear();
        verbs.clear();
        verbs_thirdperson.clear();
        verbs_past.clear();
        adj.clear();

        //Parsing del JSON
        JSONObject jsonObject = new JSONObject(callAPI(sentence, apiKey));
        JSONArray tokens = jsonObject.getJSONArray("tokens");

        //Estrapola i tokens
        for (int i = 0; i < tokens.length(); i++) {
            JSONObject token = tokens.getJSONObject(i);
            String word = token.getJSONObject("text").getString("content");
            String posTag = token.getJSONObject("partOfSpeech").getString("tag");

            String person = token.getJSONObject("partOfSpeech").getString("person");
            String temp = token.getJSONObject("partOfSpeech").getString("tense");

            //Salva le parole in array
            switch (posTag) {
            case "PRON":
                nouns.add(new MyNoun(word));
                break;
            case "NOUN":
                nouns.add(new MyNoun(word));
                break;
            case "VERB":
                if(person.equals("THIRD")){
                    verbs_thirdperson.add(new MyVerb(word));
                } else if (temp.equals("PAST")){
                    verbs_past.add(new MyVerb(word));
                } else {
                    verbs.add(new MyVerb(word));
                }
                break;
            case "ADJ":
                adj.add(new MyAdjective(word));
                break;
                default: break;
            }

        }

    }

    public static String semanticTree(String sentence, String apiKey) throws Exception{
        //Variabili
        String s = "";
        String response = callAPI(sentence, apiKey);

        //Parsing del JSON
        JSONObject jsonObject = new JSONObject(response);
        JSONArray tokens = jsonObject.getJSONArray("tokens");

        //Estrapola e salva i tokens in una stringa
        for (int i = 0; i < tokens.length(); i++) {
            JSONObject token = tokens.getJSONObject(i);
            s = s + token.getJSONObject("text").getString("content") + " | " + token.getJSONObject("partOfSpeech").getString("tag") + "\n";
        }

        //Ritorna la stringa con i token
        return s;
    }

    //Metodo per chiamare l'API
    protected static String callAPI(String sentence, String apiKey) throws Exception{
        //API Key presa in automatico dal form html

        //Endpoint dell'API
        String url = "https://language.googleapis.com/v1/documents:analyzeSyntax?key=" + apiKey;

        //Testo JSON per la richiesta
        String requestBody = "{\n" +
                "  \"document\": {\n" +
                "    \"type\": \"PLAIN_TEXT\",\n" +
                "    \"content\": \"" + sentence + "\"\n" +
                "  },\n" +
                "  \"encodingType\": \"UTF8\"\n" +
                "}";

        //Crea un client HTTP
        HttpClient client = HttpClient.newHttpClient();

        //Crea una richiesta HTTP POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        //Invia la richiesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Gestione degli errori
        if (response.statusCode() != 200) {
            System.out.println("[Error]: " + response.statusCode());
            System.out.println("[Details]: " + response.body());
        }

        String jsonResponse = response.body();

        //Return
        return jsonResponse;
    }

    //metodi get
    public static ArrayList<MyNoun> getNouns() {
        return nouns;
    }

    public static ArrayList<MyVerb> getVerbs() {
        return verbs;
    }

    public static ArrayList<MyVerb> getVerbs_thirdperson() {
        return verbs_thirdperson;
    }

    public static ArrayList<MyVerb> getVerbs_past() {
        return verbs_past;
    }

    public static ArrayList<MyAdjective> getAdj() {
        return adj;
    }
    
}
