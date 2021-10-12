import java.util.List;

public class Lexeme {
    private LexType token;
    private String str;

    public Lexeme(LexType token, String str) {
        this.token = token;
        this.str = str;
    }
    public Lexeme(LexType token, Character str) {
        this.token = token;
        this.str = str.toString();
    }

    public LexType getToken() {
        return token;
    }

    public String getStr() {
        return str;
    }
}
