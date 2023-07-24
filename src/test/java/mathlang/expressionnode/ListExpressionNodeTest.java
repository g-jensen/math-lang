package mathlang.expressionnode;

import mathlang.Scope;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListExpressionNodeTest {

    @Test
    void evaluates() {
        Scope s = new Scope();
        Value[] v1 = {};
        ExpressionNode n1 = new ListExpressionNode(v1);
        assertEquals(new Value("[]"),n1.evaluate(s));

        Value[] v2 = {new Value("1"), new Value("2"), new Value("3")};
        ExpressionNode n2 = new ListExpressionNode(v2);
        assertEquals(new Value("[1 2 3]"),n2.evaluate(s));
    }
}