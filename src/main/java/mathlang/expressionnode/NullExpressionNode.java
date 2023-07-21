package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;
import mathlang.expressionnode.BinaryExpressionNode;

public class NullExpressionNode implements ExpressionNode {
    public Value evaluate() {
        return new NullValue();
    }
}
