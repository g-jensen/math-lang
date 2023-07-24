package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.UnaryExpressionNode;

public class NaturalLogExpressionNode extends UnaryExpressionNode {
    public NaturalLogExpressionNode(ExpressionNode parameter) {
        super(parameter);
    }
    public Value evaluate(Scope scope) {
        Value v = parameter.evaluate(scope);
        Value nullValue = new NullValue();
        if (v.equals(nullValue)) {
            return nullValue;
        } else {
            double val = Math.log(v.toDouble());
            return new Value(Double.toString(val));
        }
    }
}
