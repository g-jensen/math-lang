package mathlang;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

public class ExpressionTreeBuilder {
    public BinaryExpressionNode build(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+")) {
                return buildAdditionExpressionNode(tokens,i);
            } else if (isNumeric(token)) {
                return new ConstantExpressionNode(Integer.parseInt(token));
            }
        }
        return new NullExpressionNode();
    }
    // TODO - move to AdditionExpressionNodeFactory
    private BinaryExpressionNode buildAdditionExpressionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = nextParameter(tokens,tokenIndex);
            String[] p2 = nextParameter(tokens,tokenIndex+p1.length);
            return new AdditionExpressionNode(build(p1), build(p2));
        } catch (ArrayIndexOutOfBoundsException e) {
            return new NullExpressionNode();
        }
    }
    private String[] nextParameter(String[] tokens, int startingIndex) {
        if (isNumeric(tokens[startingIndex+1])) {
            return new String[]{tokens[startingIndex+1]};
        } else {
            return getNest(tokens, startingIndex);
        }
    }

    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }

    private String[] getNest(String[] tokens, int startingIndex) {
        ArrayList<String> outputTokens = new ArrayList<>();
        int openNestCount= 0;
        int closeNestCount = 0;
        for (int i = startingIndex +1; i < tokens.length; i++) {
            String token = tokens[i];
            outputTokens.add(token);
            if (token.equals("(")) {
                openNestCount++;
            } else if (token.equals(")")) {
                closeNestCount++;
                if (openNestCount == closeNestCount) {break;}
            }
        }
        return outputTokens.toArray(new String[0]);
    }
}
