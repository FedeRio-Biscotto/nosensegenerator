import java.util.ArrayList;
import java.util.List;

/*fare i getter e setter */

public class Template {
    //Raga le variabili mettetele sopra per favore che dc mi viene il cancro agli occhi
    //Variabili
    private String template;
    private int namesNumber,verbsNumber, adjNumber;

    //Costruttore
    public Template(String t, int names, int verbs, int adjs){
        template=t;
        namesNumber=names;
        verbsNumber=verbs;
        adjNumber=adjs;
    }

    //Metodo che riempe il template
    public String FillTemplate(String[] nouns, String[] verbs, String[] adjectives){
        //Controllo che i nomi, verbi e aggettivi passati siano almeno quanti ne servono
        if(nouns.length < namesNumber){
            System.err.println("[Error]: Insufficient nouns");
            return template;
        }
        if(verbs.length < verbsNumber){
            System.err.println("[Error]: Insufficient verbs");
            return template;
        }
        if(adjectives.length < adjNumber){
            System.err.println("[Error]: Insufficient adjectives");
            return template;
        }

        //Creo una copia di template su cui lavorare
        String tem = template;

        //Riempe il template: dove trova [noun] mette un nome, [verb] un verbo, [adj] un aggettivo
        //Esempio di template: <noun> <verb> a <adj> <noun>
        
        //Sostituisce i nomi
        for (int i = 0; i < namesNumber; i++){
            tem = tem.replaceFirst("\\(noun\\)", nouns[i]);  
        } 
        //Sostituisce i verbi
        for (int i = 0; i < verbsNumber; i++){
            tem = tem.replaceFirst("\\(verb\\)", verbs[i]);  
        } 
        //Sostituisce gli aggettivi
        for (int i = 0; i < adjNumber; i++){
            tem = tem.replaceFirst("\\(adj\\)", adjectives[i]);  
        } 

        return tem;
    }
    


}
