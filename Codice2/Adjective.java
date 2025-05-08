public class Adjective extends Token {
    public Adjective (String a){
        super(a);
    }

    public String getAdj(){
        return this.getToken();
    }

    public void setAdj(String n){
        this.setToken(n);
    }
}
