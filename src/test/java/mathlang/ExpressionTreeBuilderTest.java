package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeBuilderTest {

    @Test
    void buildsNullExpressionTreeForInvalidTokens() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[0]);
        assertInstanceOf(NullExpressionNode.class,n1);
        assertEquals(new NullValue(),n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"("});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate());

        BinaryExpressionNode n3 = b.build(new String[]{"(",")"});
        assertInstanceOf(NullExpressionNode.class,n3);
        assertEquals(new NullValue(),n3.evaluate());

        BinaryExpressionNode n4 = b.build(new String[]{"+","3"});
        assertInstanceOf(NullExpressionNode.class,n4);
        assertEquals(new NullValue(),n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForConstants() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[]{"1"});
        assertInstanceOf(ConstantExpressionNode.class,n1);
        assertEquals(new Value("1"),n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"12"});
        assertInstanceOf(ConstantExpressionNode.class,n2);
        assertEquals(new Value("12"),n2.evaluate());

        BinaryExpressionNode n3 = b.build(new String[]{"(","3",")"});
        assertInstanceOf(ConstantExpressionNode.class,n3);
        assertEquals(new Value("3"),n3.evaluate());

        BinaryExpressionNode n4 = b.build(new String[]{"(","-31",")"});
        assertInstanceOf(ConstantExpressionNode.class,n4);
        assertEquals(new Value("-31"),n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForAdditionWithLiterals() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[]{"+","1","1"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("2"),n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"+","1","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("3"),n2.evaluate());

        BinaryExpressionNode n3 = b.build(new String[]{"(","+","2","3",")"});
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("5"),n3.evaluate());
    }
    
    @Test
    void buildsNullExpressionForNonExistentFunctionCall() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[]{"p","1"});
        assertInstanceOf(NullExpressionNode.class,n1);
        assertEquals(new NullValue(),n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"p","1","2"});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertEquals(new NullValue(),n2.evaluate());
    }

    @Test
    void buildsExpressionTreeForNestedAddition() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[]{"+","1","(","+","1","1",")"});
        assertInstanceOf(AdditionExpressionNode.class,n1);
        assertEquals(new Value("3"),n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"+","(","+","1","1",")","2"});
        assertInstanceOf(AdditionExpressionNode.class,n2);
        assertEquals(new Value("4"),n2.evaluate());

        String[] t1 = new String[]{"+","(","+","(","+","2","3",")","1",")","2"};
        BinaryExpressionNode n3 = b.build(t1);
        assertInstanceOf(AdditionExpressionNode.class,n3);
        assertEquals(new Value("8"),n3.evaluate());
    }
}