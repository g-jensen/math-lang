package mathlang.expressionnode;

import mathlang.Value;

import java.util.Arrays;

public class ListExpressionNode implements ExpressionNode {
    public ListExpressionNode(Value[] values) {
        this.values = values;
        String s = String.join(" ", Arrays.stream(values)
                .map(Value::toString)
                .toArray(String[]::new));
        this.value = new Value("[" + s + "]");
    }
    public Value evaluate() {
        return value;
    }
    private Value value;
    public Value[] values;
}
