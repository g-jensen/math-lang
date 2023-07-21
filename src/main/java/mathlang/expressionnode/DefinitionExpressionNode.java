package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;

public class DefinitionExpressionNode extends BinaryExpressionNode {
    public DefinitionExpressionNode(ExpressionNode left, ExpressionNode right) {
        super(left,right);
    }
    public Value evaluate() {
        if (!left.evaluate().isWord()) {return new NullValue();}
        return right.evaluate();
    }
}
