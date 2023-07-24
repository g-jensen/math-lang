package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.BinaryExpressionNode;

public class NullExpressionNode implements ExpressionNode {
    public Value evaluate(Scope scope) {
        return new NullValue();
    }
}
