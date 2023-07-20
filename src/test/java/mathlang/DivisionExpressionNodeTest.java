package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionExpressionNodeTest {

    @Test
    void evaluatesToQuotient() {
        ConstantExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ConstantExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ConstantExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ConstantExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ConstantExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        BinaryExpressionNode n1 = new DivisionExpressionNode(one, one);
        BinaryExpressionNode n2 = new DivisionExpressionNode(two, one);
        BinaryExpressionNode n3 = new DivisionExpressionNode(two, two);
        BinaryExpressionNode n4 = new DivisionExpressionNode(three, one);
        BinaryExpressionNode n5 = new DivisionExpressionNode(one,zero);
        BinaryExpressionNode n6 = new DivisionExpressionNode(one,nullNode);

        assertEquals(new Value("1"),n1.evaluate());
        assertEquals(new Value("2"),n2.evaluate());
        assertEquals(new Value("1"),n3.evaluate());
        assertEquals(new Value("3"),n4.evaluate());
        assertEquals(new Value("Infinity"),n5.evaluate());
        assertEquals(new NullValue(),n6.evaluate());
    }
}