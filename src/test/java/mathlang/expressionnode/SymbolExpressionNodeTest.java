package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolExpressionNodeTest {

    @Test
    void evaluates() {
        Scope s = new Scope();
        Value v = new Value("1");

        ExpressionNode symbol = new SymbolExpressionNode("hi");

        assertEquals(new NullValue(),symbol.evaluate(s));
        s.definedSymbols.put("hi",v);
        assertEquals(v,symbol.evaluate(s));

        ExpressionNode add = new AdditionExpressionNode(new ConstantExpressionNode(v),symbol);
        assertEquals(new Value("2"),add.evaluate(s));
    }
}