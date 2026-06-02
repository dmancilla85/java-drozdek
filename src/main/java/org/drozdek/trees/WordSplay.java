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
        InputStream fIn;
        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            if (args.length == 0) {
                System.out.println("Enter a filename: ");
                fileName = buffer.readLine();
                fIn = new FileInputStream(fileName);
            } else {
                fIn = new FileInputStream(args[0]);
                fileName = args[0];
            }
            new WordSplay().run(fIn, fileName);
            fIn.close();

        } catch (IOException io) {
            System.err.println("Error: " + io.getMessage());
        }
    }

    public void run(InputStream fIn, String filename) {
        int ch = 1;
        Word p;

        try {
            while (ch > -1) {
                while (true) {
                    if (ch > -1 && !Character.isLetter((char) ch))
                        ch = fIn.read();
                    else
                        break;
                }

                if (ch == -1)
                    break;
                StringBuilder s = new StringBuilder();

                while (ch > -1 && Character.isLetter((char) ch)) {
                    s.append(Character.toUpperCase((char) ch));
                    ch = fIn.read();
                }

                if ((p = (Word) search(new Word(s.toString()))) == null)
                    insert(new Word(s.toString()));
                else
                    p.freq++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inorder(System.out);
        System.out.println("\nFile " + filename + " contains " + wordCnt + " words whose " + differentWords +
                " are different.\n");

    }

}
