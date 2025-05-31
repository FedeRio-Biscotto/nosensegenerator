//Package
package NoSenGen.template;
//Import
import java.util.*;
import java.io.*;
import java.security.SecureRandom;

//Questa classe crea gli oggetti template
public class TemplatesLibrary{

    //Variabili
    static ArrayList<Template> templates = new ArrayList<>();

    static {
        templates = templateAdder("src/main/resources/templates.txt");
        if (templates.isEmpty()) {
        System.err.println("[Error]: No templates found in templates.txt");
        }

    }

    //FileReader e Crea template
    private static ArrayList<Template> templateAdder(String filenoun){

        try (BufferedReader reader = new BufferedReader(new FileReader(filenoun))) {
            String riga;

            while ((riga = reader.readLine()) != null) {
                templates.add(new Template(riga));

            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
        }

        return templates;
    }

    //Metodo RandomPicker
    public static Template randomTemplatePicker(){
        //Creo un numero randoom
        SecureRandom rand = new SecureRandom();
        return templates.get(rand.nextInt(templates.size()));

    }
}