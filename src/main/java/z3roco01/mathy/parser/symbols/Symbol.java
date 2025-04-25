package z3roco01.mathy.parser.symbols;

public enum Symbol {
    ADD('+', "ADD"),
    SUB('-', "SUB"),
    MUL('*', "MUL"),
    DIV('/', "DIV"),
    EXPO('^', "EXPO"),
    LEFT_PAREN('(', "LEFT_PAREN"),
    RIGHT_PAREN(')', "RIGHT_PAREN");


    final char symbol;
    final String name; // used mostly for debu
    private Symbol(char Symbol, String name) {
        this.symbol = Symbol;
        this.name = name;
    }
}
