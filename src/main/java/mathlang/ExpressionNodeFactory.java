package mathlang;

import mathlang.expressionnode.*;

import java.util.Arrays;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
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
            return createAdditionNode(tokens, tokenIndex);
        } else if (token.equals("-")) {
            return createSubtractionNode(tokens,tokenIndex);
        } else if (token.equals("*")) {
            return createMultiplicationNode(tokens,tokenIndex);
        } else if (token.equals("/")) {
            return createDivisionNode(tokens,tokenIndex);
        } else if (token.equals("exp")) {
            return createExponentialNode(tokens,tokenIndex);
        } else if (token.equals("ln")) {
            return createNaturalLogNode(tokens,tokenIndex);
        } else if (token.equals("sin")) {
            return createSineNode(tokens,tokenIndex);
        } else if (token.equals("cos")) {
            return createCosineNode(tokens,tokenIndex);
        }
        return null;
    }
    private String[] specialTokens = {"(",")","+","-","*","/","exp","ln","sin","cos"};
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
    private ExpressionNode createAdditionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new AdditionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createSubtractionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new SubtractionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createMultiplicationNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new MultiplicationExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createDivisionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new DivisionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createExponentialNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return new ExponentialExpressionNode(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createNaturalLogNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return new NaturalLogExpressionNode(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createSineNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return new SineExpressionNode(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createCosineNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return new CosineExpressionNode(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
}
