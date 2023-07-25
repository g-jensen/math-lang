package mathlang.expressionnode;

import mathlang.NullValue;
import mathlang.Scope;
import mathlang.Value;

import java.util.Objects;

public class SumExpressionNode implements ExpressionNode {
    public SumExpressionNode(ExpressionNode start, ExpressionNode end, ExpressionNode f) {
        this.start = start;
        this.end = end;
        this.f = f;
    }

    @Override
    public Value evaluate(Scope scope) {
        double accumulation = 0.0;
        Integer s = start.evaluate(scope).toInteger();
        Integer e = end.evaluate(scope).toInteger();
        if (Objects.isNull(s) || Objects.isNull(e)) {
            return new Value(Double.toString(accumulation));
        }
        for (int i = s; i <= e; i++) {
            try {
                FunctionExpressionNode fun = scope.definedFunctions.get(f.evaluate(scope).toString());
                ExpressionNode n = new ConstantExpressionNode(new Value(Integer.toString(i)));
                fun.addParametersToScope(new ExpressionNode[]{n});
                accumulation += fun.evaluate(scope).toDouble();
            } catch (Exception | MismatchParameterCountException ex) {
                return new NullValue();
            }
        }

        return new Value(Double.toString(accumulation));
    }
    private ExpressionNode start;
    private ExpressionNode end;
    private ExpressionNode f;
}
