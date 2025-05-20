//Package
package dictionary;

//Import
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/*intanto vi carico una prima versione del dizionario,
 * bisogna commentare il codice per bene e
 * cancellare alcuni metodi tipo test
 */

public class Dictionary  {
    //Costruttore
    public Dictionary() throws IOException{
        nouns=setList("src/data/Nouns.txt", MyNoun::new);

        verbs=setList("src/data/Verbs_thirdperson.txt", MyVerb::new);
        verbs_nothirdperson=setList("src/data/Verbs.txt", MyVerb::new);
        verbs_past=setList("src/data/Verbs_past.txt", MyVerb::new);

        adjs=setList("src/data/Adjs.txt", MyAdjective::new);
    }
 
    //metodo usato nel costruttore
    private static <T> List<T> setList(String fileName, Function<String, T> creator) throws IOException{
        FileReader file=new FileReader(fileName);
        Scanner sc=new Scanner(file);
        List<T> result=new ArrayList<>();
        while (sc.hasNextLine()) {
            String line=sc.nextLine();
            result.add(creator.apply(line));
        }

        sc.close();
        return result;
    }

    //Aggiungere verbi in terza persona e past
    
//Metodi get
    public MyNoun getNoun(){
        return chooseToken(nouns);
    }

    public MyVerb getVerb(){
        return chooseToken(verbs);
    }

    public MyVerb getVerb_nothirdperson(){
        return chooseToken(verbs_nothirdperson);
    }

    public MyVerb getVerb_past(){
        return chooseToken(verbs_past);
    }

    public MyAdjective getAdj(){
        return chooseToken(adjs);
    }

    //metodi Random
    private <T> T chooseToken(List<T> l){
        Random rand=new Random();
        T t=l.get(rand.nextInt(l.size()));
        return t;
    }

// METODI DI DEBUG  

    //Debug: metodo print
    private static <T> void printList(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }


    //Variabili
    private List<MyNoun> nouns;
    private List<MyVerb> verbs, verbs_nothirdperson, verbs_past;
    private List<MyAdjective> adjs;


}
