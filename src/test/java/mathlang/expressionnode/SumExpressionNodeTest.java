package mathlang.expressionnode;

import mathlang.ExpressionNodeFactory;
import mathlang.ExpressionTreeBuilder;
import mathlang.NullValue;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumExpressionNodeTest {

    @Test
    void evaluates() {
        ExpressionNodeFactory f = new ExpressionNodeFactory(new ExpressionTreeBuilder());
        ExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ExpressionNode negOne = new ConstantExpressionNode(new Value("-1"));
        ExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ExpressionNode fun = new ConstantExpressionNode(new Value("exp"));
        ExpressionNode e1 = new SumExpressionNode(zero,zero,fun);
        ExpressionNode e2 = new SumExpressionNode(zero,negOne,fun);
        ExpressionNode e3 = new SumExpressionNode(fun,negOne,fun);
        ExpressionNode e4 = new SumExpressionNode(zero,fun,fun);
        ExpressionNode e5 = new SumExpressionNode(zero,zero,zero);
        ExpressionNode e6 = new SumExpressionNode(zero,three,fun);

        assertEquals(new Value("1"),e1.evaluate(f.scope));
        assertEquals(new Value("0"),e2.evaluate(f.scope));
        assertEquals(new Value("0"),e3.evaluate(f.scope));
        assertEquals(new Value("0"),e4.evaluate(f.scope));
        assertEquals(new NullValue(),e5.evaluate(f.scope));
        assertEquals(new Value("31.19287485057736"),e6.evaluate(f.scope));
    }
}