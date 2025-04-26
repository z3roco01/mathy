package z3roco01.mathy.parser;

import z3roco01.mathy.parser.symbols.Symbol;
import z3roco01.mathy.parser.symbols.tree.EquationTree;
import z3roco01.mathy.parser.symbols.tree.SymbolNode;

import java.util.ArrayList;
import java.util.List;

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

        // form the flat list into a tree
        EquationTree tree = createTree(flatNodes);

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
     * forms an EquationTree from a flat list created by parseSymbols
     * @param nodeList the flat list of nodes
     * @return the tree from the symbols, null on any error
     */
    public EquationTree createTree(ArrayList<SymbolNode> nodeList) {
        // obv cant create a tree from nothing
        if(nodeList.isEmpty())
            return null;

        SymbolNode root = null;
        int rootIdx = 0;
        SymbolNode curNode;
        // loop over the list until there is an operator, this will be the root
        // use index looping since it needs the index for later
        for(int idx=0; idx<nodeList.size(); ++idx) {
            curNode = nodeList.get(idx);

            if(curNode.isOperator()) {
                // save the node and break out of the loop
                root = curNode;
                rootIdx = idx;
                break;
            }
        }

        // cant do math if theres no operators, so return null
        if(root == null)
            return null;

        EquationTree tree = new EquationTree(root);

        // next, recursivly find the right and left symbols, they can also be an operator, so it must account for that
        findSide(nodeList, tree, root, rootIdx, true);
        findSide(nodeList, tree, root, rootIdx, false);

        System.out.println(root.left.toString() + " <- " + root + " -> " + root.right.toString());

        return tree;
    }

    /**
     * will find the side that is requested, will also take into account operators, and them needing their sides found
     * @param nodeList the flat node list
     * @param tree the EquationTree being made currently
     * @param parent the parent whoes side is being found
     * @param idx the index of the parent operator
     * @param left true if finding the left side, false otherwise
     */
    private void findSide(List<SymbolNode> nodeList, EquationTree tree, SymbolNode parent, int idx, boolean left) {
        // first create the bounds being searched
        int lowerBound;
        int upperBound;

        // subList is exclusive on the upperbound, so it doesnt need to be subbed
        if(left) { // if left is being searched, search from 0 to just before the operator
            lowerBound = 0;
            upperBound = idx;
        }else { // if right, searched from just after operator till the end
            lowerBound = idx+1;
            upperBound = nodeList.size();
        }

        // now get the arraylist for this bound
        List<SymbolNode> searchArea = nodeList.subList(lowerBound, upperBound);
        // do this if left, so that search will go away from the parent
        if(left) searchArea = searchArea.reversed();

        // then search for the first operator from the original, if found, run this on it
        for(SymbolNode node : searchArea) {
            if(node.isOperator()) {
                // add this node as a child
                tree.addNode(parent, node, left);
                // then set its sides
                findSide(searchArea, tree, node, searchArea.indexOf(node), true);
                findSide(searchArea, tree, node, searchArea.indexOf(node), false);
                // everything would be parsed if and operator is found, so just return
                return;
            }
        }
        // must only be a number, or there is a bug, but either way, add the correct node as a child straight up
        SymbolNode newChild;
        if(left)
            newChild = nodeList.get(idx-1);
        else
            newChild = nodeList.get(idx+1);

        tree.addNode(parent, newChild, left);
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
