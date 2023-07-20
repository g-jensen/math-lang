package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeBuilderTest {

    @Test
    void buildsNullExpressionTreeForInvalidTokens() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode n1 = b.build(new String[0]);
        assertInstanceOf(NullExpressionNode.class,n1);
        assertNull(n1.evaluate());

        BinaryExpressionNode n2 = b.build(new String[]{"("});
        assertInstanceOf(NullExpressionNode.class,n2);
        assertNull(n2.evaluate());

        BinaryExpressionNode n3 = b.build(new String[]{"(",")"});
        assertInstanceOf(NullExpressionNode.class,n3);
        assertNull(n3.evaluate());

        BinaryExpressionNode n4 = b.build(new String[]{"+","3"});
        assertInstanceOf(NullExpressionNode.class,n4);
        assertNull(n4.evaluate());
    }

    @Test
    void buildsExpressionTreeForConstants() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode r1 = b.build(new String[]{"1"});
        assertInstanceOf(ConstantExpressionNode.class,r1);
        assertEquals(1,r1.evaluate());

        BinaryExpressionNode r2 = b.build(new String[]{"12"});
        assertInstanceOf(ConstantExpressionNode.class,r2);
        assertEquals(12,r2.evaluate());

        BinaryExpressionNode r3 = b.build(new String[]{"(","3",")"});
        assertInstanceOf(ConstantExpressionNode.class,r3);
        assertEquals(3,r3.evaluate());

        BinaryExpressionNode r4 = b.build(new String[]{"(","-31",")"});
        assertInstanceOf(ConstantExpressionNode.class,r4);
        assertEquals(-31,r4.evaluate());
    }

    @Test
    void buildsExpressionTreeForAdditionWithLiterals() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode r1 = b.build(new String[]{"+","1","1"});
        assertInstanceOf(AdditionExpressionNode.class,r1);
        assertEquals(2,r1.evaluate());

        BinaryExpressionNode r2 = b.build(new String[]{"+","1","2"});
        assertInstanceOf(AdditionExpressionNode.class,r2);
        assertEquals(3,r2.evaluate());

        BinaryExpressionNode r3 = b.build(new String[]{"(","+","2","3",")"});
        assertInstanceOf(AdditionExpressionNode.class,r3);
        assertEquals(5,r3.evaluate());
    }

    @Test
    void buildsExpressionTreeForNestedAddition() {
        ExpressionTreeBuilder b = new ExpressionTreeBuilder();

        BinaryExpressionNode r1 = b.build(new String[]{"+","1","(","+","1","1",")"});
        assertInstanceOf(AdditionExpressionNode.class,r1);
        assertEquals(3,r1.evaluate());

        BinaryExpressionNode r2 = b.build(new String[]{"+","(","+","1","1",")","2"});
        assertInstanceOf(AdditionExpressionNode.class,r2);
        assertEquals(4,r2.evaluate());

        String[] t1 = new String[]{"+","(","+","(","+","2","3",")","1",")","2"};
        BinaryExpressionNode r3 = b.build(t1);
        assertInstanceOf(AdditionExpressionNode.class,r3);
        assertEquals(8,r3.evaluate());
    }
}