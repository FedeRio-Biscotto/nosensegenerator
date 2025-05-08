//Import
import java.util.ArrayList;
import java.util.Arrays;


public class main {
    public static void main(String[] args) {
        //Variabili
        ArrayList<String> nouns;
        ArrayList<String> verbs;
        ArrayList<String> adj;
        String result;


        //Faccio finta che ho randoom questa stringa di nomi: {"Anna", "dog", "Luca", "Marco"}; returnata da un metodo
        //Quindi avro': nouns = metodo(...) e uguale per verbi e adj
        nouns = new ArrayList<String>(Arrays.asList("Anna", "dog", "Luca", "Marco"));
        verbs = new ArrayList<String>(Arrays.asList("eats", "plays", "is", "dies"));
        adj   = new ArrayList<String>(Arrays.asList("funny", "pretty", "bad", "good"));

        //Prendo un oggetto template
        Template t1 = TemplatesLibrary.RandomTemplatePicker();

        result = t1.FillTemplate(nouns, verbs, adj);
        System.out.println(result);




    }
}
