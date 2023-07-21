package mathlang;

import mathlang.expressionnode.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        this.specialTokens = new String[]{"(",")","+","-","*","/","exp","ln","sin","cos"};
    }
    public ExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (Utils.isNumeric(token)) {
            return createConstantNodeFromNumber(tokens, tokenIndex);
        } else if (treeBuilder.definedSymbols.containsKey(token)) {
            return createConstantNodeFromSymbol(tokens, tokenIndex);
        } else if (!isSpecial(token)) {
            return createNullNode();
        } else if (token.equals("+")) {
            return createBinaryNode(tokens,tokenIndex,AdditionExpressionNode.class);
        } else if (token.equals("-")) {
            return createBinaryNode(tokens,tokenIndex,SubtractionExpressionNode.class);
        } else if (token.equals("*")) {
            return createBinaryNode(tokens,tokenIndex,MultiplicationExpressionNode.class);
        } else if (token.equals("/")) {
            return createBinaryNode(tokens,tokenIndex,DivisionExpressionNode.class);
        } else if (token.equals("exp")) {
            return createUnaryNode(tokens,tokenIndex,ExponentialExpressionNode.class);
        } else if (token.equals("ln")) {
            return createUnaryNode(tokens,tokenIndex,NaturalLogExpressionNode.class);
        } else if (token.equals("sin")) {
            return createUnaryNode(tokens,tokenIndex,SineExpressionNode.class);
        } else if (token.equals("cos")) {
            return createUnaryNode(tokens,tokenIndex,CosineExpressionNode.class);
        }
        return null;
    }
    private boolean isSpecial(String token) {
        return Arrays.asList(specialTokens).contains(token);
    }
    private  ExpressionNode createNullNode() {
        return new NullExpressionNode();
    }
    private ExpressionNode createConstantNodeFromNumber(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(new Value(tokens[tokenIndex]));
    }
    private ExpressionNode createConstantNodeFromSymbol(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(treeBuilder.definedSymbols.get(tokens[tokenIndex]));
    }
    private ExpressionNode createBinaryNode(String[] tokens, int tokenIndex, Class<? extends BinaryExpressionNode> c) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return c.getConstructor(ExpressionNode.class,ExpressionNode.class)
                    .newInstance(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createUnaryNode(String[] tokens, int tokenIndex, Class<? extends UnaryExpressionNode> c) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return c.getConstructor(ExpressionNode.class)
                    .newInstance(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
    private String[] specialTokens;
}
