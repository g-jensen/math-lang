package mathlang;

import java.util.ArrayList;
import java.util.Objects;

public class ExpressionTreeBuilder {
    public BinaryExpressionNode build(String[] tokens) {
        ExpressionNodeFactory nodeFactory = new ExpressionNodeFactory(this);
        for (int i = 0; i < tokens.length; i++) {
            BinaryExpressionNode node = nodeFactory.createNode(tokens,i);
            if (Objects.nonNull(node)) {return node;}
        }
        return new NullExpressionNode();
    }
    public String[] nextParameter(String[] tokens, int startingIndex) {
        if (Utils.isNumeric(tokens[startingIndex+1])) {
            return new String[]{tokens[startingIndex+1]};
        } else {
            return getNest(tokens, startingIndex);
        }
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
