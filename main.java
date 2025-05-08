//Import


public class main {
    public static void main(String[] args) {
        //Variabili
        String[] nouns;
        String[] verbs;
        String[] adj;
        String result;


        //Faccio finta che ho randoom questa stringa di nomi: {"Anna", "dog", "Luca", "Marco"}; returnata da un metodo
        //Quindi avro': nouns = metodo(...) e uguale per verbi e adj
        nouns = new String[]{"Anna", "dog", "Luca", "Marco"};
        verbs = new String[]{"eats", "plays", "is", "dies"};
        adj = new String[]{"funny", "pretty", "bad", "good"};

        //Creo un oggetto template
        Template t1 = new Template("(noun) (verb) a (adj) (noun) and (adj) (noun) (verb) (adj)", 3, 2, 3);

        result = t1.FillTemplate(nouns, verbs, adj);
        System.out.println(result);




    }
}
