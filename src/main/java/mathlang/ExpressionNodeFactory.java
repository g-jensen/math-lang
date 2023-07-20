package mathlang;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
    }
    public BinaryExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (token.equals("+")) {
            return createAdditionNode(tokens, tokenIndex);
        } else if (Utils.isNumeric(token)) {
            return createConstantNode(tokens, tokenIndex);
        }
        return null;
    }

    private BinaryExpressionNode createConstantNode(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(Integer.parseInt(tokens[tokenIndex]));
    }

    private BinaryExpressionNode createAdditionNode(String[] tokens, int tokenIndex) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return new AdditionExpressionNode(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (ArrayIndexOutOfBoundsException e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
}
