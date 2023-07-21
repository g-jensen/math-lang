package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;
import mathlang.expressionnode.AdditionExpressionNode;
import mathlang.expressionnode.BinaryExpressionNode;
import mathlang.expressionnode.ConstantExpressionNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionExpressionNodeTest {

    @Test
    void evaluatesToSum() {
        ConstantExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ConstantExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ConstantExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ConstantExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        BinaryExpressionNode n1 = new AdditionExpressionNode(one, one);
        BinaryExpressionNode n2 = new AdditionExpressionNode(one, two);
        BinaryExpressionNode n3 = new AdditionExpressionNode(two, three);
        BinaryExpressionNode n4 = new AdditionExpressionNode(one, nullNode);

        assertEquals(new Value("2"),n1.evaluate());
        assertEquals(new Value("3"),n2.evaluate());
        assertEquals(new Value("5"),n3.evaluate());
        assertEquals(new NullValue(),n4.evaluate());
    }
}