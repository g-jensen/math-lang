package mathlang;

import mathlang.expressionnode.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionNodeFactoryTest {

    @Test
    void createsConstantNodeIfTokenIsNumber() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = {"2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsSymbolNodeIfTokenIsNotSpecialAndNotNumber() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"p"};
        assertInstanceOf(SymbolExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = {"greg"};
        assertInstanceOf(SymbolExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsConstantNodeIfMismatchParameterCount() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"+", "1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = {"-", "1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t2,0));

        String[] t3 = {"*", "1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t3,0));

        String[] t4 = {"/", "1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t4,0));

        String[] t5 = {"exp"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t5,0));

        String[] t6 = {"ln"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t6,0));

        String[] t7 = {"sin"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t7,0));

        String[] t8 = {"cos"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t8,0));
    }

    @Test
    void createsConstantNodeIfTokenIsPlus() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"+","1","2"};
        ExpressionNode n = factory.createNode(t1,0);
        assertInstanceOf(ConstantExpressionNode.class,n);
    }

    @Test
    void createsConstantNodeIfTokenIsMinus() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"-","2","1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsAsterisk() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"*","2","1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsSlash() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"/","2","1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsExp() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"exp","2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsLn() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"ln","2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsSin() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"sin","2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsCos() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"cos","2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsE() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"e"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsConstantNodeIfTokenIsTau() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"tau"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsDefinitionNodeIfTokenIsDef() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"def","greg","10"};
        assertInstanceOf(DefinitionExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void addsDefinedSymbolToMap() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        assertFalse(factory.scope.definedSymbols.containsKey("greg"));
        factory.createNode(new String[]{"def","greg","5"},0);
        assertTrue(factory.scope.definedSymbols.containsKey("greg"));
        assertEquals(new Value("5"),factory.scope.definedSymbols.get("greg"));

        assertFalse(factory.scope.definedSymbols.containsKey("hello"));
        factory.createNode(new String[]{"def","hello","(","+","2","5",")"},0);
        assertTrue(factory.scope.definedSymbols.containsKey("hello"));
        assertEquals(new Value("7"),factory.scope.definedSymbols.get("hello"));
    }
}