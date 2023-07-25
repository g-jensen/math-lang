package mathlang;

import java.io.*;

public interface Repl {
    public String read() throws IOException;
    public Value evaluate(String input);
    public void print(Value val);
    public void loop() throws IOException;
}
