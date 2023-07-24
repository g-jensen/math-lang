package mathlang;

import mathlang.expressionnode.FunctionExpressionNode;
import mathlang.expressionnode.FunctionParameterCountException;
import mathlang.expressionnode.SymbolNameException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScopeTest {

    @Test
    void initializesAScope() throws FunctionParameterCountException, SymbolNameException {
        Value v = new Value("hello");
        FunctionExpressionNode f = new FunctionExpressionNode(new String[]{"fun","a","[","]","1"});
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