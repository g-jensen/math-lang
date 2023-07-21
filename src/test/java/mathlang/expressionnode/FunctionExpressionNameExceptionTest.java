package mathlang.expressionnode;

import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionNameExceptionTest {

    @Test
    void createsCorrectMessage() {
        FunctionExpressionNameException e1 = new FunctionExpressionNameException(new Value("1"));
        assertEquals(
                "Invalid function name: \"1\". Name must be a word",
                e1.getMessage()
        );

        FunctionExpressionNameException e2 = new FunctionExpressionNameException(new Value(";milk123"));
        assertEquals(
                "Invalid function name: \";milk123\". Name must be a word",
                e2.getMessage()
        );
    }
}