package z3roco01.mathy.parser.symbols;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Symbol {
    ADD('+', true),
    SUB('-', true),
    MUL('*', true),
    DIV('/', true),
    EXPO('^', true),
    NUM('\0', false); // does not have a symbol, since it will be parsed dif

    /**
     * the character that corisponds to this symbol
     * will be \0 if there isnt one
     */
    final Character character;
    /**
     * used in tree creation, if set this will have the ability to become a root/parent
     */
    public final Boolean isOperator;
    Symbol(Character Symbol, Boolean isOperator) {
        this.character = Symbol;
        this.isOperator = isOperator;
    }

    private static Map<Character, Symbol> createMap() {
        HashMap<Character, Symbol> map = new HashMap<>();
        for(Symbol symbol : Symbol.values())
            map.put(symbol.character, symbol);

        return map;
    }

    /**
     * checks if the passed character matches with a symbol ( wont check null symbols )
     * @param character the character being checked
     * @return true if it matches, else false ( also returns false on null )
     */
    public static boolean isSymbol(char character) {
        // since there will be symbols with null check it before, since those arent supposed to match
        if(character == '\0') return false;

        return charMap.containsKey(character);
    }

    /**
     * will turn a character into its coresponding symbol ( if possible, else will return Optional.empty() )
     * @param character the character to be converted
     * @return an Optional, will contain the symbol if there is one, else returns an empty Optional
     */
    public static Optional<Symbol> ofChar(char character) {
        if(isSymbol(character))
            return Optional.of(charMap.get(character));
        else
            return Optional.empty();
    }

    static final Map<Character, Symbol> charMap = Collections.unmodifiableMap(createMap());
}
