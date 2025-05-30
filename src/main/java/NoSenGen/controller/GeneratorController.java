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
    private static boolean error;
    private static String message;



    public GeneratorController(Generator generator) {

        this.generator = generator;
    }


    @GetMapping("/")
    public String showHomePage(Model model) {
        // Aggiungiamo alcuni attributi per gestire i limiti
        model.addAttribute("maxSentences", 20);
        return "index";
    }

    @PostMapping("/generate")
    @ResponseBody  // Questo ci permette di restituire JSON invece di una vista
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
                // Crea un'istanza di GeneratorResponse per l'errore
                GeneratorResponse errorResponse = new GeneratorResponse();
                errorResponse.setError(true); // Imposta il flag di errore
                errorResponse.setMessage("[Error]: No API key provided"); // Messaggio di errore
                //errorResponse.setDetails("L'API key non è stata fornita. Assicurati di inserirla e riprova."); // Dettagli aggiuntivi

                // Restituisci l'errore incapsulato in un ResponseEntity
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse).getBody();
            }

            // Validazione
            if (totalSentences > 20) {
                throw new IllegalArgumentException("[Error]: Maximum 20 sentences allowed");
            }
            if (pastSentences + presentSentences + futureSentences != totalSentences) {
                throw new IllegalArgumentException("[Error]: Sum of tense-specific sentences must equal total sentences");
            }

            GeneratorResponse response = new GeneratorResponse();

            // Se mode è 2, impostiamo inputSentence come stringa vuota
            String sentence = (mode == 2) ? "" : inputSentence;

            // Genera le frasi per ogni tempo verbale
            for (int i = 0; i < pastSentences; i++) {
                response.getPastSentences().add(generator.genSentence(sentence, 0, savedApiKey ));
            }
            for (int i = 0; i < presentSentences; i++) {
                response.getPresentSentences().add(generator.genSentence(sentence, 1, savedApiKey));
            }
            for (int i = 0; i < futureSentences; i++) {
                response.getFutureSentences().add(generator.genSentence(sentence, 2, savedApiKey));
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException("[Error]: generating sentences: " + e.getMessage(), e);
        }
    }

    @PostMapping("/Tree")
    @ResponseBody  // Questo ci permette di restituire JSON invece di una lista
    public ResponseEntity<Map<String, String>> semanticTree(
            @RequestParam String inputSentence
    ) {
        try {
            //ApiKey
            if(savedApiKey==null){
                // Creazione della mappa con il messaggio di errore
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("[Error]:", " No API key provided");

                // Restituisci l'errore come ResponseEntity
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            // Chiama il metodo Semantic_Tree della tua classe GoogleLanguageAPI
            String semanticTree = GoogleLanguageAPI.Semantic_Tree(inputSentence, savedApiKey);

            // Sostituisci i '\n' con '<br>' per il rendering HTML
            String semanticTreeWithBr = semanticTree.replace("\n", "<br>");

            // Crea una mappa con i dati in formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("semanticTree", semanticTreeWithBr);

            // Restituisci una risposta HTTP con un oggetto JSON
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            throw new RuntimeException("[Error]: generating sentences: " + e.getMessage(), e);
        }
    }


    @PostMapping("/getTheKey")
    @ResponseBody
    public ResponseEntity<String> apiKeyfunction(@RequestParam String apiKey) {
        try {
            if (apiKey == null || apiKey.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(" API key non valida");
            }
            // Salva la API key nel campo savedApiKey
            this.savedApiKey = apiKey;

            //[DEBUG]
            //System.out.println("API key salvata correttamente: " + savedApiKey); // Debug

            return ResponseEntity.ok("API key salvata correttamente");
        } catch (Exception e) {
            throw new RuntimeException("[Error]: Errore durante il salvataggio della API key: " + e.getMessage(), e);
        }
    }

    // Classe interna per la risposta JSON
    public static class GeneratorResponse {
        private List<String> pastSentences = new ArrayList<>();
        private List<String> presentSentences = new ArrayList<>();
        private List<String> futureSentences = new ArrayList<>();

        // Getters e setters
        public List<String> getPastSentences() { return pastSentences; }
        public List<String> getPresentSentences() { return presentSentences; }
        public List<String> getFutureSentences() { return futureSentences; }

        public void setError(boolean b) {
            error = b;
        }

        public void setMessage(String s) {
            message = s;
        }
    }
}