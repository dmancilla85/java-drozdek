package org.drozdek.compression;

/// Node of a Huffman tree holding a character symbol, its occurrence
/// frequency, and optional left/right children.
///
/// Leaves carry a symbol and a non-zero frequency; internal nodes carry a
/// frequency equal to the sum of their children and no symbol.
///
/// **Real-world use case:** Building block of the optimal prefix-code tree
/// used by lossless compression (ZIP, JPEG, MP3).
///
/// Complexity Analysis:
/// Time Complexity: O(1) for construction and accessors
/// Auxiliary Space: O(1) per node
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 11.
public class HuffmanNode implements Comparable<HuffmanNode> {

    /// Character encoded by this node, or {@code 0} for internal nodes.
    private final char symbol;

    /// Occurrence count of the symbol (or the sum of the children frequencies).
    private final int frequency;

    /// Left child in the Huffman tree.
    private HuffmanNode left;

    /// Right child in the Huffman tree.
    private HuffmanNode right;

    /// Creates a leaf node carrying a symbol and its frequency.
    ///
    /// @param symbol    character encoded by this leaf
    /// @param frequency occurrence count of the symbol
    public HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    /// Creates an internal node from two children, combining their symbols
    /// and summing their frequencies.
    ///
    /// @param left  left child
    /// @param right right child
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.symbol = 0;
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }

    /// Returns the symbol stored in this node.
    ///
    /// @return the character, or {@code 0} for internal nodes
    public char getSymbol() {
        return symbol;
    }

    /// Returns the frequency associated with this node.
    ///
    /// @return occurrence count (or child sum for internal nodes)
    public int getFrequency() {
        return frequency;
    }

    /// Returns the left child.
    ///
    /// @return the left child, or {@code null} for leaves
    public HuffmanNode getLeft() {
        return left;
    }

    /// Returns the right child.
    ///
    /// @return the right child, or {@code null} for leaves
    public HuffmanNode getRight() {
        return right;
    }

    /// Reports whether this node is a leaf (no children).
    ///
    /// @return {@code true} if this is a leaf node
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /// Orders nodes by ascending frequency so the lowest-frequency nodes are
    /// merged first by a {@link java.util.PriorityQueue}.
    ///
    /// @param other node to compare against
    /// @return negative, zero, or positive when this frequency is less than,
    ///         equal to, or greater than the other's
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(frequency, other.frequency);
    }

    @Override
    public String toString() {
        return isLeaf() ? symbol + "(" + frequency + ")" : "[internal " + frequency + "]";
    }
}
