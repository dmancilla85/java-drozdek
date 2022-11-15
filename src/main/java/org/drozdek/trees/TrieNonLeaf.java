package org.drozdek.trees;

public class TrieNonLeaf extends TrieNode{
    protected boolean endOfWord;
    protected String letters;
    protected TrieNode[] ptr;

    public TrieNonLeaf(char ch){
        letters= "";
        letters+=ch;
        isLeaf=false;
        endOfWord=false;
        ptr =new TrieNode[1];
    }

    public TrieNonLeaf(){
      this(Character.MIN_VALUE);
    }


}
