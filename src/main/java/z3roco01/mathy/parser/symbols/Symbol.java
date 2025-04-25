package z3roco01.mathy.parser.symbols;

public enum Symbol {
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/'),
    EXPO('^'),
    LEFT_PARAN('('),
    RIGHT_PARAN(')');


    final char symbol;
    private Symbol(char Symbol) {
        this.symbol = Symbol;
    }
}
