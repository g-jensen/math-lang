package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

public class AdditionExpressionNode extends BinaryExpressionNode {
    public AdditionExpressionNode(ExpressionNode left, ExpressionNode right) {
        super(left, right);
    }
    public Value evaluate(Scope scope) {
        Value a = left.evaluate(scope);
        Value b = right.evaluate(scope);
        Value nullValue = new NullValue();
        if (a.equals(nullValue) || b.equals(nullValue)) {
            return nullValue;
        } else {
            double val = a.toDouble() + b.toDouble();
            return new Value(Double.toString(val));
        }
    }
}
