package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;
import mathlang.expressionnode.BinaryExpressionNode;

public class NullExpressionNode extends BinaryExpressionNode {
    public Value evaluate() {
        return new NullValue();
    }
}
