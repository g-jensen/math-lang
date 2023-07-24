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
        this.scope.definedSymbols.putAll(scope.definedSymbols);
        return body.evaluate(this.scope);
    }
    public void setParameterValues(ExpressionNode[] values) throws MismatchParameterCountException {
        scope = new Scope();
        try {
            for (int i = 0; i < params.values.length; i++) {
                scope.definedSymbols.put(params.values[i].toString(),values[i].evaluate(scope));
            }
        } catch (Exception e) {
            throw new MismatchParameterCountException(values.length,params.values.length);
        }
    }
    public int parameterCount;
    private Scope scope;
    private ExpressionNode body;
    private ListExpressionNode params;
}
