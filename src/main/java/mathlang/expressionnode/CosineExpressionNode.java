package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

public class CosineExpressionNode extends UnaryExpressionNode {
    public CosineExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate(Scope scope) {
        Value v = parameter.evaluate(scope);
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.cos(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
