public class Sentence {
    public Sentence(){
        sentence=null;
    }

    public Sentence(String s){
        sentence=s;
    }

    public String getSentence(){
        return sentence;
    }

    public void setSentence(String s){
        sentence=s;
    }

    public void addToken(String t){
        sentence += t;
    }

    public String toString(){
        return this.getSentence();
    }



    private String sentence;
}
