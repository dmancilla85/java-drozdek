package org.drozdek.trees;

public class Word implements Comparable<Word> {
    protected int freq;
    private final String words;

    public Word(String s) {
        this.words = s;
        freq=1;
    }

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Word o) {
        return words.compareTo(o.words);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return words + " (" + freq + ") ";
    }
}
