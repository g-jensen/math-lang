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
    void evaluatesInput() {
        initializeReplWithoutData();

        assertEquals(0, repl.evaluate("0"));
        assertEquals(1, repl.evaluate("1"));
        assertEquals(2, repl.evaluate("2"));
        assertEquals(-1, repl.evaluate("-1"));
        assertEquals(2,repl.evaluate("+ 1 1"));
        assertEquals(5,repl.evaluate("(+ 2 3)"));
        assertEquals(5,repl.evaluate("(+ 2 (+ 1 2))"));
        assertEquals(12,repl.evaluate("(+ 2 (+ (+ 5 3) 2))"));
    }

    @Test
    void printsValueToPrintStream() {
        initializeReplWithData(new String[]{"0", "1", "2", "-1", "-2"});

        repl.print(0);
        assertEquals("=> 0\n",outputStream.toString());
        outputStream.reset();

        repl.print(1);
        repl.print(2);
        assertEquals("=> 1\n=> 2\n",outputStream.toString());
        outputStream.reset();

        repl.print(-1);
        repl.print(-2);
        assertEquals("=> -1\n=> -2\n",outputStream.toString());
    }

    @Test
    void loopsUntilQuit() {
        initializeReplWithData(new String[]{"0","quit","-3"});

        repl.loop();
        assertEquals("=> 0\n",outputStream.toString());
    }
}