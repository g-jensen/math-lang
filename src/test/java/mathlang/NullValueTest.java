package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullValueTest {

    @Test
    void createsANullValue() {
        Value v = new NullValue();
        assertEquals("null",v.toString());
        assertNull(v.toDouble());
        assertNull(v.toInteger());
    }
}