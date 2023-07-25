package mathlang.expressionnode;

import mathlang.*;

public class FunctionExpressionNode implements ExpressionNode {
    public FunctionExpressionNode(ListExpressionNode params, ExpressionNode body) {
        this.body = body;
        this.params = params;
        this.parameterCount = params.values.length;
        this.scope = new Scope();
    }
    @Override
    public Value evaluate(Scope scope) {
        Scope newScope = new Scope(scope);
        newScope.definedSymbols.putAll(this.scope.definedSymbols);
        return body.evaluate(newScope);
    }
    public void addParametersToScope(ExpressionNode[] values) throws MismatchParameterCountException {
        try {
            for (int i = 0; i < params.values.length; i++) {
                scope.definedSymbols.put(params.values[i].toString(),values[i].evaluate(scope));
            }
        } catch (Exception e) {
            throw new MismatchParameterCountException(values.length,params.values.length);
        }
    }
    public Scope scope;
    public int parameterCount;
    public final ExpressionNode body;
    private ListExpressionNode params;
}
