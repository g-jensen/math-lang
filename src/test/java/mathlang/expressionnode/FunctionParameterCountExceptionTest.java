package mathlang.expressionnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionParameterCountExceptionTest {

    @Test
    void createsCorrectMessage() {
        FunctionParameterCountException e1 = new FunctionParameterCountException(2,3);
        assertEquals(
                "Invalid parameter count: 3. Parameter count must be at least 2",
                e1.getMessage()
        );

        FunctionParameterCountException e2 = new FunctionParameterCountException(2,4);
        assertEquals(
                "Invalid parameter count: 4. Parameter count must be at least 2",
                e2.getMessage()
        );
    }
}