package mathlang.expressionnode;

public class MissingParametersException extends Throwable {
    public MissingParametersException(String name) {
        super("Missing parameters in " + name);
    }
}
