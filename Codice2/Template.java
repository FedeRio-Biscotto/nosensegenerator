import java.util.ArrayList;
import java.util.List;

/*fare i getter e setter */

public class Template {
    public Template(String t, int names, int verbs, int adjs){
        template=t;
        namesNumber=names;
        verbsNumber=verbs;
        adjNumber=adjs;
    }

    public int getNamesNumber() {
        return namesNumber;
    }

    private String template;
    private int namesNumber,verbsNumber, adjNumber;



}
