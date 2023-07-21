package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;

public class SineExpressionNode extends UnaryExpressionNode {
    public SineExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate() {
        Value v = parameter.evaluate();
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.sin(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
