package org.drozdek.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HuffmanCodingTest {

    @Test
    @DisplayName("Frequency counting of a short string")
    void countFrequencies_talliesOccurrences() {
        Map<Character, Integer> frequencies = HuffmanCoding.countFrequencies("aabbbc");
        assertEquals(2, frequencies.get('a'));
        assertEquals(3, frequencies.get('b'));
        assertEquals(1, frequencies.get('c'));
    }

    @Test
    @DisplayName("Frequency counting of an empty string")
    void countFrequencies_emptyInput() {
        Map<Character, Integer> frequencies = HuffmanCoding.countFrequencies("");
        assertTrue(frequencies.isEmpty());
    }

    @Test
    @DisplayName("Tree construction with a single distinct symbol")
    void buildTree_singleSymbol() {
        Map<Character, Integer> frequencies = Map.of('x', 10);
        HuffmanNode root = HuffmanCoding.buildTree(frequencies);
        assertTrue(root.isLeaf());
        assertEquals('x', root.getSymbol());
    }

    @Test
    @DisplayName("Round trip: encode then decode reproduces the input")
    void roundTrip_encodesAndDecodes() {
        String text = "aaabbbcccdddeee";
        Map<Character, Integer> frequencies = HuffmanCoding.countFrequencies(text);
        HuffmanNode root = HuffmanCoding.buildTree(frequencies);
        Map<Character, String> codes = HuffmanCoding.generateCodes(root);
        String encoded = HuffmanCoding.encode(text, codes);
        String decoded = HuffmanCoding.decode(encoded, root);
        assertEquals(text, decoded);
    }

    @Test
    @DisplayName("Single-character input always produces a usable code")
    void generateCodes_singleSymbol() {
        Map<Character, Integer> frequencies = Map.of('a', 5);
        HuffmanNode root = HuffmanCoding.buildTree(frequencies);
        Map<Character, String> codes = HuffmanCoding.generateCodes(root);
        assertEquals("0", codes.get('a'));
    }

    @Test
    @DisplayName("Empty frequencies produce a null tree")
    void buildTree_emptyInput() {
        assertNull(HuffmanCoding.buildTree(Map.of()));
    }

    @Test
    @DisplayName("Frequent symbols receive shorter codewords")
    void generateCodes_frequentSymbolsAreShortest() {
        String text = "aaaaabbbcc";
        Map<Character, Integer> frequencies = HuffmanCoding.countFrequencies(text);
        HuffmanNode root = HuffmanCoding.buildTree(frequencies);
        Map<Character, String> codes = HuffmanCoding.generateCodes(root);
        assertTrue(codes.get('a').length() <= codes.get('b').length());
        assertTrue(codes.get('b').length() <= codes.get('c').length());
    }
}
