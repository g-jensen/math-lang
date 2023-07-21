package mathlang;

public class NaturalLogExpressionNode extends UnaryExpressionNode {
    public NaturalLogExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate() {
        Value v = parameter.evaluate();
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.log(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
