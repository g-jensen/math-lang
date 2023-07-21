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

        String[] t3 = {"*", "1"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t3,0));

        String[] t4 = {"/", "1"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t4,0));

        String[] t5 = {"exp"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t5,0));

        String[] t6 = {"ln"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t6,0));

        String[] t7 = {"sin"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t7,0));

        String[] t8 = {"cos"};
        assertInstanceOf(NullExpressionNode.class,factory.createNode(t8,0));
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

    @Test
    void createsSubtractionNodeIfTokenIsAsterisk() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"*","2","1"};
        assertInstanceOf(MultiplicationExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsSubtractionNodeIfTokenIsSlash() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"/","2","1"};
        assertInstanceOf(DivisionExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsExponentialNodeIfTokenIsExp() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"exp","2"};
        assertInstanceOf(ExponentialExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsExponentialNodeIfTokenIsLn() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"ln","2"};
        assertInstanceOf(NaturalLogExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsExponentialNodeIfTokenIsSin() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"sin","2"};
        assertInstanceOf(SineExpressionNode.class,factory.createNode(t1,0));
    }

    @Test
    void createsExponentialNodeIfTokenIsCos() {
        ExpressionNodeFactory factory = new ExpressionNodeFactory(new ExpressionTreeBuilder());

        String[] t1 = {"cos","2"};
        assertInstanceOf(CosineExpressionNode.class,factory.createNode(t1,0));
    }
}