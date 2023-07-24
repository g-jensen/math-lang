package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.ConstantExpressionNode;
import mathlang.expressionnode.ExponentialExpressionNode;
import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.UnaryExpressionNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponentialExpressionNodeTest {

    @Test
    void evaluatesToExp() {
        ExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        UnaryExpressionNode n1 = new ExponentialExpressionNode(zero);
        UnaryExpressionNode n2 = new ExponentialExpressionNode(one);
        UnaryExpressionNode n3 = new ExponentialExpressionNode(two);
        UnaryExpressionNode n4 = new ExponentialExpressionNode(nullNode);

        Scope s = new Scope();
        assertEquals(new Value("1"),n1.evaluate(s));
        assertEquals(new Value(Double.toString(Math.exp(1))),n2.evaluate(s));
        assertEquals(new Value(Double.toString(Math.exp(2))),n3.evaluate(s));
        assertEquals(new NullValue(),n4.evaluate(s));
    }
}