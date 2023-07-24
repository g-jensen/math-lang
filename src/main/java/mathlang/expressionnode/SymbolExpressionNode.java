package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

public class SymbolExpressionNode implements ExpressionNode {
    public SymbolExpressionNode(String name) {
        this.name = name;
    }
    @Override
    public Value evaluate(Scope scope) {
        if (!scope.definedSymbols.containsKey(name)) {
            return new NullValue();
        } else {
            return scope.definedSymbols.get(name);
        }
    }
    private String name;
}
