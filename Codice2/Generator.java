import java.util.ArrayList;
import java.util.List;

public class Generator {
    
    /*metodo alla base di tutto: genera una sentence random dalla sentence immessa dall'utente */
    public String genSentence(String sentenceIn){

        //----------PARTE 1--------------------
        //----ANALIZZA SENTENCE IN ------------

        //Debug: I template hanno in input solo la frase, in automatico viene analizzato il numero di tokens mancanti, ci sono dei get per averli se servono
        
        // A QUESTO PUNTO, NAMELIST, ADJLIST E VERB LIST contengono le
        // parole della sentenceIn


        //--------PARTE 2----------------------
        //------COSTRUISCI LA FRASE RANDOM-----

        //Template template=getTemplate();
        Template template= new Template("dioboia", 5, 6, 7); 
        for (int i=template.getNamesNumber(); i<=nameList.size(); i++){
            nameList.add(dict.getName());
            System.out.println(i);
        }




        //-------PARTE 3 ---------------------------
        // ------------CHECHK TOXICITY---------------



        return null;
    }





    private List<Name> nameList=new ArrayList<>();
    private List<Adjective> adjList=new ArrayList<>();
    private List<Verb> verbList=new ArrayList<>();
    private Dictionary dict=new Dictionary(); 

    //Queste variabili non servono perché basta fare un get sul template
    //private int missingName, missingVerb, missingAdj;
}
