package mathlang.expressionnode;

import mathlang.Value;

public abstract class BinaryExpressionNode implements ExpressionNode {
    public BinaryExpressionNode(ExpressionNode left, ExpressionNode right) {
        this.left = left;
        this.right = right;
    }
    public abstract Value evaluate();
    protected ExpressionNode left;
    protected ExpressionNode right;
}
