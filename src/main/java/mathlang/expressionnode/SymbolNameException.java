package mathlang.expressionnode;

import mathlang.Value;

public class SymbolNameException extends Throwable {
    public SymbolNameException(Value name) {
        super("Invalid function name: \"" + name.toString() + "\". Name must be a word");
    }
}
