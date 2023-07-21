package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;

public class ExponentialExpressionNode extends UnaryExpressionNode {
    public ExponentialExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate() {
        Value v = parameter.evaluate();
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.exp(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
