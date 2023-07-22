package mathlang.expressionnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissingParametersExceptionTest {

    @Test
    void createsCorrectMessage() {
        Throwable e = new MissingParametersException("greg");
        assertEquals("Missing parameters in greg",e.getMessage());
    }
}