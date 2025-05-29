//Package
package NoSenGen.myDictionary;

public class MyNoun extends Token{
    //Costruttore
    public MyNoun(String n){
        super(n);
    }
    
    //Metodo GET
    public String getMyNoun(){
        return this.getToken();
    }

    //Metodo SET
    public void setMyNoun(String n){
        this.setToken(n);
    }
}
