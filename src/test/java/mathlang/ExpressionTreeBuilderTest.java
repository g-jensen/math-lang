package mathlang;

import mathlang.expressionnode.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeBuilderTest {

    @Test
    void buildsNullExpressionTreeForInvalidTokens() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[0]);
        assertInstanceOf(NullExpressionNode.class,n1);
        assertEquals(new NullValue(),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"("});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate());

        ExpressionNode n3 = b.build(new String[]{"(",")"});
        assertInstanceOf(NullExpressionNode.class,n3);
        assertEquals(new NullValue(),n3.evaluate());

        ExpressionNode n4 = b.build(new String[]{"+","3"});
        assertInstanceOf(NullExpressionNode.class,n4);
        assertEquals(new NullValue(),n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForConstants() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"1"});
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"12"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value("12"),n2.evaluate());

        ExpressionNode n3 = b.build(new String[]{"(","3",")"});
        assertInstanceOf(ConstantExpressionNode.class,n3);
        assertEquals(new Value("3"),n3.evaluate());

        ExpressionNode n4 = b.build(new String[]{"(","-31",")"});
        assertInstanceOf(ConstantExpressionNode.class,n4);
        assertEquals(new Value("-31"),n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForSymbols() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"e"});
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value(Double.toString(Math.exp(1))),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"tao"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value(Double.toString(2 * Math.PI)),n2.evaluate());
    }

    @Test
    void buildsExpressionTreeForFunctionsWithLiterals() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"+","1","1"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("2"),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"+","1","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("3"),n2.evaluate());

        ExpressionNode n3 = b.build(new String[]{"(","+","2","3",")"});
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("5"),n3.evaluate());
    }

    @Test
    void buildsExpressionTreeForFunctionsWithSymbols() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"cos","tao"});
        assertInstanceOf(CosineExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"/","tao","2"});
        assertInstanceOf(DivisionExpressionNode.class,n2);
        assertEquals(new Value(Double.toString(Math.PI)),n2.evaluate());
    }
    
    @Test
    void buildsNullExpressionForNonExistentFunctionCall() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"p","1"});
        assertInstanceOf(NullExpressionNode.class,n1);
        assertEquals(new NullValue(),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"p","1","2"});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate());
    }

    @Test
    void buildsExpressionTreeForNestedFunctions() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"+","1","(","+","1","1",")"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("3"),n1.evaluate());

        ExpressionNode n2 = b.build(new String[]{"+","(","+","1","1",")","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("4"),n2.evaluate());

        String[] t1 = {"+","(","+","(","+","2","3",")","1",")","2"};
        ExpressionNode n3 = b.build(t1);
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("8"),n3.evaluate());

        String[] t2 = {"(","/","e","(","+","tao","2",")",")"};
        ExpressionNode n4 = b.build(t2);
        assertInstanceOf(DivisionExpressionNode.class,n4);
        assertEquals(new Value("0.32816866068454725"),n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForDef() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"def","greg","5"});
        assertInstanceOf(DefinitionExpressionNode.class,n1);
        assertEquals(new Value("5"),n1.evaluate());

        String[] t1 = {"(","def","hello","(","+","1","3",")",")"};
        ExpressionNode n2 = b.build(t1);
        assertInstanceOf(DefinitionExpressionNode.class,n2);
        assertEquals(new Value("4"),n2.evaluate());
    }

    @Test
    void addsDefinedSymbolToMap() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        assertFalse(b.definedSymbols.containsKey("greg"));
        b.build(new String[]{"def","greg","5"});
        assertTrue(b.definedSymbols.containsKey("greg"));
        assertEquals(new Value("5"),b.definedSymbols.get("greg"));

        assertFalse(b.definedSymbols.containsKey("hello"));
        b.build(new String[]{"def","hello","(","+","2","5",")"});
        assertTrue(b.definedSymbols.containsKey("hello"));
        assertEquals(new Value("7"),b.definedSymbols.get("hello"));
    }

    @Test
    void buildsExpressionTreeForList() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"[","]"});
        assertInstanceOf(ListExpressionNode.class,n1);
        ListExpressionNode l1 = (ListExpressionNode)n1;
        assertEquals(new Value("[]"),l1.evaluate());
        assertArrayEquals(l1.values,new Value[0]);

        ExpressionNode n2 = b.build(new String[]{"[","1","]"});
        assertInstanceOf(ListExpressionNode.class,n2);
        ListExpressionNode l2 = (ListExpressionNode)n2;
        assertEquals(new Value("[1]"),l2.evaluate());
        assertArrayEquals(l2.values,new Value[]{new Value("1")});

        ExpressionNode n3 = b.build(new String[]{"[","1","2","3","]"});
        assertInstanceOf(ListExpressionNode.class,n3);
        ListExpressionNode l3 = (ListExpressionNode)n3;
        assertEquals(new Value("[1 2 3]"),l3.evaluate());
        Value[] v3 = {new Value("1"),new Value("2"),new Value("3")};
        assertArrayEquals(l3.values,v3);

        ExpressionNode n4 = b.build(new String[]{"[","greg","ham","g","poop","]"});
        assertInstanceOf(ListExpressionNode.class,n4);
        ListExpressionNode l4 = (ListExpressionNode)n4;
        assertEquals(new Value("[greg ham g poop]"),l4.evaluate());
        Value[] v4 = {new Value("greg"),new Value("ham"),new Value("g"),new Value("poop")};
        assertArrayEquals(l4.values,v4);
    }
}