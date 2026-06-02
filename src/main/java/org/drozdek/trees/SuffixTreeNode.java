package org.drozdek.trees;

import java.util.Arrays;
import java.util.Iterator;

/// Node for suffix tree. Stores descendant pointers, left/right interval arrays, and a suffix link
/// used during Ukkonen's algorithm.
///
/// Complexity Analysis:
/// Time Complexity: O(1)
/// Auxiliary Space: O(|alphabet|) per node
///
/// Source: [Geeks for Geeks](https://www.geeksforgeeks.org/ukkonens-suffix-tree-construction-part-1/)
class SuffixTreeNode {
    private static int cnt = 0; // for printing only
    protected final SuffixTreeNode[] descendants;
    protected final int[] left;
    protected final int[] right;
    protected final int id; // for printing only
    protected SuffixTreeNode suffixLink;

    SuffixTreeNode(int sz) {
        id = cnt++;
        suffixLink = null;
        descendants = new SuffixTreeNode[sz];
        left = new int[sz];
        right = new int[sz];

        for (int i = 0; i < sz; i++)
            left[i] = -1;
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.id);
        buffer.append(System.lineSeparator());

        for (Iterator<SuffixTreeNode> it = Arrays.stream(descendants).iterator();it.hasNext();) {
            SuffixTreeNode next = it.next();

            if (next == null)
                continue;

            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        buffer.append(System.lineSeparator());
        print(buffer, "", "");
        return buffer.toString();
    }
}
