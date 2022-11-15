package org.drozdek.trees;

public class Word implements Comparable {
    public int freq = 1;
    private String word = "";

    public Word() {
    }

    public Word(String s) {
        this.word = s;
    }

    @Override
    public int compareTo(Object el) {
        return word.compareTo(((Word) el).word);
    }

    @Override
    public boolean equals(Object el) {
        return el != null && word.equals(((Word) el).word);
    }

    @Override
    public String toString() {
        return word + " (" + freq + ") ";
    }
}
