package mathlang;

import mathlang.expressionnode.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        this.specialTokens = new String[]{"(",")","[","]","+","-","*","/","exp","ln","sin","cos","def","fun"};
    }
    public ExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (Utils.isNumeric(token)) {
            return createConstantNodeFromNumber(tokens, tokenIndex);
        } else if (treeBuilder.definedSymbols.containsKey(token)) {
            return createConstantNodeFromSymbol(tokens, tokenIndex);
        } else if (treeBuilder.definedFunctions.containsKey(token)) {
            return createConstantNodeFromFunctionCall(tokens,tokenIndex);
        } else if (!isSpecial(token)) {
            return createNullNode();
        } else if (token.equals("+")) {
            return createBinaryNode(tokens,tokenIndex,AdditionExpressionNode.class);
        } else if (token.equals("-")) {
            return createBinaryNode(tokens,tokenIndex,SubtractionExpressionNode.class);
        } else if (token.equals("*")) {
            return createBinaryNode(tokens,tokenIndex,MultiplicationExpressionNode.class);
        } else if (token.equals("/")) {
            return createBinaryNode(tokens,tokenIndex,DivisionExpressionNode.class);
        } else if (token.equals("exp")) {
            return createUnaryNode(tokens,tokenIndex,ExponentialExpressionNode.class);
        } else if (token.equals("ln")) {
            return createUnaryNode(tokens,tokenIndex,NaturalLogExpressionNode.class);
        } else if (token.equals("sin")) {
            return createUnaryNode(tokens,tokenIndex,SineExpressionNode.class);
        } else if (token.equals("cos")) {
            return createUnaryNode(tokens,tokenIndex,CosineExpressionNode.class);
        } else if (token.equals("def")) {
            return createDefinitionNode(tokens,tokenIndex);
        } else if (token.equals("[")) {
            return createListNode(tokens,tokenIndex);
        } else if (token.equals("fun")) {
            return createFunctionNode(tokens,tokenIndex);
        }
        return null;
    }
    private boolean isSpecial(String token) {
        return Arrays.asList(specialTokens).contains(token);
    }
    private  ExpressionNode createNullNode() {
        return new NullExpressionNode();
    }
    private ExpressionNode createConstantNodeFromNumber(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(new Value(tokens[tokenIndex]));
    }
    private ExpressionNode createConstantNodeFromSymbol(String[] tokens, int tokenIndex) {
        return new ConstantExpressionNode(treeBuilder.definedSymbols.get(tokens[tokenIndex]));
    }
    private ExpressionNode createConstantNodeFromFunctionCall(String[] tokens, int tokenIndex) {
        try {
            FunctionExpressionNode f = treeBuilder.definedFunctions.get(tokens[tokenIndex]);
            // TODO - replace "new Value[0]" with list of parameter values
            // will need to test creating functions with parameters first.
            Value v = f.call(new ListExpressionNode(new Value[0]));
            return new ConstantExpressionNode(v);
        } catch (MissingParametersException | MismatchParameterCountException e) {
            return new ConstantExpressionNode(new Value(e.getMessage()));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createBinaryNode(String[] tokens, int tokenIndex, Class<? extends BinaryExpressionNode> c) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+p1.length);
            return c.getConstructor(ExpressionNode.class,ExpressionNode.class)
                    .newInstance(treeBuilder.build(p1), treeBuilder.build(p2));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createUnaryNode(String[] tokens, int tokenIndex, Class<? extends UnaryExpressionNode> c) {
        try {
            String[] p1 = treeBuilder.nextParameter(tokens,tokenIndex);
            return c.getConstructor(ExpressionNode.class)
                    .newInstance(treeBuilder.build(p1));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createDefinitionNode(String[] tokens, int tokenIndex) {
        try {
            String symbol = tokens[tokenIndex+1];
            ExpressionNode n = new ConstantExpressionNode(new Value(symbol));
            String[] p2 = treeBuilder.nextParameter(tokens,tokenIndex+1);
            ExpressionNode value = treeBuilder.build(p2);
            treeBuilder.definedSymbols.put(symbol,value.evaluate());
            return new DefinitionExpressionNode(n,value);
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionNode createListNode(String[] tokens, int tokenIndex) {
        ArrayList<Value> values = new ArrayList<>();
        for (int i = tokenIndex+1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("]")) {
                return new ListExpressionNode(values.toArray(new Value[0]));
            }
            values.add(new Value(token));
        }
        return new NullExpressionNode();
    }
    private ExpressionNode createFunctionNode(String[] tokens, int tokenIndex) {
        try {
            ArrayList<ExpressionNode> parameters = new ArrayList<>();
            String strName = tokens[tokenIndex+1];
            ExpressionNode name = new ConstantExpressionNode(new Value(strName));
            ExpressionNode params = createListNode(tokens,tokenIndex+2);
            parameters.add(name);
            parameters.add(params);
            for (int i = tokenIndex+2; i < tokens.length-1; i++) {
                String[] p = treeBuilder.nextParameter(tokens,i);
                i += p.length-1;
                parameters.add(treeBuilder.build(p));
            }
            FunctionExpressionNode n = new FunctionExpressionNode(parameters.toArray(new ExpressionNode[0]));
            treeBuilder.definedFunctions.put(strName,n);
            return n;
        } catch (FunctionParameterCountException | SymbolNameException e) {
            return new ConstantExpressionNode(new Value(e.getMessage()));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
    private String[] specialTokens;
}
