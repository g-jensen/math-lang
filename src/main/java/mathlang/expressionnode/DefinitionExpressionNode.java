package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

public class DefinitionExpressionNode extends BinaryExpressionNode {
    public DefinitionExpressionNode(ExpressionNode left, ExpressionNode right) {
        super(left,right);
    }
    public Value evaluate(Scope scope) {
        if (!left.evaluate(scope).isWord()) {return new NullValue();}
        return right.evaluate(scope);
    }
}
