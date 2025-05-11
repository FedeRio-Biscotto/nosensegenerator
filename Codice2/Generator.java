import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.jar.Attributes.Name;

public class Generator {
    
    /*metodo alla base di tutto: genera una sentence random dalla sentence immessa dall'utente */
    public String genSentence(String sentenceIn) throws IOException{

        Dictionary dict=new Dictionary();
        //----------PARTE 1--------------------
        //----ANALIZZA SENTENCE IN ------------

        //fede poi collega tu, ci sarà un metodo tipo analyze(Sentence in)
        // che returna nomi, agg, verbi
        // A QUESTO PUNTO, NAMELIST, ADJLIST E VERB LIST contengono le
        // parole della sentenceIn


        //--------PARTE 2----------------------
        //------COSTRUISCI LA FRASE RANDOM-----


        nameList.add(new Name("god"));
        adjList.add(new Adjective("bastard"));
        verbList.add(new Verb("fuck"));
        
        Template template=TemplatesLibrary.RandomTemplatePicker();

        //riempi le liste
        fillList(nameList, template.getMissingNouns(), dict::getName);
        fillList(verbList, template.getMissingVerbs(), dict::getVerb);
        fillList(adjList, template.getMissingAdjectives(), dict::getAdj);

        Collections.shuffle(nameList);
        Collections.shuffle(verbList);
        Collections.shuffle(adjList);

        sentenceOut=template.FillTemplate(nameList, verbList, adjList);
        
        



        //-------PARTE 3 ---------------------------
        // ------------CHECHK TOXICITY---------------



        return sentenceOut;
    }

    private static <T> void fillList(List<T> list, int targetSize, Supplier<T> sup){
        while (list.size() < targetSize){
            list.add(sup.get());
        }
    }





    private List<Name> nameList=new ArrayList<>();
    private List<Adjective> adjList=new ArrayList<>();
    private List<Verb> verbList=new ArrayList<>();
    private String sentenceOut="";
    //private Dictionary dict=new Dictionary(); 

}
