//Package
package dictionary;

public class MyAdjective extends Token {
    public MyAdjective (String a){
        super(a);
    }

    public String getAdj(){
        return this.getToken();
    }

    public void setAdj(String n){
        this.setToken(n);
    }
}
