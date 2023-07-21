package mathlang.expressionnode;

public class FunctionExpressionParameterCountException extends Throwable {
    public FunctionExpressionParameterCountException(int parameterCount) {
        super("Invalid parameter count: " + parameterCount + ". Parameter count must be greater than 2");
    }
}
