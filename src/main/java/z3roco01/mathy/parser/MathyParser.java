package z3roco01.mathy.parser;

import z3roco01.mathy.parser.symbols.Symbol;
import z3roco01.mathy.parser.symbols.tree.SymbolNode;

/**
 * parses a provided equation string
 */
public class MathyParser {
    public String equation = "";

    public MathyParser(String equation) {
        this.equation = equation;
    }

    // if they dont wanna pass an equation right away thats fine
    public MathyParser(){}

    /**
     * parses the held equation string, returns the result, will return 0 on error
     * @return the result of the equation, or 0 if error
     *
     */
    public double parse() {
        // obv cant do anything on a blank string
        if(equation.isBlank())
            return 0;

        // hold the current char, defined outside as optimization
        char curChar;
        SymbolNode temp;
        // loop over every character
        for(int idx=0; idx<equation.length(); ++idx) {
            curChar = equation.charAt(idx);

            // obv dont do anything with whitespace
            if(Character.isWhitespace(curChar))
                continue;

            if(Character.isDigit(curChar)) {
                char chr = curChar;
                int numIdx = idx;
                // find the bounds of this number
                while(Character.isDigit(chr) && ++numIdx<equation.length())
                    chr = equation.charAt(numIdx);

                // get the characters that compose the number as a string
                String numberStr = equation.substring(idx, numIdx);
                System.out.println(numberStr);

                // and set the index to the end of the number, so it doesnt keep going over it
                // -1 since it adds one at the start
                idx = numIdx-1;
            }else if(Symbol.isSymbol(curChar)) {
                temp = new SymbolNode(Symbol.ofChar(curChar).get(), null, null, null);
                System.out.println(temp);
            }
        }

        return 0;
    }
}
