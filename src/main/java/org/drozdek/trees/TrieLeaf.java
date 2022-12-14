package org.drozdek.trees;

public class TrieLeaf extends TrieNode{
    protected final String suffix;

    public TrieLeaf(String suffix){
        this.suffix=suffix;
        isLeaf=true;
    }

    @Override
    public String toString(){
        return suffix;
    }
}
