package org;

import mathlang.ConsoleRepl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new ConsoleRepl(System.in,System.out).loop();
    }
}