//Package
package dictionary;

public class MyAdjective extends Token {
    //Costruttore
    public MyAdjective (String a){
        super(a);
    }

    //Metodo GET
    public String getAdj(){
        return this.getToken();
    }

    //Metodo SET
    public void setAdj(String n){
        this.setToken(n);
    }
}
