package mathlang;

public class ConstantExpressionNode extends BinaryExpressionNode {
    public ConstantExpressionNode(Value value) {
        super();
        this.value = value;
    }
    public Value evaluate() {
        return value;
    }
    private Value value;
}
