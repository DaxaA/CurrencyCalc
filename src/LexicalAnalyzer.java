import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
    public static List<Lexeme> lexAnalysis(String text) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int index = 0;
        while (index < text.length()) {
            char ch = text.charAt(index);
            switch (ch) {
                case '+':
                    lexemes.add(new Lexeme(LexType.plus, ch));
                    index++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexType.minus, ch));
                    index++;
                    continue;
                case '(':
                    lexemes.add(new Lexeme(LexType.leftp, ch));
                    index++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexType.rightp, ch));
                    index++;
                    continue;
                case '$':
                    index++;
                    ch = text.charAt(index);
                    if (ch <= '9' && ch >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(ch);
                            index++;
                            if (index >= text.length()) {
                                break;
                            }
                            ch = text.charAt(index);
                        } while (ch <= '9' && ch >= '0');
                        if(ch == '.'){
                            sb.append(ch);
                            index++;
                            ch = text.charAt(index);
                            if(ch != ' ') {
                                do {
                                    sb.append(ch);
                                    index++;
                                    if (index >= text.length()) {
                                        break;
                                    }
                                    ch = text.charAt(index);
                                } while (ch <= '9' && ch >= '0');
                            } else {
                                //error
                            }
                        }
                        lexemes.add(new Lexeme(LexType.dol, sb.toString()));
                    }
                    continue;
                default:
                    if (ch <= '9' && ch >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(ch);
                            index++;
                            if (index >= text.length()) {
                                break;
                            }
                            ch = text.charAt(index);
                        } while (ch <= '9' && ch >= '0');
                        if(ch == '.'){
                            sb.append(ch);
                            index++;
                            ch = text.charAt(index);
                            if(ch != ' ') {
                                do {
                                    sb.append(ch);
                                    index++;
                                    if (index >= text.length()) {
                                        break;
                                    }
                                    ch = text.charAt(index);
                                } while (ch <= '9' && ch >= '0');
                            } else {
                                //error
                            }
                        }
                        index++;
                        if(ch == 'р') {
                            lexemes.add(new Lexeme(LexType.rub, sb.toString()));
                        } else {
                            //error
                        }
                    } else {
                        if (ch != ' ') {
                            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                                StringBuilder sb = new StringBuilder();
                                do {
                                    sb.append(ch);
                                    index++;
                                    if (index >= text.length()) {
                                        break;
                                    }
                                    ch = text.charAt(index);
                                } while (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z');

                                if (sb.toString().equals("toDollars")) {
                                    lexemes.add(new Lexeme(LexType.tod, sb.toString()));
                                } else if (sb.toString().equals("toRubles")) {
                                    lexemes.add(new Lexeme(LexType.tor, sb.toString()));
                                } else {
                                    throw new RuntimeException("Unexpected character: " + sb.toString());
                                }
                            }
                        } else {
                            index++;
                        }
                    }
            }
        }
        lexemes.add(new Lexeme(LexType.end, ""));
        return lexemes;
    }
}
