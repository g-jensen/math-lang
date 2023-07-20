package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionNodeFactoryTest {

    @Test
    void createsConstantNodeIfTokenIsNumber() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = new String[]{"1"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = new String[]{"2"};
        assertInstanceOf(ConstantExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsNullNodeIfTokenIsNotSpecialAndNotNumber() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = new String[]{"p"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = new String[]{"greg"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsAdditionNodeIfTokenIsPlus() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = new String[]{"+","1","2"};
        assertInstanceOf(AdditionExpressionNode.class,factory.createNode(t1,0));
    }
}