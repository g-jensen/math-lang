package mathlang.expressionnode;

import mathlang.Scope;
import mathlang.Value;

public class ConstantExpressionNode implements ExpressionNode {
    public ConstantExpressionNode(Value value) {
        super();
        this.value = value;
    }
    public Value evaluate(Scope scope) {
        return value;
    }
    private Value value;
}
