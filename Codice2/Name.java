public class Name extends Token{
    public Name(String n){
        super(n);
    }

    public String getName(){
        return this.getToken();
    }

    public void setName(String n){
        this.setToken(n);
    }
}
