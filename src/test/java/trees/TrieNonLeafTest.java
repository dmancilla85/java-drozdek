package trees;

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
}
