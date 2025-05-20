//Package
package dictionary;

public class MyVerb extends Token {
    //Costruttore
    public MyVerb (String v){
        super(v);
    }

    //Metodo GET
    public String getMyVerb(){
        return this.getToken();
    }

    //Metodo SET
    public void setMyVerb(String n){
        this.setToken(n);
    }
}
