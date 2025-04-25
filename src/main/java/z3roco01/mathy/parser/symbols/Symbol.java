package z3roco01.mathy.parser.symbols;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Symbol {
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/'),
    EXPO('^'),
    LEFT_PAREN('('),
    RIGHT_PAREN(')'),
    NUM('\0'); // does not have a symbol, since it will be parsed dif

    /**
     * the character that corisponds to this symbol
     * will be \0 if there isnt one
     */
    final Character character;
    private Symbol(Character Symbol) {
        this.character = Symbol;
    }

    private static Map<Character, Symbol> createMap() {
        HashMap<Character, Symbol> map = new HashMap<>();
        for(Symbol symbol : Symbol.values())
            map.put(symbol.character, symbol);

        return map;
    }

    static Map<Character, Symbol> char2SymbolMap = Collections.unmodifiableMap(createMap());
}
