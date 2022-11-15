package org.drozdek.trees;

public class TrieLeaf extends TrieNode{
    protected String suffix;

    public TrieLeaf(){
        this("");
    }

    public TrieLeaf(String suffix){
        this.suffix=suffix;
        isLeaf=true;
    }
}
