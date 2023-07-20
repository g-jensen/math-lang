package mathlang;

public class AdditionExpressionNode extends BinaryExpressionNode{
    public AdditionExpressionNode(BinaryExpressionNode left, BinaryExpressionNode right) {
        super(left, right);
    }
    public Value evaluate() {
        Value a = left.evaluate();
        Value b = right.evaluate();
        if (a.toString().equals("null") || b.toString().equals("null")) {
            return new Value("null");
        } else {
            Double val = a.toDouble() + b.toDouble();
            return new Value(val.toString());
        }
    }
}
