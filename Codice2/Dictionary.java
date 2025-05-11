import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.jar.Attributes.Name;

/*intanto vi carico una prima versione del dizionario,
 * bisogna commentare il codice per bene e
 * cancellare alcuni metodi tipo test
 */
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.jar.Attributes.Name;

/*intanto vi carico una prima versione del dizionario,
 * bisogna commentare il codice per bene e
 * cancellare alcuni metodi tipo test
 */

public class Dictionary  {
    public Dictionary() throws IOException{
        names=setList("Names.txt", Name::new);
        verbs=setList("Verbs.txt", Verb::new);
        adjs=setList("Adjs.txt", Adjective::new);
    }
 

    
    public Name getName(){
        return chooseToken(names);
    }

    public Verb getVerb(){
        return chooseToken(verbs);
    }

    public Adjective getAdj(){
        return chooseToken(adjs);
    }

    private <T> T chooseToken(List<T> l){
        Random rand=new Random();
        T t=l.get(rand.nextInt(l.size()));
        return t;
    }

    //CARIAMO LE LISTE DIOCANEE
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

    private static <T> void printList(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }

    public void test() throws IOException{
               
    }





    private List<Name> names;
    private List<Verb> verbs;
    private List<Adjective> adjs;


}
