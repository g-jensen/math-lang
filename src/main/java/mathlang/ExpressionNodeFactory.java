package mathlang;

import mathlang.expressionnode.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.scope = new Scope();
        this.treeBuilder = treeBuilder;
        this.specialTokens = new String[]{"(",")","def"};
        this.scope.definedSymbols = new HashMap<>();
        addDefinedSymbols();
        this.scope.definedFunctions = new HashMap<>();
        addDefinedFunctions();
    }
    public ExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (Utils.isNumeric(token))
            return createConstantNodeFromNumber(tokens, tokenIndex);
        else if (scope.definedSymbols.containsKey(token))
            return createConstantNodeFromSymbol(tokens, tokenIndex);
        else if (scope.definedFunctions.containsKey(token))
            return createNodeFromFunctionCall(tokens,tokenIndex);
        else if (!isSpecial(token))
            return new SymbolExpressionNode(token);
        else if (token.equals("def"))
            return createDefinitionNode(tokens,tokenIndex);
        return null;
    }
    private boolean isSpecial(String token) {
        return Arrays.asList(specialTokens).contains(token);
    }
    private ExpressionNode createConstantNodeFromNumber(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(new Value(tokens[tokenIndex]));
    }
    private ExpressionNode createConstantNodeFromSymbol(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(scope.definedSymbols.get(tokens[tokenIndex]));
    }
    private ExpressionNode createNodeFromFunctionCall(String[] tokens, int tokenIndex) {
        try {
            FunctionExpressionNode f = scope.definedFunctions.get(tokens[tokenIndex]);
            ArrayList<ExpressionNode> params = new ArrayList<>();
            int index = tokenIndex;
            while (index < tokens.length-1) {
                String[] t = treeBuilder.nextParameter(tokens,index);
                index += t.length;
                params.add(treeBuilder.build(t));
            }
            f.addParametersToScope(params.toArray(new ExpressionNode[0]));
            return new ConstantExpressionNode(f.evaluate(scope));
        } catch (MismatchParameterCountException e) {
            return new ConstantExpressionNode(new Value(e.getMessage()));
        }
    }
    private ExpressionNode createDefinitionNode(String[] tokens, int tokenIndex) {
        try {
            String symbol = tokens[tokenIndex+1];
            ExpressionNode n = new ConstantExpressionNode(new Value(symbol));
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+1);
            ExpressionNode value = treeBuilder.build(p2);
            scope.definedSymbols.put(symbol,value.evaluate(scope));
            return new DefinitionExpressionNode(n,value);
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private void addDefinedSymbols() {
        scope.definedSymbols.put("e",new Value("2.718281828459045"));
        scope.definedSymbols.put("tau",new Value("6.283185307179586"));
    }
    private void addDefinedFunctions() {
        Value[] twoValues = new Value[]{new Value("a"),new Value("b")};
        ListExpressionNode twoParams = new ListExpressionNode(twoValues);
        Value[] oneValue = new Value[]{new Value("a")};
        ListExpressionNode oneParam = new ListExpressionNode(oneValue);
        ExpressionNode a = new SymbolExpressionNode("a");
        ExpressionNode b = new SymbolExpressionNode("b");
        scope.definedFunctions.put("+",new FunctionExpressionNode(twoParams,new AdditionExpressionNode(a,b)));
        scope.definedFunctions.put("-",new FunctionExpressionNode(twoParams,new SubtractionExpressionNode(a,b)));
        scope.definedFunctions.put("*",new FunctionExpressionNode(twoParams,new MultiplicationExpressionNode(a,b)));
        scope.definedFunctions.put("/",new FunctionExpressionNode(twoParams,new DivisionExpressionNode(a,b)));
        scope.definedFunctions.put("exp",new FunctionExpressionNode(oneParam,new ExponentialExpressionNode(a)));
        scope.definedFunctions.put("ln",new FunctionExpressionNode(oneParam,new NaturalLogExpressionNode(a)));
        scope.definedFunctions.put("sin",new FunctionExpressionNode(oneParam,new SineExpressionNode(a)));
        scope.definedFunctions.put("cos",new FunctionExpressionNode(oneParam,new CosineExpressionNode(a)));
    }
    private ExpressionTreeBuilder treeBuilder;
    private String[] specialTokens;
    public Scope scope;
}
