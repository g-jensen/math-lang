package mathlang.expressionnode;

import mathlang.ExpressionNodeFactory;
import mathlang.ExpressionTreeBuilder;
import mathlang.NullValue;
import mathlang.Value;

import java.util.ArrayList;
import java.util.Arrays;


public class FunctionExpressionNode implements ExpressionNode {
    public FunctionExpressionNode(String[] tokens) throws FunctionParameterCountException, SymbolNameException {
        this.tokens = tokens;
        this.scope = new ExpressionTreeBuilder();
        this.name = new Value(tokens[1]);
        this.parameters = scope.nodeFactory.createNode(tokens,2);
        try {
            this.parameterNames = ((ListExpressionNode)parameters).values;
            this.parameterCount = parameterNames.length;
        } catch (Exception e) {
            throw new FunctionParameterCountException(1,0);
        }
        if (!name.isWord()) {
            throw new SymbolNameException(name);
        }
        this.value = new Value("FunctionExpression: " + name.toString());
    }
    @Override
    public Value evaluate() {
        return value;
    }
    public ExpressionNode call(ExpressionNode[] parameters) throws MissingParametersException, MismatchParameterCountException {
        if (parameters.length != parameterNames.length) {
            throw new MismatchParameterCountException(parameters.length,parameterNames.length);
        }
        int startOfOperation = parameterNames.length+4;
        for (int i = startOfOperation; i < tokens.length; i++) {
            for (int k = 0; k < parameterNames.length; k++) {
                if (tokens[i].equals(parameterNames[k].toString())) {
                    scope.nodeFactory.definedSymbols.put(tokens[i],parameters[k].evaluate());
                }
            }
        }
        return scope.build(Arrays.copyOfRange(tokens,startOfOperation, tokens.length));
    }
    private final String[] tokens;
    private final Value value;
    private final ExpressionNode parameters;
    private final Value[] parameterNames;
    public final Value name;
    public final int parameterCount;
    public ExpressionTreeBuilder scope;
}
