import java.util.List;
public class TokenList {
    private int pos;
    public List<Lexeme> lexemes;
    public TokenList(List<Lexeme> lexemes){
        this.lexemes = lexemes;
    }
    public Lexeme next() {
        return lexemes.get(pos++);
    }
    public void back() {
        pos--;
    }
}
