public class Verb extends Token {
    public Verb (String v){
        super(v);
    }

    public String getVerb(){
        return this.getToken();
    }

    public void setVerb(String n){
        this.setToken(n);
    }
}
