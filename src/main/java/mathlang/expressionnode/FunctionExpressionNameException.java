package mathlang.expressionnode;

import mathlang.Value;

public class FunctionExpressionNameException extends Throwable {
    public FunctionExpressionNameException(Value name) {
        super("Invalid function name: \"" + name.toString() + "\". Name must be a word");
    }
}
