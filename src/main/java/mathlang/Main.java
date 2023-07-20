package mathlang;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Repl(System.in,System.out).loop();
    }
}