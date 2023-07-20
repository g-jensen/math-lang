package mathlang;

import java.util.Arrays;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
    }
    public BinaryExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (Utils.isNumeric(token)) {
            return createConstantNode(tokens, tokenIndex);
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
        }
        return null;
    }
    private String[] specialTokens = {"(", ")", "+","-", "*", "/"};
    private boolean isSpecial(String token) {
        return Arrays.asList(specialTokens).contains(token);
    }
    private  BinaryExpressionNode createNullNode() {
        return new NullExpressionNode();
    }
    private BinaryExpressionNode createConstantNode(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(new Value(tokens[tokenIndex]));
    }
    private BinaryExpressionNode createAdditionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new AdditionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private BinaryExpressionNode createSubtractionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new SubtractionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private BinaryExpressionNode createMultiplicationNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new MultiplicationExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private BinaryExpressionNode createDivisionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new DivisionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
}
