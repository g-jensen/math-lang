package org;

import mathlang.Repl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            new Repl(System.in,System.out).loop();
        } else {
            new Repl(Files.newInputStream(Paths.get(args[0])),System.out).loop();
        }
    }
}