package mathlang;

public abstract class BinaryExpressionNode {
    public BinaryExpressionNode() {
        this.left = null;
        this.right = null;
    }
    public BinaryExpressionNode(BinaryExpressionNode left, BinaryExpressionNode right) {
        this.left = left;
        this.right = right;
    }
    abstract Integer evaluate();
    protected BinaryExpressionNode left;
    protected BinaryExpressionNode right;
}
