package mathlang.expressionnode;

import mathlang.Scope;
import mathlang.Value;

import java.util.Map;

public interface ExpressionNode {
    Value evaluate(Scope scope);
}
