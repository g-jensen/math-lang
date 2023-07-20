package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullExpressionNodeTest {

    @Test
    void alwaysEvaluatesToNull() {
        NullExpressionNode n = new NullExpressionNode();
        assertNull(n.evaluate());
    }
}