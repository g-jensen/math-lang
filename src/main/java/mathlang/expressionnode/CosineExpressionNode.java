package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;

public class CosineExpressionNode extends UnaryExpressionNode {
    public CosineExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate() {
        Value v = parameter.evaluate();
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.cos(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
