package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

public class SineExpressionNode extends UnaryExpressionNode {
    public SineExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate(Scope scope) {
        Value v = parameter.evaluate(scope);
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.sin(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
