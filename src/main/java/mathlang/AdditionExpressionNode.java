package mathlang;

import java.util.Objects;

public class AdditionExpressionNode extends BinaryExpressionNode{
    public AdditionExpressionNode(BinaryExpressionNode left, BinaryExpressionNode right) {
        super(left, right);
    }
    public Value evaluate() {
        Value a = left.evaluate();
        Value b = right.evaluate();
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return null;
        } else {
            Double val = a.toDouble() + b.toDouble();
            return new Value(val.toString());
        }
    }
}
