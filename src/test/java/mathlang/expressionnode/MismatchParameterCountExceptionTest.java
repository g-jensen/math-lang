package mathlang.expressionnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MismatchParameterCountExceptionTest {

    @Test
    void createsCorrectMessage() {
        Throwable t1 = new MismatchParameterCountException(1,2);
        assertEquals("Mismatched parameter count: 1 when expected 2",t1.getMessage());

        Throwable t2 = new MismatchParameterCountException(4,7);
        assertEquals("Mismatched parameter count: 4 when expected 7",t2.getMessage());
    }
}