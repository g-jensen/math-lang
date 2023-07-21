package mathlang;

public class ConstantExpressionNode implements ExpressionNode {
    public ConstantExpressionNode(Value value) {
        super();
        this.value = value;
    }
    public Value evaluate() {
        return value;
    }
    private Value value;
}
