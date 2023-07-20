package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionExpressionNodeTest {

    @Test
    void evaluatesToSum() {
        ConstantExpressionNode one = new ConstantExpressionNode(1);
        ConstantExpressionNode two = new ConstantExpressionNode(2);
        ConstantExpressionNode three = new ConstantExpressionNode(3);
        BinaryExpressionNode n1 = new AdditionExpressionNode(one, one);
        BinaryExpressionNode n2 = new AdditionExpressionNode(one, two);
        BinaryExpressionNode n3 = new AdditionExpressionNode(two, three);

        assertEquals(2,n1.evaluate());
        assertEquals(3,n2.evaluate());
        assertEquals(5,n3.evaluate());
    }
}