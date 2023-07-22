package mathlang;

import mathlang.expressionnode.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExpressionNodeFactory {
    public ExpressionNodeFactory(ExpressionTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        this.specialTokens = new String[]{"(",")","[","]","+","-","*","/","exp","ln","sin","cos","def","fun"};

        this.definedSymbols = new HashMap<>();
        definedSymbols.put("e",new Value("2.718281828459045"));
        definedSymbols.put("tao",new Value("6.283185307179586"));

        this.definedFunctions = new HashMap<>();
    }
    public ExpressionNode createNode(String[] tokens, int tokenIndex) {
        String token = tokens[tokenIndex];
        if (Utils.isNumeric(token)) {
            return createConstantNodeFromNumber(tokens, tokenIndex);
        } else if (definedSymbols.containsKey(token)) {
            return createConstantNodeFromSymbol(tokens, tokenIndex);
        } else if (definedFunctions.containsKey(token)) {
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
        return new ConstantExpressionNode(definedSymbols.get(tokens[tokenIndex]));
    }
    private ExpressionNode createConstantNodeFromFunctionCall(String[] tokens, int tokenIndex) {
        try {
            FunctionExpressionNode f = definedFunctions.get(tokens[tokenIndex]);
            ArrayList<ExpressionNode> params = new ArrayList<>();
            int index = tokenIndex;
            for (int i = 0; i < f.parameterCount; i++) {
                String[] t = treeBuilder.nextParameter(tokens,index);
                index += t.length;
                params.add(treeBuilder.build(t));
            }
            return f.call(params.toArray(new ExpressionNode[0]));
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
            definedSymbols.put(symbol,value.evaluate());
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
            FunctionExpressionNode n = new FunctionExpressionNode(Arrays.copyOfRange(tokens,tokenIndex,tokens.length));
            definedFunctions.put(n.name.toString(),n);
            return n;
        } catch (FunctionParameterCountException | SymbolNameException e) {
            return new ConstantExpressionNode(new Value(e.getMessage()));
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
    private String[] specialTokens;
    public Map<String, Value> definedSymbols;
    public Map<String, FunctionExpressionNode> definedFunctions;
}
