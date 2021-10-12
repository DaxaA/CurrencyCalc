import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        FileInputStream input;
        Properties property = new Properties();
        try {
            input = new FileInputStream("src/config.properties");
            property.load(input);
            double exRate = Double.parseDouble(property.getProperty("exRate"));
            String text = read.nextLine(); //toDollars(5.5р + toRubles($60.5))
            List<Lexeme> lexemes = LexicalAnalyzer.lexAnalysis(text);
            TokenList list = new TokenList(lexemes);
            System.out.println(SyntaxAnalyzer.parse(list, exRate));
        } catch (IOException ex) {
            System.err.println("Configuration file is not found!");
        }
    }
}