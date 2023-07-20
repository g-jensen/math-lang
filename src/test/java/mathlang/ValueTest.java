package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueTest {

    @Test
    void numberIsNullWhenNotNumber() {
        Value v1 = new Value("");
        assertNull(v1.toInteger());
        assertNull(v1.toDouble());
        assertEquals("",v1.toString());

        Value v2 = new Value("greg");
        assertNull(v2.toInteger());
        assertNull(v2.toDouble());
        assertEquals("greg",v2.toString());

        Value v3 = new Value("d\\f][=4(*#&");
        assertNull(v3.toInteger());
        assertNull(v3.toDouble());
        assertEquals("d\\f][=4(*#&",v3.toString());
    }

    @Test
    void parsesAsNumberWhenPossible() {
        Value v1 = new Value("4");
        assertEquals(4,v1.toInteger());
        assertEquals(4.0,v1.toDouble());

        Value v2 = new Value("3.0");
        assertEquals(3,v2.toInteger());
        assertEquals(3.0,v2.toDouble());

        Value v3 = new Value("3.53");
        assertEquals(3,v3.toInteger());
        assertEquals(3.53,v3.toDouble());
    }

    @Test
    void convertsToString() {
        Value v1 = new Value("4");
        assertEquals("4",v1.toString());

        Value v2 = new Value("3.0");
        assertEquals("3",v2.toString());

        Value v3 = new Value("3.123");
        assertEquals("3.123",v3.toString());

        Value v4 = new Value("3.0123");
        assertEquals("3.0123",v4.toString());

        Value v5 = new Value("3.000123");
        assertEquals("3.000123",v5.toString());
    }
}