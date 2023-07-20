package mathlang;

public class AdditionExpressionNode extends BinaryExpressionNode{
    public AdditionExpressionNode(BinaryExpressionNode left, BinaryExpressionNode right) {
        super(left, right);
    }
    public Integer evaluate() {
        return left.evaluate() + right.evaluate();
    }
}
