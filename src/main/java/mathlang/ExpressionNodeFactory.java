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
        definedSymbols.put("tau",new Value("6.283185307179586"));

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
            return new SymbolExpressionNode(token);
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
            f.setParameterValues(params.toArray(new ExpressionNode[0]));
            return new ConstantExpressionNode(f.evaluate(new Scope()));
        } catch (MismatchParameterCountException e) {
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
            definedSymbols.put(symbol,value.evaluate(new Scope()));
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
            String name = treeBuilder.nextParameter(tokens,tokenIndex)[0];
            String[] ps = Arrays.copyOfRange(tokens,tokenIndex+2,tokens.length);
            ListExpressionNode params = (ListExpressionNode)treeBuilder.build(ps);
            String[] b = Arrays.copyOfRange(tokens,tokenIndex+params.values.length+4,tokens.length);
            System.out.println(Arrays.toString(b));
            ExpressionNode body = treeBuilder.build(b);
            FunctionExpressionNode n = new FunctionExpressionNode(params,body);
            definedFunctions.put(name,n);
            return n;
        } catch (Exception e) {
            return new NullExpressionNode();
        }
    }
    private ExpressionTreeBuilder treeBuilder;
    private String[] specialTokens;
    public Map<String, Value> definedSymbols;
    public Map<String, FunctionExpressionNode> definedFunctions;
}
