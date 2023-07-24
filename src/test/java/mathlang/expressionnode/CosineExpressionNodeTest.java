package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.ConstantExpressionNode;
import mathlang.expressionnode.CosineExpressionNode;
import mathlang.expressionnode.ExpressionNode;
import mathlang.expressionnode.UnaryExpressionNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosineExpressionNodeTest {

    @Test
    void evaluatesToCosine() {
        ExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        UnaryExpressionNode n1 = new CosineExpressionNode(zero);
        UnaryExpressionNode n2 = new CosineExpressionNode(one);
        UnaryExpressionNode n3 = new CosineExpressionNode(two);
        UnaryExpressionNode n4 = new CosineExpressionNode(nullNode);

        Scope s = new Scope();
        assertEquals(new Value("1"),n1.evaluate(s));
        assertEquals(new Value(Double.toString(Math.cos(1))),n2.evaluate(s));
        assertEquals(new Value(Double.toString(Math.cos(2))),n3.evaluate(s));
        assertEquals(new NullValue(),n4.evaluate(s));
    }
}