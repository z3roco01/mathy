package z3roco01.mathy.parser.symbols.tree;

import z3roco01.mathy.parser.symbols.Symbol;

/**
 * used in the equation tree, holds the symbol of this node, its parent and children
 */
public class SymbolNode {
    public Symbol symbol;
    // any data that goes along with the symbol
    public Double data;
    public SymbolNode parent;
    // the two children
    public SymbolNode left;
    public SymbolNode right;

    public SymbolNode(Symbol symbol, SymbolNode parent, SymbolNode left, SymbolNode right, Double data) {
        this.symbol = symbol;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public SymbolNode(Symbol symbol, SymbolNode parent, SymbolNode left, SymbolNode right) {
        this(symbol, parent, left, right, 0.0);
    }

    /**
     * @return true if it has no children and a parent
     */
    public boolean isLeaf() {
        return hasParent() && !(hasLeft() || hasRight());
    }

    /**
     * @return true of the symbol is an operator, the same as symbol.isOperator
     */
    public boolean isOperator() {
        return symbol.isOperator;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    @Override
    public String toString() {
        return symbol.name() + " " + data.toString();
    }
}
