package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantExpressionNodeTest {

    @Test
    void evaluatesToConstant() {
        BinaryExpressionNode n1 = new ConstantExpressionNode(0);
        BinaryExpressionNode n2 = new ConstantExpressionNode(1);
        BinaryExpressionNode n3 = new ConstantExpressionNode(-1);

        assertEquals(0,n1.evaluate());
        assertEquals(1,n2.evaluate());
        assertEquals(-1,n3.evaluate());
    }
}