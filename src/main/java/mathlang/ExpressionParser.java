package mathlang;

import java.util.ArrayList;

public class ExpressionParser {

    private boolean isPartOfNumber(char c) {
        return Character.isDigit(c) || c == '-' || c == '+';
    }

    public String parseNumber(String input, int startIndex) {
        StringBuilder numAsString = new StringBuilder();
        for (int i = startIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isPartOfNumber(c)) {
                numAsString.append(c);
            } else {
                break;
            }
        }
        return numAsString.toString();
    }

    public ArrayList<String> getTokens(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isPartOfNumber(c)) {
                String n = parseNumber(input,i);
                tokens.add(n);
                i += n.length()-1;
            } else if (c != ' '){
                tokens.add(Character.toString(c));
            }
        }
        return tokens;
    }
}
