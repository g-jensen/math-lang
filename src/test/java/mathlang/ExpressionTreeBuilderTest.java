package mathlang;

import mathlang.expressionnode.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeBuilderTest {

    @Test
    void buildsNullExpressionTreeForInvalidTokens() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[0]);
        assertInstanceOf(NullExpressionNode.class,n1);
        assertEquals(new NullValue(),n1 .evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"("});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate(s));

        ExpressionNode n3 = b.build(new String[]{"(",")"});
        assertInstanceOf(NullExpressionNode.class,n3);
        assertEquals(new NullValue(),n3.evaluate(s));

        ExpressionNode n4 = b.build(new String[]{"+","3"});
        assertInstanceOf(NullExpressionNode.class,n4);
        assertEquals(new NullValue(),n4.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForConstants() {
        Scope s = new Scope();
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"1"});
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"12"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value("12"),n2.evaluate(s));

        ExpressionNode n3 = b.build(new String[]{"(","3",")"});
        assertInstanceOf(ConstantExpressionNode.class,n3);
        assertEquals(new Value("3"),n3.evaluate(s));

        ExpressionNode n4 = b.build(new String[]{"(","-31",")"});
        assertInstanceOf(ConstantExpressionNode.class,n4);
        assertEquals(new Value("-31"),n4.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForSymbols() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"e"});
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value(Double.toString(Math.exp(1))),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"tau"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value(Double.toString(2 * Math.PI)),n2.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForFunctionsWithLiterals() {
        Scope s = new Scope();
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"+","1","1"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("2"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"+","1","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("3"),n2.evaluate(s));

        ExpressionNode n3 = b.build(new String[]{"(","+","2","3",")"});
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("5"),n3.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForFunctionsWithSymbols() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"cos","tau"});
        assertInstanceOf(CosineExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"/","tau","2"});
        assertInstanceOf(DivisionExpressionNode.class,n2);
        assertEquals(new Value(Double.toString(Math.PI)),n2.evaluate(s));
    }
    
    @Test
    void buildsSymbolExpressionForNonExistentFunctionCall() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"p","1"});
        assertInstanceOf(SymbolExpressionNode.class,n1);
        assertEquals(new NullValue(),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"p","1","2"});
        assertInstanceOf(SymbolExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForNestedFunctions() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"+","1","(","+","1","1",")"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("3"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"+","(","+","1","1",")","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("4"),n2.evaluate(s));

        String[] t1 = {"+","(","+","(","+","2","3",")","1",")","2"};
        ExpressionNode n3 = b.build(t1);
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("8"),n3.evaluate(s));

        String[] t2 = {"(","/","e","(","+","tau","2",")",")"};
        ExpressionNode n4 = b.build(t2);
        assertInstanceOf(DivisionExpressionNode.class,n4);
        assertEquals(new Value("0.32816866068454725"),n4.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForDef() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"def","greg","5"});
        assertInstanceOf(DefinitionExpressionNode.class,n1);
        assertEquals(new Value("5"),n1.evaluate(s));

        String[] t1 = {"(","def","hello","(","+","1","3",")",")"};
        ExpressionNode n2 = b.build(t1);
        assertInstanceOf(DefinitionExpressionNode.class,n2);
        assertEquals(new Value("4"),n2.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForList() {
        Scope s = new Scope();
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        ExpressionNode n1 = b.build(new String[]{"[","]"});
        assertInstanceOf(ListExpressionNode.class,n1);
        ListExpressionNode l1 = (ListExpressionNode)n1;
        assertEquals(new Value("[]"),l1.evaluate(s));
        assertArrayEquals(l1.values,new Value[0]);

        ExpressionNode n2 = b.build(new String[]{"[","1","]"});
        assertInstanceOf(ListExpressionNode.class,n2);
        ListExpressionNode l2 = (ListExpressionNode)n2;
        assertEquals(new Value("[1]"),l2.evaluate(s));
        assertArrayEquals(l2.values,new Value[]{new Value("1")});

        ExpressionNode n3 = b.build(new String[]{"[","1","2","3","]"});
        assertInstanceOf(ListExpressionNode.class,n3);
        ListExpressionNode l3 = (ListExpressionNode)n3;
        assertEquals(new Value("[1 2 3]"),l3.evaluate(s));
        Value[] v3 = {new Value("1"),new Value("2"),new Value("3")};
        assertArrayEquals(l3.values,v3);

        ExpressionNode n4 = b.build(new String[]{"[","greg","ham","g","poop","]"});
        assertInstanceOf(ListExpressionNode.class,n4);
        ListExpressionNode l4 = (ListExpressionNode)n4;
        assertEquals(new Value("[greg ham g poop]"),l4.evaluate(s));
        Value[] v4 = {new Value("greg"),new Value("ham"),new Value("g"),new Value("poop")};
        assertArrayEquals(l4.values,v4);
    }

    @Test
    void buildsExpressionTreeForFunctionDef() throws MissingParametersException, MismatchParameterCountException {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        String[] t1 = {"fun","one","[","]","1"};
        ExpressionNode n1 = b.build(t1);
        assertInstanceOf(FunctionExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate(s));

        String[] t2 = {"fun","addTwo","[","n","]","(","+","n","2",")"};
        ExpressionNode n2 = b.build(t2);
        assertInstanceOf(FunctionExpressionNode.class,n2);
        FunctionExpressionNode f2 = (FunctionExpressionNode) n2;
        f2.setParameterValues(new ExpressionNode[]{new ConstantExpressionNode(new Value("1"))});
        assertEquals(new Value("3"),f2.evaluate(s));
    }

    @Test
    void canCallDefinedFunction() {
        Scope s = new Scope();
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        String[] t1 = {"fun","won","[","]","1"};
        b.build(t1);
        String[] t2 = {"won"};
        assertEquals(new Value("1"),b.build(t2).evaluate(s));

        String[] t3 = {"fun","add","[","a","b","]","(","+","a","b",")"};
        b.build(t3);
        String[] t4 = {"add","1","2"};
        assertEquals(new Value("3"),b.build(t4).evaluate(s));
        String[] t5 = {"(","add","1","2",")"};
        assertEquals(new Value("3"),b.build(t5).evaluate(s));
        String[] t6 = {"(","add","(","+","1","1",")","2",")"};
        assertEquals(new Value("4"),b.build(t6).evaluate(s));
    }
}