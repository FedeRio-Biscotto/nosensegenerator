//Package
package dictionary;


public class Token {
    //Costruttore
    public Token(String s){
        token=s;
    }

    public Token(){
        token=null;
    }

    //Metodo GET
    public String getToken(){
        return token;
    }

    //Metodo SET
    public void setToken(String t){
        token=t;
    }

    //Metodo toString
    public String toString(){
        return this.getToken();
    }

    //Variabili
    private String token;
}
