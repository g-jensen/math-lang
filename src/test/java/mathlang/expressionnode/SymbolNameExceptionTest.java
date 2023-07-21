package mathlang.expressionnode;

import mathlang.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolNameExceptionTest {

    @Test
    void createsCorrectMessage() {
        SymbolNameException e1 = new SymbolNameException(new Value("1"));
        assertEquals(
                "Invalid function name: \"1\". Name must be a word",
                e1.getMessage()
        );

        SymbolNameException e2 = new SymbolNameException(new Value(";milk123"));
        assertEquals(
                "Invalid function name: \";milk123\". Name must be a word",
                e2.getMessage()
        );
    }
}