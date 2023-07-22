package mathlang.expressionnode;

import mathlang.ExpressionParser;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionNodeTest {

    @Test
    void throwsParameterCountException() {
        ExpressionParser p = new ExpressionParser();
        String def = "fun 1 2 3";
        assertThrows(
                FunctionParameterCountException.class,
                ()-> new FunctionExpressionNode(p.getTokens(def))
        );
    }

    @Test
    void throwsFunctionNameException() {
        ExpressionParser p = new ExpressionParser();
        String def = "fun 1 [] 2";
        assertThrows(
                SymbolNameException.class,
                ()-> new FunctionExpressionNode(p.getTokens(def))
        );
    }

    @Test
    void evaluates() throws SymbolNameException, FunctionParameterCountException {
        ExpressionParser p = new ExpressionParser();
        String def = "fun greg [] 2";
        ExpressionNode e = new FunctionExpressionNode(p.getTokens(def));
        assertEquals(new Value("FunctionExpression: greg"),e.evaluate());
    }

    @Test
    void throwsMismatchParametersException() throws FunctionParameterCountException, SymbolNameException {
        ExpressionParser p = new ExpressionParser();
        String def = "fun greg [a] 1";
        FunctionExpressionNode e = new FunctionExpressionNode(p.getTokens(def));
        assertThrows(MismatchParameterCountException.class,
                ()->e.call(new ExpressionNode[0])
        );
    }

    @Test
    void callsFunctionWithNoParametersOrDefs() throws FunctionParameterCountException, SymbolNameException, MissingParametersException, MismatchParameterCountException {
        ExpressionParser p = new ExpressionParser();
        String def = "fun hello [] 2";
        FunctionExpressionNode f = new FunctionExpressionNode(p.getTokens(def));
        assertEquals(new Value("2"),f.call(new ExpressionNode[0]).evaluate());
    }
}