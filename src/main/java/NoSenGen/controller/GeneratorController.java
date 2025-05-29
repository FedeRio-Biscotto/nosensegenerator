//Package
package NoSenGen.controller;

//Import
import NoSenGen.generator.Generator;
import NoSenGen.api.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GeneratorController {
    private final Generator generator;

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
            @RequestParam(required = false) String inputSentence,
            @RequestParam int mode,
            @RequestParam int totalSentences,
            @RequestParam int pastSentences,
            @RequestParam int presentSentences,
            @RequestParam int futureSentences
    ) {
        try {
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
                response.getPastSentences().add(generator.genSentence(sentence, 0));
            }
            for (int i = 0; i < presentSentences; i++) {
                response.getPresentSentences().add(generator.genSentence(sentence, 1));
            }
            for (int i = 0; i < futureSentences; i++) {
                response.getFutureSentences().add(generator.genSentence(sentence, 2));
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
            // Chiama il metodo Semantic_Tree della tua classe GoogleLanguageAPI
            String semanticTree = GoogleLanguageAPI.Semantic_Tree(inputSentence);

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

    // Classe interna per la risposta JSON
    public static class GeneratorResponse {
        private List<String> pastSentences = new ArrayList<>();
        private List<String> presentSentences = new ArrayList<>();
        private List<String> futureSentences = new ArrayList<>();

        // Getters e setters
        public List<String> getPastSentences() { return pastSentences; }
        public List<String> getPresentSentences() { return presentSentences; }
        public List<String> getFutureSentences() { return futureSentences; }
    }
}