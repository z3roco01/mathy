package z3roco01.mathy.parser.symbols.tree;

/**
 * tree holding the symbols of the equation
 */
public class EquationTree {
    public final SymbolNode root;

    public EquationTree(SymbolNode root) {
        this.root = root;
    }

    /**
     * adds a new node as a child of a specific node, will override children and parents
     * if there is any error no changes will happen to the tree
     * @param parent the parent that this node will have
     * @param newNode the node to be added as a child
     * @param left if true, will be added to the left, else to the right
     */
    public void addNode(SymbolNode parent, SymbolNode newNode, Boolean left) {
        if(left) {
            parent.left = newNode;
        }else {
            parent.right = newNode;
        }

        newNode.parent = parent;
    }
}
