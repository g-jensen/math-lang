package mathlang;

import mathlang.expressionnode.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScopeTest {

    @Test
    void initializesAScope() {
        Value v = new Value("hello");
        ListExpressionNode l = new ListExpressionNode(new Value[0]);
        ExpressionNode body = new ConstantExpressionNode(new Value("1"));
        FunctionExpressionNode f = new FunctionExpressionNode(l,body);
        HashMap<String, Value> symbols = new HashMap<>();
        HashMap<String,FunctionExpressionNode> functions = new HashMap<>();
        functions.put("f",f);

        Scope s1 = new Scope(symbols,functions);
        assertTrue(s1.definedSymbols.isEmpty());
        assertEquals(f,s1.definedFunctions.get("f"));
        symbols.put("v",v);
        assertTrue(s1.definedSymbols.isEmpty());
        s1.definedSymbols.put("v",v);

        Scope s2 = new Scope(s1);
        assertEquals(v,s2.definedSymbols.get("v"));
        assertEquals(f,s2.definedFunctions.get("f"));


    }
}