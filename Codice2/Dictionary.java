import java.util.ArrayList;
import java.util.List;

/*qua ci vanno messe le liste dei names e poi un metodo random (chooseToken) 
mi sceglie quale elemento prendere */

public class Dictionary {
    public Dictionary(){    }

    Name n=new Name("diocane che mal di schiena");
    
    public Name getName(){
        System.out.println(n);
        return names.getFirst();
    }


    private List<Name> names= new ArrayList<Name>();
}
