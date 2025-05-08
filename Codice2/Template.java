import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Template {
    //Variabili
    private String template;
    private int namesNumber,verbsNumber, adjNumber;

    //Costruttore
    public Template(String t){
        template=t;
        namesNumber= CountTokens("\\(noun\\)", t);
        verbsNumber= CountTokens("\\(verb\\)", t);
        adjNumber= CountTokens("\\(adj\\)", t);
    }

    //Metodo privato che conta quanti nomi/aggettivi/verbi ci siano nel template
    private int CountTokens(String target, String sentence){
        int count = 0;

        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()) {
            count++;
        }

        return count;
    }


    //Metodo che riempie il template
    public String FillTemplate(ArrayList<String> nouns, ArrayList<String> verbs, ArrayList<String> adjectives){
        //Controllo che i nomi, verbi e aggettivi passati siano almeno quanti ne servono; altrimenti ritorno il template vuoto
        if(nouns.size() < namesNumber){
            System.err.println("[Error]: Insufficient nouns");
            return template;
        }
        if(verbs.size() < verbsNumber){
            System.err.println("[Error]: Insufficient verbs");
            return template;
        }
        if(adjectives.size() < adjNumber){
            System.err.println("[Error]: Insufficient adjectives");
            return template;
        }

        //Creo una copia di template su cui lavorare
        String tem = template;

        //Riempie il template: dove trova (noun) mette un nome, (verb) un verbo, (adj) un aggettivo
        //Esempio di template: (noun) (verb) a (adj) (noun)
        
        //Sostituisce i nomi
        for (int i = 0; i < namesNumber; i++){
            tem = tem.replaceFirst("\\(noun\\)", nouns.get(i));  
        } 
        //Sostituisce i verbi
        for (int i = 0; i < verbsNumber; i++){
            tem = tem.replaceFirst("\\(verb\\)", verbs.get(i));  
        } 
        //Sostituisce gli aggettivi
        for (int i = 0; i < adjNumber; i++){
            tem = tem.replaceFirst("\\(adj\\)", adjectives.get(i));  
        } 

        return tem;
    }


    //Metodi get
    public String getTemplate(){
        return template;
    }

    public int getMissingVerbs(){
        return verbsNumber;
    }
    

    public int getMissingNouns(){
        return namesNumber;
    }


    public int getMissingAdjectives(){
        return adjNumber;
    }


}
