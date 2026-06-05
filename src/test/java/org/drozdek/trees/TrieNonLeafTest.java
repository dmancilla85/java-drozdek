package trees;

import org.drozdek.trees.nodes.TrieLeaf;
import org.drozdek.trees.nodes.TrieNode;
import org.drozdek.trees.nodes.TrieNonLeaf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieNonLeafTest {

    @Test
    @DisplayName("Constructor with character")
    void constructorWithChar() {
        TrieNonLeaf node = new TrieNonLeaf('a');
        assertNotNull(node.toString());
    }

    @Test
    @DisplayName("Default constructor")
    void defaultConstructor() {
        TrieNonLeaf node = new TrieNonLeaf();
        assertNotNull(node.toString());
    }

    @Test
    @DisplayName("ToString contains the letter")
    void toStringContainsLetter() {
        TrieNonLeaf node = new TrieNonLeaf('a');
        String result = node.toString();
        assertTrue(result.contains("a"));
    }

    @Test
    @DisplayName("ToString prints leaf child")
    void toStringLeafChild() {
        TrieNonLeaf node = new TrieNonLeaf('a');
        node.getPtr()[0] = new TrieLeaf("bc");
        String result = node.toString();
        assertTrue(result.contains("leaf"));
    }

    @Test
    @DisplayName("ToString prints null child")
    void toStringNullChild() {
        TrieNonLeaf node = new TrieNonLeaf('a');
        node.setLetters("ab");
        node.setPtr(new TrieNode[2]);
        node.getPtr()[1] = new TrieLeaf("c");
        String result = node.toString();
        assertNotNull(result);
    }

    @Test
    @DisplayName("ToString prints non-leaf child recursively")
    void toStringNonLeafChild() {
        TrieNonLeaf parent = new TrieNonLeaf('a');
        parent.setLetters("ab");
        parent.setPtr(new TrieNode[2]);
        TrieNonLeaf child = new TrieNonLeaf('c');
        parent.getPtr()[1] = child;
        String result = parent.toString();
        assertNotNull(result);
    }
}
