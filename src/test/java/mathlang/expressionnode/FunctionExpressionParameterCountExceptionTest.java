package mathlang.expressionnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionExpressionParameterCountExceptionTest {

    @Test
    void createsCorrectMessage() {
        FunctionExpressionParameterCountException e1 = new FunctionExpressionParameterCountException(3);
        assertEquals(
                "Invalid parameter count: 3. Parameter count must be greater than 2",
                e1.getMessage()
        );

        FunctionExpressionParameterCountException e2 = new FunctionExpressionParameterCountException(4);
        assertEquals(
                "Invalid parameter count: 4. Parameter count must be greater than 2",
                e2.getMessage()
        );
    }
}