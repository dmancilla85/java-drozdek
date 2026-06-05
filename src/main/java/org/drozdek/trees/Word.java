package org.drozdek.trees;

import org.drozdek.commons.LoggerService;

/// Word with frequency for splay tree. Stores a word string and its occurrence count.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(1)
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/splay-tree/)
public class Word implements Comparable<Word> {
    private final String words;
    public int freq;

    public Word(String s) {
        this.words = s;
        freq = 1;
    }

    /// @param o the object to be compared.
    @Override
    public int compareTo(Word o) {
        return words.compareTo(o.words);
    }

    @Override
    public String toString() {
        return words + " (" + freq + ") ";
    }

    public void print() {
        LoggerService.logInfo(toString());
    }
}
