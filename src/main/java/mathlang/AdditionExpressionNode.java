package mathlang;

import java.util.Objects;

public class AdditionExpressionNode extends BinaryExpressionNode{
    public AdditionExpressionNode(BinaryExpressionNode left, BinaryExpressionNode right) {
        super(left, right);
    }
    public Integer evaluate() {
        Integer a = left.evaluate();
        Integer b = right.evaluate();
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return null;
        } else {
            return a + b;
        }
    }
}
