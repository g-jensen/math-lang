package mathlang;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

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
    void readsFromReader() {
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

        assertEquals("0", repl.evaluate("0").toString());
        assertEquals("1", repl.evaluate("1").toString());
        assertEquals("2", repl.evaluate("2").toString());
        assertEquals("-1", repl.evaluate("-1").toString());
        assertEquals("2",repl.evaluate("+ 1 1").toString());
        assertEquals("5",repl.evaluate("(+ 2 3)").toString());
        assertEquals("5",repl.evaluate("(+ 2 (+ 1 2))").toString());
        assertEquals("12",repl.evaluate("(+ 2 (+ (+ 5 3) 2))").toString());
    }

    @Test
    void evaluatesInvalidInput() {
        initializeReplWithoutData();

        assertNull(repl.evaluate(""));
        assertNull(repl.evaluate("+ 3"));
        assertNull(repl.evaluate("("));
        assertNull(repl.evaluate("+ 3 ()"));
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
    void loopsUntilQuit() {
        initializeReplWithData(new String[]{"0","quit","-3"});

        repl.loop();
        assertEquals("=> 0\n",outputStream.toString());
    }
}