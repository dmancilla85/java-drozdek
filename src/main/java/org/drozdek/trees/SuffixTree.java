package org.drozdek.trees;

import static java.lang.System.out;

/**
 *
 */
public class SuffixTree {
    protected SuffixTreeNode root;
    protected int size;
    protected int offset;
    protected String text;
    private int left = 1;
    private boolean endPoint;

    public SuffixTree() {
        this(0, 127);
    }

    public SuffixTree(int from, int to) {
        size = to - from + 1;
        offset = from;
        root = new SuffixTreeNode(size);
        root.suffixLink = root;
    }

    private SuffixTreeNode findCanonicalNode(SuffixTreeNode p, int rt) {
        if (rt >= left) {
            int pos = text.charAt(left) - offset;
            SuffixTreeNode pp = p.descendants[pos];
            int lt = p.left[pos];
            int rt2 = p.right[pos];
            while (rt2 - lt <= rt - left) {
                left = left + rt2 - lt + 1;
                p = pp;
                if (left <= rt) {
                    pos = text.charAt(left) - offset;
                    pp = p.descendants[pos];
                    lt = p.left[pos];
                    rt2 = p.right[pos];
                    if (p == root)
                        pp = root;
                }
            }
        }
        return p;
    }

    private void printTree(SuffixTreeNode p, int lvl, int lt, int rt, int pos) {
        for (int i = 1; i <= lvl; i++)
            System.out.print("   ");
        if (p != null) {                    // if a nonleaf
            if (p == root)
                System.out.println(p.id);
            else if (p.suffixLink != null) // to print in the middle of
                System.out.println(text.substring(lt, rt + 1) // update
                        + " " + p.id + " " + p.suffixLink.id
                        + " [" + lt + " " + rt + "]");
            else System.out.println(text.substring(lt, pos + 1) + " " + p.id);
            for (char i = 0; i < size; i++)
                if (p.left[i] != -1)       // if a tree node
                    printTree(p.descendants[i], lvl + 1, p.left[i], p.right[i], pos);
        } else
            System.out.println(text.substring(lt, pos + 1) + " [" + lt + " " + rt + "]");
    }

    public void printTree(int pos) {
        System.out.println();
        printTree(root, 0, 0, 0, pos);
    }

    SuffixTreeNode testAndSplit(SuffixTreeNode p, int i) {
        int rt = i - 1;

        if (left <= rt) {
            int pos = text.charAt(left) - offset;
            SuffixTreeNode pp = p.descendants[pos];
            int lt = p.left[pos];
            int rt2 = p.right[pos];

            if (text.charAt(i) == text.charAt(lt + rt - left + 1)) { // if T(lt..rt) is
                endPoint = true;                      // and extension of
                return p;                             // T(Lt..i)
            } else {
                /*
                 insert a new node r between s and ss by splitting
                 edge(p,pp) = T(lt..rt) into
                 edge(p,r)  = T(lt..lt+Rt-Lt) and
                 edge(r,pp) = T(lt+Rt-Lt+1..rt)
                */
                pos = text.charAt(lt) - offset;
                SuffixTreeNode r = p.descendants[pos] = new SuffixTreeNode(size);
                p.right[pos] = lt + rt - left;
                pos = text.charAt(lt + rt - left + 1) - offset;
                r.descendants[pos] = pp;
                r.left[pos] = lt + rt - left + 1;
                r.right[pos] = rt2;
                endPoint = false;
                return r;
            }
        } else endPoint = p.left[text.charAt(i) - offset] != -1;
        return p;
    }

    /**
     * In 1995, Esko Ukkonen proposed a linear time algorithm for creating suffix trees i.e., Ukkonen's Algorithm.
     * Initially, an implicit suffix tree is created that contains the first character of the string. Then, as you move
     * forward, it keeps on adding the other characters till the tree is complete. The orderly addition of characters
     * in the tree maintains the on-line property of this algorithm.
     * <p>
     * *Implicit Trees are those intermediate trees that we encounter while creating the suffix tree of a given string
     * using Ukkonen's Algorithm.
     * <p>
     * We understand that the working example of such a complex algorithm could prove to be a very important step for
     * your learning. But, first read the below 2 statements to understand the basics.
     *
     * @param text
     */
    public void ukkonen(String text) {
        this.text = text;
        final int n = this.text.length();
        final int pos = this.text.charAt(0) - offset;
        SuffixTreeNode canonicalNodeAP = root;
        SuffixTreeNode canonicalNodeEP;
        root.left[pos] = 0;
        root.right[pos] = n - 1;

        for (int i = 1; i < n; i++) {
            canonicalNodeEP = update(canonicalNodeAP, i);
            // and thus, endpoint = node(canonicalNodeEP,Lt,i)
            canonicalNodeAP = findCanonicalNode(canonicalNodeEP, i);
            // and so, active point = node(canonicalNodeAP,Lt,i)
            printTree(i);
        }
    }

    private SuffixTreeNode update(SuffixTreeNode p, int i) {
        SuffixTreeNode prev = null;
        SuffixTreeNode r = testAndSplit(p, i);
        while (!endPoint) {
            int pos = text.charAt(i) - offset;
            r.left[pos] = i;      // add a T(i)-edge to r
            r.right[pos] = text.length() - 1;

            if (prev != null)
                prev.suffixLink = r;

            prev = r;

            if (p == root)
                left++;
            else p = p.suffixLink;

            p = findCanonicalNode(p, i - 1);
            r = testAndSplit(p, i); // check if not the endpoint
        }
        if (prev != null)
            prev.suffixLink = p;
        return p;
    }

}

