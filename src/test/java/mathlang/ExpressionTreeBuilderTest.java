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
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value("2"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"+","1","2"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value("3"),n2.evaluate(s));

        ExpressionNode n3 = b.build(new String[]{"(","+","2","3",")"});
        assertInstanceOf(ConstantExpressionNode.class,n3);
        assertEquals(new Value("5"),n3.evaluate(s));
    }

    @Test
    void buildsExpressionTreeForFunctionsWithSymbols() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();
        Scope s = new Scope();

        ExpressionNode n1 = b.build(new String[]{"cos","tau"});
        assertEquals(new Value("1"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"/","tau","2"});
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
        assertEquals(new Value("3"),n1.evaluate(s));

        ExpressionNode n2 = b.build(new String[]{"+","(","+","1","1",")","2"});
        assertEquals(new Value("4"),n2.evaluate(s));

        String[] t1 = {"+","(","+","(","+","2","3",")","1",")","2"};
        ExpressionNode n3 = b.build(t1);
        assertEquals(new Value("8"),n3.evaluate(s));

        String[] t2 = {"(","/","e","(","+","tau","2",")",")"};
        ExpressionNode n4 = b.build(t2);
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
}