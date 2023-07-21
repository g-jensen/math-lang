package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.expressionnode.NullExpressionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullExpressionNodeTest {

    @Test
    void alwaysEvaluatesToNull() {
        NullExpressionNode n = new NullExpressionNode();
        assertNull(n.evaluate().toDouble());
        assertNull(n.evaluate().toInteger());
        Assertions.assertEquals(new NullValue(),n.evaluate());
    }
}