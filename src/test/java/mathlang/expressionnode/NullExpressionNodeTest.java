package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.expressionnode.NullExpressionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullExpressionNodeTest {

    @Test
    void alwaysEvaluatesToNull() {
        Scope s = new Scope();
        NullExpressionNode n = new NullExpressionNode();
        assertNull(n.evaluate(s).toDouble());
        assertNull(n.evaluate(s).toInteger());
        Assertions.assertEquals(new NullValue(),n.evaluate(s));
    }
}