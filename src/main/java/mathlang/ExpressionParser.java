package mathlang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class ExpressionParser {
    public ExpressionParser() {
        specialChars = new Character[]{' ', '(', ')','[',']'};
    }

    private boolean isPartOfNumber(char c) {
        return  c == '.' ||
                c == '-' ||
                c == '+' ||
                Character.isDigit(c);
    }

    private boolean isNotSpecialChar(Character c) {
        return !Arrays.asList(specialChars).contains(c);
    }

    public String parse(String input, int startIndex, Predicate<Character> pred) {
        StringBuilder numAsString = new StringBuilder();
        for (int i = startIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            if (pred.test(c)) {
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
                String n = parse(input,i,this::isPartOfNumber);
                tokens.add(n);
                i += n.length()-1;
            } else if (Character.isAlphabetic(c)) {
                String w = parse(input,i,this::isNotSpecialChar);
                tokens.add(w);
                i += w.length()-1;
            } else if (c != ' '){
                tokens.add(Character.toString(c));
            }
        }
        return tokens.toArray(new String[0]);
    }
    Character[] specialChars;
}
