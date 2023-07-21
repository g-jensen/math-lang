package mathlang;

import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.NullExpressionNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExpressionTreeBuilder {
    public ExpressionTreeBuilder() {
        this.definedSymbols = new HashMap<>();
        definedSymbols.put("e",new Value("2.718281828459045"));
        definedSymbols.put("tao",new Value("6.283185307179586"));
    }
    public ExpressionNode build(String[] tokens) {
        ExpressionNodeFactory nodeFactory = new ExpressionNodeFactory(this);
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
    public Map<String, Value> definedSymbols;
}
