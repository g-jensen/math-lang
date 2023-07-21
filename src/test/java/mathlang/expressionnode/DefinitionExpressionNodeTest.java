package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefinitionExpressionNodeTest {

    @Test
    void EvaluatesToNullIfNotAWord() {
        ExpressionNode four = new ConstantExpressionNode(new Value("4"));
        ExpressionNode three = new ConstantExpressionNode(new Value("3"));

        ExpressionNode n1 = new DefinitionExpressionNode(four,three);
        assertEquals(new NullValue(),n1.evaluate());
    }

    @Test
    void EvaluatesToValueIfWord() {
        ExpressionNode greg = new ConstantExpressionNode(new Value("greg"));
        ExpressionNode three = new ConstantExpressionNode(new Value("3"));

        ExpressionNode n1 = new DefinitionExpressionNode(greg,three);
        assertEquals(new Value("3"),n1.evaluate());
    }
}