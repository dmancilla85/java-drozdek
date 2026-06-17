package org.drozdek.trees;

import java.io.*;

/// Splay tree specialized for Word objects. Reads a file, inserts words with frequency counting,
/// and prints the result in-order.
///
/// Complexity Analysis:
/// Time Complexity: O(n * log n) amortized for building the tree
/// Auxiliary Space: O(n) for storage
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/splay-tree/)
public class WordSplay extends SplayTree<Word> {
    private int differentWords;
    private int wordCnt;

    public WordSplay() {
        differentWords = wordCnt = 0;
    }

    public static void testSplaying(String[] args) {
        String fileName;
        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            if (args.length == 0) {
                System.out.println("Enter a filename: ");
                fileName = buffer.readLine();
            } else {
                fileName = args[0];
            }
            try (InputStream fIn = new FileInputStream(fileName)) {
                new WordSplay().run(fIn, fileName);
            }

        } catch (IOException io) {
            System.err.println("Error: " + io.getMessage());
        }
    }

    public void run(InputStream fIn, String filename) {
        try {
            processWords(fIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inorder(System.out);
        System.out.println("\nFile " + filename + " contains " + wordCnt + " words whose " + differentWords +
                " are different.\n");
    }

    private void processWords(InputStream fIn) throws IOException {
        String word;
        while ((word = nextWord(fIn)) != null) {
            Word p = search(new Word(word));
            if (p == null) {
                insert(new Word(word));
                differentWords++;
            } else {
                p.incrementFreq();
            }
            wordCnt++;
        }
    }

    private static String nextWord(InputStream fIn) throws IOException {
        int ch = fIn.read();
        while (ch > -1 && !Character.isLetter((char) ch)) {
            ch = fIn.read();
        }
        if (ch == -1)
            return null;

        StringBuilder s = new StringBuilder();
        while (ch > -1 && Character.isLetter((char) ch)) {
            s.append(Character.toUpperCase((char) ch));
            ch = fIn.read();
        }
        return s.toString();
    }

}
