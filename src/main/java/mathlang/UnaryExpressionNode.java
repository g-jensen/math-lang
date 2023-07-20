package mathlang;

public abstract class UnaryExpressionNode implements ExpressionNode{
    public UnaryExpressionNode(ExpressionNode parameter) {
        this.parameter = parameter;
    }
    public abstract Value evaluate();
    protected ExpressionNode parameter;
}
