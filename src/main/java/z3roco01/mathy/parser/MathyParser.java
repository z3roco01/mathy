package z3roco01.mathy.parser;

import z3roco01.mathy.parser.symbols.Symbol;
import z3roco01.mathy.parser.symbols.tree.SymbolNode;

import java.util.ArrayList;

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
     */
    public double parse() {
        // obv cant do anything on a blank string
        if(equation.isBlank())
            return 0;

        // create the flat list of nodes
        ArrayList<SymbolNode> flatNodes = parseSymbols();

        for(SymbolNode node : flatNodes)
            System.out.println(node);

        return 0;
    }

    /**
     * creates an initial flat list of SymbolNodes, is the first step of parsing
     * is made into a tree afterwards
     * @return the flat list of symbols
     */
    private ArrayList<SymbolNode> parseSymbols() {
        ArrayList<SymbolNode> symbolNodes = new ArrayList<>();

        // hold the current char, defined outside as optimization
        char curChar;
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
                // also need to account for decimal points, since it supports decimals
                while((Character.isDigit(chr) | chr == '.') && ++numIdx<equation.length())
                    chr = equation.charAt(numIdx);

                // get the characters that compose the number as a string
                String numberStr = equation.substring(idx, numIdx);

                // add the number to the symbol list
                addNode(symbolNodes, Symbol.NUM, Double.parseDouble(numberStr));

                // and set the index to the end of the number, so it doesnt keep going over it
                // -1 since it adds one at the start
                idx = numIdx-1;
            }else if(Symbol.isSymbol(curChar)) {
                // simply get the symbol the character aligns with and add it to the list
                addNode(symbolNodes, Symbol.ofChar(curChar).get());
            }
        }

        return symbolNodes;
    }

    /**
     * util function to add a new symbol node to the passed array list
     * creates a new one with no parents or children, that has a specified symbol and data
     * @param arrayList the ArrayList to add to
     * @param symbol the Symbol of the new SymbolNode
     * @param data the data of the new SymbolNode
     */
    private static void addNode(ArrayList<SymbolNode> arrayList, Symbol symbol, Double data) {
        arrayList.add(new SymbolNode(symbol, null, null, null, data));
    }

    /**
     * like the other add node, but doesnt take in data, just for simplification
     * @param arrayList the ArrayList to add to
     * @param symbol the Symbol of the new SymbolNode
     */
    private static void addNode(ArrayList<SymbolNode> arrayList, Symbol symbol) {
        addNode(arrayList, symbol, 0.0);
    }
}
