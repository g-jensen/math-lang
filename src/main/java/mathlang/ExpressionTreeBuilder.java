package mathlang;

import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.NullExpressionNode;

import java.util.ArrayList;
import java.util.Objects;

public class ExpressionTreeBuilder {
    public ExpressionTreeBuilder() {
        this.nodeFactory = new ExpressionNodeFactory(this);
    }
    public ExpressionNode build(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            ExpressionNode node = nodeFactory.createNode(tokens,i);
            if (Objects.nonNull(node)) {return node;}
        }
        return new NullExpressionNode();
    }
    public String[] nextParameter(String[] tokens, int startingIndex) {
        String token = tokens[startingIndex+1];
        if (token.equals("(") || token.equals(")")) {
            return getNest(tokens, startingIndex);
        } else {
            return new String[]{token};
        }
    }
    private String[] getNest(String[] tokens, int startingIndex) {
        ArrayList<String> outputTokens = new ArrayList<>();
        int openNestCount = 0;
        int closeNestCount = 0;
        for (int i = startingIndex + 1; i < tokens.length; i++) {
            String token = tokens[i];
            outputTokens.add(token);
            if (token.equals("(")) {
                openNestCount++;
            } else if (token.equals(")")) {
                closeNestCount++;
                if (openNestCount == closeNestCount) {
                    break;
                }
            }
        }
        return outputTokens.toArray(new String[0]);
    }
    public ExpressionNodeFactory nodeFactory;
}
