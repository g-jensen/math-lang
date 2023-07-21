package mathlang;

public class AdditionExpressionNode extends BinaryExpressionNode{
    public AdditionExpressionNode(ExpressionNode left, ExpressionNode right) {
        super(left, right);
    }
    public Value evaluate() {
        Value a = left.evaluate();
        Value b = right.evaluate();
        Value nullValue = new NullValue();
        if (a.equals(nullValue) || b.equals(nullValue)) {
            return nullValue;
        } else {
            double val = a.toDouble() + b.toDouble();
            return new Value(Double.toString(val));
        }
    }
}
