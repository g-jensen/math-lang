package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationExpressionNodeTest {

    @Test
    void evaluatesToProduct() {
        ConstantExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ConstantExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ConstantExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ConstantExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        BinaryExpressionNode n1 = new MultiplicationExpressionNode(zero, zero);
        BinaryExpressionNode n2 = new MultiplicationExpressionNode(zero, two);
        BinaryExpressionNode n3 = new MultiplicationExpressionNode(two, three);
        BinaryExpressionNode n4 = new MultiplicationExpressionNode(three, nullNode);
        BinaryExpressionNode n5 = new MultiplicationExpressionNode(two,two);

        assertEquals(new Value("0"),n1.evaluate());
        assertEquals(new Value("0"),n2.evaluate());
        assertEquals(new Value("6"),n3.evaluate());
        assertEquals(new NullValue(),n4.evaluate());
        assertEquals(new Value("4"),n5.evaluate());
    }
}