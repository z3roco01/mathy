package z3roco01.mathy.parser.symbols.tree;

import z3roco01.mathy.parser.symbols.Symbol;

/**
 * used in the equation tree, holds the symbol of this node, its parent and children
 */
public class SymbolNode {
    public Symbol symbol;
    // any data that goes along with the symbol
    public Double data = 0.0;
    private SymbolNode parent;
    // the two children
    private SymbolNode left;
    private SymbolNode right;

    public SymbolNode(Symbol symbol, SymbolNode parent, SymbolNode left, SymbolNode right) {
        this.symbol = symbol;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public SymbolNode getParent() {
        return parent;
    }

    public SymbolNode getLeft() {
        return left;
    }

    public SymbolNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return symbol.name() + " " + data.toString();
    }
}
