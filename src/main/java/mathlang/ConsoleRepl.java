package mathlang;

import java.io.*;

public class ConsoleRepl implements Repl{
    public ConsoleRepl(InputStream inputStream, PrintStream printStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.printStream = printStream;
        this.parser = new ExpressionParser();
        this.treeBuilder = new ExpressionTreeBuilder();
    }
    public String read() throws IOException {
        return reader.readLine();
    }
    public Value evaluate(String input) {
        if (input.equalsIgnoreCase("quit")) {
            return new Value("quit");
        } else {
            return treeBuilder.build(parser.getTokens(input)).evaluate(new Scope());
        }
    }
    public void print(Value val) {
        printStream.println("=> " + val);
    }
    public void loop() throws IOException {
        Value value = evaluate(read());
        while (!value.toString().equals("quit")) {
            print(value);
            value = evaluate(read());
        }
    }
    private BufferedReader reader;
    private PrintStream printStream;
    private ExpressionParser parser;
    private ExpressionTreeBuilder treeBuilder;
}
