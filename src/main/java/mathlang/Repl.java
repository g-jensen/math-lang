package mathlang;

import java.io.*;

public class Repl {
    public Repl(InputStream inputStream, PrintStream printStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.printStream = printStream;
    }
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    public Integer evaluate(String input) {
        try {
            if (input.equalsIgnoreCase("quit")) {
                return null;
            } else {
                return Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
    public void print(int val) {
        printStream.println("=> " + val);
    }
    public void loop() {
        Integer value = evaluate(read());
        while (value != null) {
            print(value);
            value = evaluate(read());
        }
    }
    private BufferedReader reader;
    private PrintStream printStream;
}
