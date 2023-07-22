package mathlang.expressionnode;

public class MismatchParameterCountException extends Throwable {
    public MismatchParameterCountException(int length, int expectedLength) {
        super("Mismatched parameter count: " + length + " when expected " + expectedLength);
    }
}
