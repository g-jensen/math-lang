package mathlang;

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
    void createsNullNodeIfTokenIsNotSpecialAndNotNumber() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"p"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = {"greg"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsNullNodeIfMismatchParameterCount() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"+", "1"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t1,0));

        String[] t2 = {"-", "1"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t2,0));
    }

    @Test
    void createsAdditionNodeIfTokenIsPlus() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"+","1","2"};
        assertInstanceOf(AdditionExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsSubtractionNodeIfTokenIsMinus() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"-","2","1"};
        assertInstanceOf(SubtractionExpressionNode.class,factory.createNode(t1,0));
    }
}