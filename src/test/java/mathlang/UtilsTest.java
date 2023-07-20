package mathlang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void checksIfAStringIsNumeric() {
        assertFalse(Utils.isNumeric(""));
        assertTrue(Utils.isNumeric("1"));
        assertTrue(Utils.isNumeric("22"));
        assertTrue(Utils.isNumeric("-23"));
        assertTrue(Utils.isNumeric("4.5"));
        assertTrue(Utils.isNumeric("-65.15"));
        assertFalse(Utils.isNumeric("greg"));
    }
}