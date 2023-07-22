package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Value;

public class FunctionExpressionNode implements ExpressionNode {
    public FunctionExpressionNode(ExpressionNode[] parameters) throws FunctionParameterCountException, SymbolNameException {
        this.parameters = parameters;
        if (parameters.length < 3) {
            throw new FunctionParameterCountException(3,parameters.length);
        }
        this.name = parameters[0].evaluate();
        if (!name.isWord()) {
            throw new SymbolNameException(name);
        }
        this.value = new Value("FunctionExpression: " + name.toString());
    }
    @Override
    public Value evaluate() {
        return value;
    }
    public Value call(ListExpressionNode parameterValues) throws MissingParametersException, MismatchParameterCountException {
        ListExpressionNode parameterNames;
        try {
            parameterNames = (ListExpressionNode) parameters[1];
        } catch (Exception e) {
            throw new MissingParametersException(value.toString());
        }
        if (parameterValues.values.length != parameterNames.values.length) {
            throw new MismatchParameterCountException(parameterValues.values.length,parameterNames.values.length);
        }
        Value lastValue = new NullValue();
        for (int i = 2; i < parameters.length; i++) {
            lastValue = parameters[i].evaluate();
        }
        return lastValue;
    }
    private final Value value;
    private final Value name;
    public ExpressionNode[] parameters;
}
