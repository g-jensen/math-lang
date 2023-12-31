package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.ConstantExpressionNode;
import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.NaturalLogExpressionNode;
import mathlang.expressionnode.UnaryExpressionNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaturalLogExpressionNodeTest {
    @Test
    void evaluatesToLn() {
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        UnaryExpressionNode n1 = new NaturalLogExpressionNode(one);
        UnaryExpressionNode n2 = new NaturalLogExpressionNode(two);
        UnaryExpressionNode n3 = new NaturalLogExpressionNode(nullNode);

        Scope s = new Scope();
        assertEquals(new Value("0"),n1.evaluate(s));
        assertEquals(new Value(Double.toString(Math.log(2))),n2.evaluate(s));
        assertEquals(new NullValue(),n3.evaluate(s));
    }
}