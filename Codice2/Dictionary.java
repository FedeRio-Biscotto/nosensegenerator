import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*qua ci vanno messe le liste dei names e poi un metodo random (chooseToken) 
mi sceglie quale elemento prendere */

public class Dictionary {
    public Dictionary(){
        setLists();
        Name n=new Name("diocane");
        names.add(n);
    }
 
    /*setta le liste di nomi, aggettivi, verbi boh ci pensiamo dopo come fare */
    private void setLists(){

    }

    
    public Name getName(){
        Name n=(Name) chooseToken(names);
        return n;
    }

    public Verb getVerb(){
        Verb v=(Verb) chooseToken(verbs);
        return v;
    }

    public Adjective getAdj(){
        Adjective a=(Adjective) chooseToken(adjs);
        return a;
    }

    private Token chooseToken(List<? extends Token> l){
        Random rand=new Random();
        Token t=l.get(rand.nextInt(l.size()));
        return t;
    }

    private List<Name> names= new ArrayList<Name>();
    private List<Verb> verbs= new ArrayList<Verb>();
    private List<Adjective> adjs= new ArrayList<Adjective>();


}
