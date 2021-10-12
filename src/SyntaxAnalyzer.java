public class SyntaxAnalyzer {

    public static double parse(TokenList list, double exRate) {
        Lexeme lexeme = list.next();
        if (lexeme.getToken() == LexType.end){
            throw new RuntimeException("Unexpected token..");
        } else {
            list.back();
            return expression(list, exRate);
        }
    }

    public static double expression(TokenList list, double exRate) {
        Lexeme lexeme = list.next();
        if (lexeme.getToken() == LexType.tor || lexeme.getToken() == LexType.rub) {
            return expForRub(list,exRate);
        } else if (lexeme.getToken() == LexType.tod || lexeme.getToken() == LexType.dol) {
            return expForDol(list, exRate);
        } else {
            throw new RuntimeException("Unexpected token..");
        }
    }

    public static double expForRub(TokenList list, double exRate) {
        double value = rubles(list, exRate);
        while (true) {
            Lexeme lexeme = list.next();
            switch (lexeme.getToken()) {
                case plus:
                    value += rubles(list, exRate);
                    break;
                case minus:
                    value -= rubles(list, exRate);
                    break;
                case end:
                case rightp:
                    list.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token..");
            }
        }
    }

    public static double expForDol(TokenList list, double exRate) {
        double value = dollars(list, exRate);
        while (true) {
            Lexeme lexeme = list.next();
            switch (lexeme.getToken()) {
                case plus:
                    value += dollars(list, exRate);
                    break;
                case minus:
                    value -= dollars(list, exRate);
                    break;
                case end:
                case rightp:
                    list.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token..");
            }
        }
    }

    public static double rubles(TokenList list, double exRate) {
        Lexeme lexeme = list.next();
        switch (lexeme.getToken()) {
            case rub:
                return Double.parseDouble(lexeme.getStr());
            case dol:
                return Double.parseDouble(lexeme.getStr()) * exRate;
            case tor: case tod:
                return expForRub(list, exRate);
            case leftp:
                double value = expForRub(list, exRate);
                lexeme = list.next();
                if (lexeme.getToken() != LexType.rightp) {
                    throw new RuntimeException();
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token..");
        }
    }

    public static double dollars(TokenList list, double exRate) {
        Lexeme lexeme = list.next();
        switch (lexeme.getToken()) {
            case dol:
                return Double.parseDouble(lexeme.getStr());
            case rub:
                return Double.parseDouble(lexeme.getStr()) / exRate;
            case tod: case tor:
                return expForDol(list, exRate);
            case leftp:
                double value = expForDol(list, exRate);
                lexeme = list.next();
                if (lexeme.getToken() != LexType.rightp) {
                    throw new RuntimeException();
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token..");
        }
    }
}