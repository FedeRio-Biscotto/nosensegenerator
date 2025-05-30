//Package
package NoSenGen;

//Import
import java.awt.*;
import java.net.URI;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import NoSenGen.generator.Generator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    //Definizione di un bean per la classe Generator
    @Bean
    public Generator generator() {
        return new Generator();
    }

    //Configurazione di un ApplicationRunner per avviare attività dopo l'avvio dell'applicazione
    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            try {
                //Controlla se il sistema supporta l'apertura di un browser e, se possibile, apre automaticamente la pagina
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("http://localhost:8080")); // Apre il browser all'indirizzo specifico
                } else {
                    //Messaggio in caso non sia possibile aprire automaticamente il browser
                    System.out.println("[System]: Welcome to NoSenseGenerator! :D \n [System]: Please open http://localhost:8080 in your web browser");
                }
            } catch (Exception e) {
                //Messaggio di fallback in caso di errore
                System.out.println("[System]: Welcome to NoSenseGenerator! :D \n [System]: Please open http://localhost:8080 in your web browser");
            }
        };
    }

    //Metodo principale: Avvia l'applicazione Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}