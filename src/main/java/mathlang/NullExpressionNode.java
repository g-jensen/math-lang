package mathlang;

public class NullExpressionNode extends  BinaryExpressionNode {
    public Value evaluate() {
        return new NullValue();
    }
}
