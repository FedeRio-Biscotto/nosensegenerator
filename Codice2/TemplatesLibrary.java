//Import
import java.util.*;
import java.io.*;
import java.security.SecureRandom;

//Questa classe crea gli oggetti template
public class TemplatesLibrary{
    //Variabili
    static ArrayList<Template> templates = new ArrayList<>();

    static {
        templates = TemplateAdder("templates.txt");

        // //Debug: Stampo i template ricevuti
        // for(int i = 0; i< templates.size(); i++){
        //     System.out.println(i + ": " + templates.get(i).getTemplate());
        // }

    }

    //FileReader e Crea template
    private static ArrayList<Template> TemplateAdder(String filename){

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
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
    public static Template RandomTemplatePicker(){
        //Creo un numero randoom
        SecureRandom rand = new SecureRandom();
        int result = 0;

        result = rand.nextInt(templates.size());

        return templates.get(result);

    }
}