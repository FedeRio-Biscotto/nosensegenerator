//Package
package NoSenGen.myDictionary;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class MyDictionary {

    //-------COSTRUTTORE DI DEFAULT (con tutti i token)-----//------//
    public MyDictionary(String s) throws IOException{
        String path="src/"+s+"/resources/";
        nouns=setList(path+"Nouns.txt", MyNoun::new);
        verbs=setList(path+"Verbs_thirdperson.txt", MyVerb::new);
        verbs_nothirdperson=setList(path+"Verbs.txt", MyVerb::new);
        verbs_past=setList(path+"Verbs_past.txt", MyVerb::new);
        adjs=setList(path+"Adjs.txt", MyAdjective::new);
    }
    //Legge il file riga per riga e trasforma ogni riga in un oggetto di tipo "T"
    // usando una funzione 'creator' e restituisce la lista di oggetti
    private static <T> List<T> setList(String fileName, Function<String, T> creator) throws IOException{
        FileReader file=new FileReader(fileName);
        Scanner sc=new Scanner(file);
        List<T> result=new ArrayList<>();
        while (sc.hasNextLine()) {
            String line=sc.nextLine();
            //Converte ogni riga del file in un oggetto T
            result.add(creator.apply(line));
        }
        sc.close();
        return result;
    }
    
//--------------------------------METODI GET--------------------------------//
    public MyNoun getNoun(){
        return chooseToken(nouns);
    }

    public MyVerb getVerb(){
        return chooseToken(verbs);
    }

    public MyVerb getVerbNothirdPerson(){
        return chooseToken(verbs_nothirdperson);
    }

    public MyVerb getVerbPast(){
        return chooseToken(verbs_past);
    }

    public MyAdjective getAdj(){
        return chooseToken(adjs);
    }

    //metodi Random
    private <T> T chooseToken(List<T> l){
        Random rand=new Random();
        return l.get(rand.nextInt(l.size()));

    }
//--------------------------------VARIABILI--------------------------------//
    private List<MyNoun> nouns;
    private List<MyVerb> verbs;
    private List<MyVerb> verbs_nothirdperson;
    private List<MyVerb> verbs_past;
    private List<MyAdjective> adjs;


}
