package mathlang;

import mathlang.expressionnode.FunctionExpressionNode;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    public Scope() {
        this.definedSymbols = new HashMap<>();
        this.definedFunctions = new HashMap<>();
    }
    public Scope(Map<String,Value> definedSymbols, Map<String,FunctionExpressionNode> definedFunctions) {
        this.definedSymbols = new HashMap<>();
        this.definedFunctions = new HashMap<>();
        this.definedSymbols.putAll(definedSymbols);
        this.definedFunctions.putAll(definedFunctions);
    }
    public Scope(Scope scope) {
        this.definedSymbols = new HashMap<>();
        this.definedFunctions = new HashMap<>();
        this.definedSymbols.putAll(scope.definedSymbols);
        this.definedFunctions.putAll(scope.definedFunctions);
    }
    public Map<String, Value> definedSymbols;
    public Map<String, FunctionExpressionNode> definedFunctions;
}
