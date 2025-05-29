package NoSenGen;
import java.awt.*;
import java.net.URI;
import java.util.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import NoSenGen.generator.Generator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

static Generator g =new Generator();


    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            try {
                // Check if Desktop is supported
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("http://localhost:8080"));
                } else {
                    System.out.println("[System]: Welcome to NoSenseGenerator! :D \n [System]: Please open http://localhost:8080 in your web browser");
                }
            } catch (Exception e) {
                System.out.println("[System]: Welcome to NoSenseGenerator! :D \n [System]: Please open http://localhost:8080 in your web browser");
            }
        };
    }



    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

}



