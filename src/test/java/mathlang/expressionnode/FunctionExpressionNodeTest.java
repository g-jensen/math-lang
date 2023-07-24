package mathlang.expressionnode;

import mathlang.ExpressionParser;
import mathlang.Scope;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionNodeTest {
    @Test
    void evaluatesWithNoParameters() {
        ListExpressionNode l = new ListExpressionNode(new Value[0]);
        ExpressionNode body = new ConstantExpressionNode(new Value("1"));
        ExpressionNode e = new FunctionExpressionNode(l, body);
        assertEquals(new Value("1"),e.evaluate(new Scope()));
    }

    @Test
    void evaluatesWithParameters() throws MismatchParameterCountException {
        Value[] params = new Value[]{new Value("a"),new Value("b")};
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));
        ExpressionNode two = new ConstantExpressionNode(new Value("2"));
        ListExpressionNode l = new ListExpressionNode(params);
        ExpressionNode a = new SymbolExpressionNode("a");
        ExpressionNode b = new SymbolExpressionNode("b");
        ExpressionNode body = new AdditionExpressionNode(a,b);
        FunctionExpressionNode f = new FunctionExpressionNode(l, body);

        f.setParameterValues(new ExpressionNode[]{one,two});
        assertEquals(new Value("3"),f.evaluate(new Scope()));
    }
}