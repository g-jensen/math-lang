package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExponentialExpressionNodeTest {

    @Test
    void evaluatesToExp() {
        ExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode two = new ConstantExpressionNode(new Value("2"));
        UnaryExpressionNode n1 = new ExponentialExpressionNode(zero);
        UnaryExpressionNode n2 = new ExponentialExpressionNode(one);
        UnaryExpressionNode n3 = new ExponentialExpressionNode(two);

        assertEquals(new Value("1"),n1.evaluate());
        assertEquals(new Value(Double.toString(Math.exp(1))),n2.evaluate());
        assertEquals(new Value(Double.toString(Math.exp(2))),n3.evaluate());
    }
}