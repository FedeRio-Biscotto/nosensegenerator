//Package
package NoSenGen.controller;

//Import
import NoSenGen.generator.Generator;
import NoSenGen.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class GeneratorController {
    //Variabili
    private final Generator generator;
    private String savedApiKey;

    public GeneratorController(Generator generator) {
        this.generator = generator;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        // Imposta valori massimi come attributi del modello
        model.addAttribute("maxSentences", 20); //(Max frasi da generare)
        return "index";
    }

    @PostMapping("/generate")
    @ResponseBody  // Risposta come JSON invece di una vista tradizionale
    public GeneratorResponse generateSentences(
            @RequestParam(required = false, defaultValue = "") String inputSentence,
            @RequestParam(required = false, defaultValue = "1") int mode,
            @RequestParam(required = false, defaultValue = "0") int totalSentences,
            @RequestParam(required = false, defaultValue = "0") int pastSentences,
            @RequestParam(required = false, defaultValue = "0") int presentSentences,
            @RequestParam(required = false, defaultValue = "0") int futureSentences
    ) {

        try {
            //ApiKey
            if (savedApiKey == null) {
                throw new IllegalArgumentException("[Error]: Api Key is missing");
            }

            // Controlli di validazione per il numero di frasi
            if (totalSentences > 20) {
                throw new IllegalArgumentException("[Error]: Maximum 20 sentences allowed");
            }
            if (pastSentences + presentSentences + futureSentences != totalSentences) {
                throw new IllegalArgumentException("[Error]: Sum of tense-specific sentences must equal total sentences");
            }

            // Inizializza risposta
            GeneratorResponse response = new GeneratorResponse();

            // Genera frasi per ognuno dei tempi verbali
            for (int i = 0; i < pastSentences; i++) {
                response.getPastSentences().add(generator.genSentence(inputSentence, 0, savedApiKey ));
            }
            for (int i = 0; i < presentSentences; i++) {
                response.getPresentSentences().add(generator.genSentence(inputSentence, 1, savedApiKey));
            }
            for (int i = 0; i < futureSentences; i++) {
                response.getFutureSentences().add(generator.genSentence(inputSentence, 2, savedApiKey));
            }

            return response;
        } catch (Exception e) {
            // Gestione dell'errore globale
            throw new RuntimeException("[Error]: generating sentences: " + e.getMessage(), e);
        }
    }

    @PostMapping("/Tree")
    @ResponseBody  // Ritorna la struttura JSON per il Semantic Tree
    public ResponseEntity<Map<String, String>> semanticTree(
            @RequestParam String inputSentence
    ) {
        try {
            // Valida l'API Key
            if (savedApiKey == null) {
                // Crea risposta JSON per errore API key mancante
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("[Error]:", "No API key provided");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Ottiene l'albero semantico dall'API
            String semanticTree = GoogleLanguageAPI.Semantic_Tree(inputSentence, savedApiKey);

            // Migliora il rendering sostituendo "\n" con "<br>"
            String semanticTreeWithBr = semanticTree.replace("\n", "<br>");

            // Invia JSON con la risposta formattata
            Map<String, String> response = new HashMap<>();
            response.put("semanticTree", semanticTreeWithBr);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Errore non previsto
            throw new RuntimeException("[Error]: Error generating semantic tree: " + e.getMessage(), e);
        }
    }

    @PostMapping("/getTheKey")
    @ResponseBody
    public ResponseEntity<String> apiKeyfunction(@RequestParam String apiKey) {
        try {
            // Valida la chiave API
            if (apiKey == null || apiKey.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("API key non valida");
            }

            // Salva la API Key e restituisci un messaggio di conferma
            this.savedApiKey = apiKey;
            return ResponseEntity.ok("API key saved successfully");
        } catch (Exception e) {
            // Gestione dell'errore
            throw new RuntimeException("[Error]: " + e.getMessage(), e);
        }
    }

    // Classe statica interna per la struttura delle risposte JSON
    public static class GeneratorResponse {
        private List<String> pastSentences = new ArrayList<>();
        private List<String> presentSentences = new ArrayList<>();
        private List<String> futureSentences = new ArrayList<>();

        // Getters e Setters per ogni lista di frasi
        public List<String> getPastSentences() { return pastSentences; }
        public List<String> getPresentSentences() { return presentSentences; }
        public List<String> getFutureSentences() { return futureSentences; }

    }
}