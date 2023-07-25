package mathlang;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ReplTest {
    private Repl repl;
    private ByteArrayOutputStream outputStream;

    void initializeReplWithData(String[] data) {
        byte[] bytes = (String.join("\n",data) + '\n').getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        repl = new Repl(in, printStream);
    }

    void initializeReplWithoutData() {
        initializeReplWithData(new String[0]);
    }

    @Test
    void readsFromReader() throws IOException {
        initializeReplWithData(new String[]{"0", "+ 1 2", "2", "(+ 65 3)"});

        assertEquals("0",repl.read());
        assertEquals("+ 1 2",repl.read());
        assertEquals("2",repl.read());
        assertEquals("(+ 65 3)",repl.read());
        assertNull(repl.read());
    }

    @Test
    void evaluatesValidInput() {
        initializeReplWithoutData();

        assertEquals(new Value("0"), repl.evaluate("0"));
        assertEquals(new Value("1"), repl.evaluate("1"));
        assertEquals(new Value("2"), repl.evaluate("2"));
        assertEquals(new Value("-1"), repl.evaluate("-1"));
        assertEquals(new Value("2"),repl.evaluate("+ 1 1"));
        assertEquals(new Value("5"),repl.evaluate("(+ 2 3)"));
        assertEquals(new Value("5"),repl.evaluate("(+ 2 (+ 1 2))"));
        assertEquals(new Value("12"),repl.evaluate("(+ 2 (+ (+ 5 3) 2))"));
    }

    @Test
    void evaluatesInvalidInput() {
        initializeReplWithoutData();

        assertEquals(new NullValue(),repl.evaluate(""));
        assertEquals(
                new Value("Mismatched parameter count: 1 when expected 2"),
                repl.evaluate("+ 3")
        );
        assertEquals(new NullValue(),repl.evaluate("("));
        assertEquals(new NullValue(),repl.evaluate("+ 3 ()"));
    }

    @Test
    void printsValueToPrintStream() {
        initializeReplWithData(new String[]{"0", "1", "2", "-1", "-2"});

        repl.print(new Value("0"));
        assertEquals("=> 0\n",outputStream.toString());
        outputStream.reset();

        repl.print(new Value("1"));
        repl.print(new Value("2.0"));
        assertEquals("=> 1\n=> 2\n",outputStream.toString());
        outputStream.reset();

        repl.print(new Value("-1"));
        repl.print(new Value("-2.12"));
        assertEquals("=> -1\n=> -2.12\n",outputStream.toString());
    }

    @Test
    void loopsUntilQuit() throws IOException {
        initializeReplWithData(new String[]{"0","quit","-3"});

        repl.loop();
        assertEquals("=> 0\n",outputStream.toString());
    }
}