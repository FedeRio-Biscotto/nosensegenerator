public class Token {
    public Token(String s){
        token=s;
    }

    public Token(){
        token=null;
    }

    public String getToken(){
        return token;
    }

    private String token;
}
