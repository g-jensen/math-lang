package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import mathlang.expressionnode.BinaryExpressionNode;
import mathlang.expressionnode.ConstantExpressionNode;
import mathlang.expressionnode.SubtractionExpressionNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionExpressionNodeTest {

    @Test
    void evaluatesToDifference() {
        ConstantExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ConstantExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ConstantExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ConstantExpressionNode nullNode = new ConstantExpressionNode(new NullValue());
        BinaryExpressionNode n1 = new SubtractionExpressionNode(one, one);
        BinaryExpressionNode n2 = new SubtractionExpressionNode(one, two);
        BinaryExpressionNode n3 = new SubtractionExpressionNode(three, two);
        BinaryExpressionNode n4 = new SubtractionExpressionNode(one, nullNode);

        Scope s = new Scope();
        assertEquals(new Value("0"),n1.evaluate(s));
        assertEquals(new Value("-1"),n2.evaluate(s));
        assertEquals(new Value("1"),n3.evaluate(s));
        assertEquals(new NullValue(),n4.evaluate(s));
    }
}