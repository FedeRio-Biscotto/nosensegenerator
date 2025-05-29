//Package
package NoSenGen.generator;
//Import
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
//Import di pacchetti di classi nostre
import org.springframework.stereotype.Component;
import NoSenGen.myDictionary.*;

import NoSenGen.api.*;
import NoSenGen.template.*;
import org.springframework.stereotype.Service;

@Component
public class Generator {
    public Generator() {
        firstSentence = true;
    }


    /**
     * analizza la sentenceIn e aggiorna le liste con i token
     * che verranno usati per ogni frase
     * @param sentenceIn la frase da cui vengono estratti i token
     * 
     */
    private void analyzeSentence(String sentenceIn) {
        try {
            GoogleLanguageAPI.LanguageApi(sentenceIn);
        } catch (Exception e) {
            // Gestione dell'eccezione
            System.out.println("[Error]: " + e.getMessage());
        }
        
        nounList.addAll(GoogleLanguageAPI.getNouns());
        adjList.addAll(GoogleLanguageAPI.getAdj());
        verbList_nothirdperson.addAll(GoogleLanguageAPI.getVerbs());
        verbList.addAll(GoogleLanguageAPI.getVerbs_thirdperson());
        verbList_past.addAll(GoogleLanguageAPI.getVerbs_past());
    }
    
    /**
     * usa i token della sentenceIn per generare una frase nosense con quei token
     * ed eventualmente altri scelti random
     * @param sentenceIn la frase immessa da utente
     * @param tense il tempo verbale della frase da comporre (passato, pres, fut)
     * @return la frase nosense generata
     * @throws IOException
    */
    public String genSentence(String sentenceIn, int tense) throws IOException {
        // Variabili del metodo
        MyDictionary dict = new MyDictionary();
        List<MyNoun> nounTemp=new ArrayList<>();
        List<MyVerb> verbTemp=new ArrayList<>();
        List<MyVerb> verbTemp_nothirdperson = new ArrayList<>();
        List<MyVerb> verbTemp_past = new ArrayList<>();
        List<MyAdjective> adjTemp = new ArrayList<>();
        String sentenceOut = "";
        Template template = TemplatesLibrary.RandomTemplatePicker();
        // ----------------------------------------------------- (1) COLLEGAMENTO API E ANALISI FRASE -----------------------------------------------------\\

        //analizzo la frase con API solo alla prima richiesta di generare una sentence
        if(firstSentence){
            analyzeSentence(sentenceIn);
            firstSentence=false;
        }

        // ----------------------------------------------------- (2) COSTRUISCI LA FRASE RANDOM -----------------------------------------------------\\

        //aggiorno le liste temporanee con i token immessi dall'utente
        nounTemp.addAll(nounList);
        verbTemp.addAll(verbList);
        verbTemp_nothirdperson.addAll(verbList_nothirdperson);
        verbTemp_past.addAll(verbList_past);
        adjTemp.addAll(adjList);
       

        // riempio le liste temporanee con i token mancanti
        fillList(nounTemp, template.getMissingNouns(), dict::getNoun);
        fillList(verbTemp, template.getMissingVerbs(), dict::getVerb);
        fillList(verbTemp_nothirdperson, template.getMissingVerbs(), dict::getVerb_nothirdperson);
        fillList(verbTemp_past, template.getMissingVerbs(), dict::getVerb_past);
        fillList(adjTemp, template.getMissingAdjectives(), dict::getAdj);

        //mescolo le liste
        Collections.shuffle(nounTemp);
        Collections.shuffle(verbTemp);
        Collections.shuffle(verbTemp_nothirdperson);
        Collections.shuffle(verbTemp_past);
        Collections.shuffle(adjTemp);


        // Si filla con i verbi al presente o al passato in base a cosa chiede l'utente
        if (tense == 0) {
            // Verbi al passato
            sentenceOut = template.FillTemplate_past(nounTemp, verbTemp_past, adjTemp);
        } else if (tense == 2) {
            // Verbi al futuro
            sentenceOut = template.FillTemplate_future(nounTemp, verbTemp_nothirdperson, adjTemp);
        } else {
            // Verbi al presente (tense == 1)
            sentenceOut = template.FillTemplate(nounTemp, verbTemp, verbTemp_nothirdperson, adjTemp);
        }

        // ----------------------------------------------------- (3) CONTROLLA LA TOSSICITA' -----------------------------------------------------\\

        try {
        if(!GoogleToxicityAPI.isToxicityAcceptable(sentenceOut)){
            //Pulisco le liste dei token
            nounList.clear();
            verbList.clear();
            adjList.clear();
            verbList_nothirdperson.clear();
            verbList_past.clear();

            //La frase è troppo tossica, riprovo con un'altra frase
            sentenceOut = "Toxic Phrase | New Phrase: " + genSentence("", tense);
        }
        }catch(Exception e){
        //Gestione di eventuali eccezioni dell'API
        return "[Error]: " + e.getMessage();
        }

        // ----------------------------------------------------- (4) RISULTATO
        // -----------------------------------------------------\\

        // Ritorna la frase
        return sentenceOut;
    }


    /**
     * riempie la lista di token T con i targetSize token che mi servono
     * @param <T> un token generico
     * @param list una lista di token
     * @param targetSize numero di token che mi mancano alla lista
     * @param sup il getter per ottenere il token da aggiungere alla lista
     */
    private static <T> void fillList(List<T> list, int targetSize, Supplier<T> sup) {
        while (list.size() < targetSize) {
            list.add(sup.get());
        }
    }

    /**
     * metodo di debug da cancellare prima di consegnare il progetto
     * @param <T>
     * @param lista
     */
    public static <T> void stampaLista(List<T> lista) {
        for (T elemento : lista) {
            System.out.println(elemento);
        }
    }

    // Varibabili

    //liste di tokens: salvano i token immessi dall'utente
    private List<MyNoun> nounList = new ArrayList<>();
    private List<MyAdjective> adjList = new ArrayList<>();
    private List<MyVerb> verbList = new ArrayList<>();
    private List<MyVerb> verbList_nothirdperson = new ArrayList<>();
    private List<MyVerb> verbList_past = new ArrayList<>();
    
    // true <=> è la prima sentence che il generator produce
    private boolean firstSentence;

}