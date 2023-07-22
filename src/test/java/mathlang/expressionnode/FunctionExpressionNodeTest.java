package mathlang.expressionnode;

import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionNodeTest {

    @Test
    void throwsParameterCountException() {
        ExpressionNode[] p1 = new ExpressionNode[]{
                new ConstantExpressionNode(new Value("greg"))
        };
        assertThrows(
                FunctionParameterCountException.class,
                ()-> new FunctionExpressionNode(p1)
        );
    }

    @Test
    void throwsFunctionNameException() {
        ExpressionNode[] p1 = new ExpressionNode[]{
                new ConstantExpressionNode(new Value("1")),
                new ConstantExpressionNode(new Value("1")),
                new ConstantExpressionNode(new Value("1"))
        };
        assertThrows(
                SymbolNameException.class,
                ()-> new FunctionExpressionNode(p1)
        );
    }

    @Test
    void evaluates() throws SymbolNameException, FunctionParameterCountException {
        ExpressionNode[] p1 = new ExpressionNode[]{
                new ConstantExpressionNode(new Value("greg")),
                new ConstantExpressionNode(new Value("greg")),
                new ConstantExpressionNode(new Value("1"))
        };
        ExpressionNode e = new FunctionExpressionNode(p1);
        assertEquals(new Value("FunctionExpression: greg"),e.evaluate());
    }

    @Test
    void throwsMissingParametersException() throws FunctionParameterCountException, SymbolNameException {
        ExpressionNode[] p1 = new ExpressionNode[]{
                new ConstantExpressionNode(new Value("functionName")),
                new ConstantExpressionNode(new Value("1")),
                new ConstantExpressionNode(new Value("1"))
        };
        FunctionExpressionNode e = new FunctionExpressionNode(p1);
        assertThrows(MissingParametersException.class,
                ()->e.call(new ListExpressionNode(new Value[0]))
        );
    }

    @Test
    void throwsMismatchParametersException() throws FunctionParameterCountException, SymbolNameException {
        Value[] v1 = {new Value("p1")};
        ExpressionNode[] p1 = new ExpressionNode[]{
                new ConstantExpressionNode(new Value("functionName")),
                new ListExpressionNode(v1),
                new ConstantExpressionNode(new Value("1"))
        };
        FunctionExpressionNode e = new FunctionExpressionNode(p1);
        assertThrows(MismatchParameterCountException.class,
                ()->e.call(new ListExpressionNode(new Value[0]))
        );
    }

    @Test
    void callsFunctionWithNoParametersOrDefs() throws FunctionParameterCountException, SymbolNameException, MissingParametersException, MismatchParameterCountException {
        ExpressionNode name = new ConstantExpressionNode(new Value("functionName"));
        ExpressionNode params = new ListExpressionNode(new Value[0]);
        ExpressionNode one = new ConstantExpressionNode(new Value("1"));

        ExpressionNode[] p1 = new ExpressionNode[]{name, params, one};
        FunctionExpressionNode e1 = new FunctionExpressionNode(p1);
        assertEquals(new Value("1"),e1.call(new ListExpressionNode(new Value[0])));

        ExpressionNode[] p2 = new ExpressionNode[]{
                name,
                params,
                one,
                new AdditionExpressionNode(one,one)
        };
        FunctionExpressionNode e2 = new FunctionExpressionNode(p2);
        assertEquals(new Value("2"),e2.call(new ListExpressionNode(new Value[0])));
    }
}