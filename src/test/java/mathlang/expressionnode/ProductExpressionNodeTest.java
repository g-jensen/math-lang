package mathlang.expressionnode;

import mathlang.ExpressionNodeFactory;
import mathlang.ExpressionTreeBuilder;
import mathlang.NullValue;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductExpressionNodeTest {

    @Test
    void evaluates() {
        ExpressionNodeFactory f = new ExpressionNodeFactory(new ExpressionTreeBuilder());
        ExpressionNode zero = new ConstantExpressionNode(new Value("0"));
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode negOne = new ConstantExpressionNode(new Value("-1"));
        ExpressionNode three = new ConstantExpressionNode(new Value("3"));
        ExpressionNode fun = new ConstantExpressionNode(new Value("identity"));
        ExpressionNode e1 = new ProductExpressionNode(one,one,fun);
        ExpressionNode e2 = new ProductExpressionNode(one,negOne,fun);
        ExpressionNode e3 = new ProductExpressionNode(fun,negOne,fun);
        ExpressionNode e4 = new ProductExpressionNode(zero,fun,fun);
        ExpressionNode e5 = new ProductExpressionNode(zero,zero,zero);
        ExpressionNode e6 = new ProductExpressionNode(one,three,fun);

        assertEquals(new Value("1"),e1.evaluate(f.scope));
        assertEquals(new Value("1"),e2.evaluate(f.scope));
        assertEquals(new Value("0"),e3.evaluate(f.scope));
        assertEquals(new Value("0"),e4.evaluate(f.scope));
        assertEquals(new NullValue(),e5.evaluate(f.scope));
        assertEquals(new Value("6"),e6.evaluate(f.scope));
    }
}