package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantExpressionNodeTest {

    @Test
    void evaluatesToConstant() {
        BinaryExpressionNode n1 = new ConstantExpressionNode(new Value("0"));
        BinaryExpressionNode n2 = new ConstantExpressionNode(new Value("1"));
        BinaryExpressionNode n3 = new ConstantExpressionNode(new Value("-1"));

        assertEquals(new Value("0"),n1.evaluate());
        assertEquals(new Value("1"),n2.evaluate());
        assertEquals(new Value("-1"),n3.evaluate());
    }
}