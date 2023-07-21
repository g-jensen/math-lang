package mathlang;

import java.util.ArrayList;

public class ExpressionParser {

    private boolean isPartOfNumber(char c) {
        return  c == '.' ||
                c == '-' ||
                c == '+' ||
                Character.isDigit(c);
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

    public String parseWord(String input, int startIndex) {
        StringBuilder numAsString = new StringBuilder();
        for (int i = startIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isAlphabetic(c)) {
                numAsString.append(c);
            } else {
                break;
            }
        }
        return numAsString.toString();
    }

    public String[] getTokens(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isPartOfNumber(c)) {
                String n = parseNumber(input,i);
                tokens.add(n);
                i += n.length()-1;
            } else if (Character.isAlphabetic(c)) {
                String w = parseWord(input,i);
                tokens.add(w);
                i += w.length()-1;
            } else if (c != ' '){
                tokens.add(Character.toString(c));
            }
        }
        return tokens.toArray(new String[0]);
    }
}
