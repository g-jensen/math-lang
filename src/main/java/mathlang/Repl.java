package mathlang;

import java.io.*;

public class Repl {
    public Repl(InputStream inputStream, PrintStream printStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.printStream = printStream;
        this.parser = new ExpressionParser();
        this.treeBuilder = new ExpressionTreeBuilder();
    }
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    public Value evaluate(String input) {
        if (input.equalsIgnoreCase("quit")) {
            // TODO - create Value class and make "quit" return a QuitValue instance
            return null;
        } else {
            // TODO - change evaluation to depend on abstraction
            return treeBuilder.build(parser.getTokens(input)).evaluate();
        }
    }
    public void print(Value val) {
        printStream.println("=> " + val.toString());
    }
    public void loop() {
        Value value = evaluate(read());
        while (value != null) {
            print(value);
            value = evaluate(read());
        }
    }
    private BufferedReader reader;
    private PrintStream printStream;
    private ExpressionParser parser;
    private ExpressionTreeBuilder treeBuilder;
}
