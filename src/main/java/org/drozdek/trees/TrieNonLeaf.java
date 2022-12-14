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

    public String toString(){

        StringBuilder node=new StringBuilder();
        node.append(letters);
        node.append("-");

        for (TrieNode trieNode : ptr) {
            node.append(trieNode);
            node.append(System.lineSeparator());
        }
        return node.toString();
    }

    public TrieNonLeaf(){
      this(Character.MIN_VALUE);
    }


}
