package z3roco01.mathy;

import z3roco01.mathy.parser.MathyParser;

public class Main {
    public static void main(String[] args) {
        MathyParser parser = new MathyParser("4+4");
        parser.parse();
    }
}