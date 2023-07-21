package mathlang.expressionnode;

public class FunctionParameterCountException extends Throwable {
    public FunctionParameterCountException(int leastPossibleCount,int parameterCount) {
        super("Invalid parameter count: " + parameterCount + ". Parameter count must be at least " + leastPossibleCount);
    }
}
