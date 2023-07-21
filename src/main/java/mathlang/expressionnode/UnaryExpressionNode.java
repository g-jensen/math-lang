package mathlang.expressionnode;

import mathlang.Value;
import mathlang.expressionnode.ExpressionNode;

public abstract class UnaryExpressionNode implements ExpressionNode {
    public UnaryExpressionNode(ExpressionNode parameter) {
        this.parameter = parameter;
    }
    public abstract Value evaluate();
    protected ExpressionNode parameter;
}
