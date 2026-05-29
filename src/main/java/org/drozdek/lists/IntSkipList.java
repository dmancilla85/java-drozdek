package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.interfaces.ListInterface;
import org.drozdek.lists.nodes.IntSkipListNode;

import java.util.Random;

/**
 * Skip list for integer values - a probabilistic data structure that provides fast search,
 * insertion, and deletion operations.
 *
 * <p>
 * Abstract Data Type: Skip list
 *
 * <p>
 * This implementation provides an expected O(log n) time complexity for search, insertion,
 * and deletion operations, and O(n) space complexity. The skip list consists of multiple
 * layers of linked lists, where each layer skips over a certain number of elements.
 *
 * <p>
 * The structure maintains an array of references to the head nodes at each level, and uses
 * randomization to determine the level of newly inserted nodes.
 *
 * <p>
 * Time Complexities (expected):
 * <ul>
 *   <li>insert(): O(log n)</li>
 *   <li>search(): O(log n)</li>
 *   <li>isEmpty(): O(1)</li>
 *   <li>printAll(): O(n)</li>
 *   <li>size(): O(n)</li>
 * </ul>
 *
 * <p>
 * Space Complexity: O(n)
 *
 * <p>
 * Bibliography:
 * <ul>
 *   <li>William Pugh. <cite>Skip Lists: A Probabilistic Alternative to Balanced Trees</cite>.
 *       Communications of the ACM, June 1990.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 12:
 *       Binary Search Trees, Skip Lists section.</li>
 *   <li>Eric W. Weisstein. <cite>Skip List</cite>. From MathWorld--A Wolfram Web Resource.</li>
 * </ul>
 */
public class IntSkipList implements ListInterface<Integer> {
    /**
     * Maximum level allowed in this skip list
     */
    private final int maximumLevel;
    /**
     * Array of references to head nodes at each level
     */
    private final IntSkipListNode[] root;
    /**
     * Precomputed powers used in level selection
     */
    private final int[] powers;
    /**
     * Random number generator for probabilistic level selection
     */
    private final Random rd = new Random();

    /**
     * Constructs a skip list with the default maximum level of 4.
     * <p>
     * Time Complexity: O(1)
     */
    public IntSkipList() {
        this(4);
    }

    /**
     * Constructs a skip list with the specified maximum level.
     *
     * @param maxLevel the maximum level allowed in this skip list
     *                 Higher values allow for taller towers but use more memory
     *                 <p>
     *                 Time Complexity: O(maxLevel) for initialization
     */
    public IntSkipList(int maxLevel) {
        maximumLevel = maxLevel;
        root = new IntSkipListNode[maximumLevel];
        powers = new int[maximumLevel];

        // Initialize all head references to null
        for (int j = 0; j < maximumLevel; j++)
            root[j] = null;

        choosePowers();
    }

    /**
     * Checks for the current valid level starting from the given level and moving downward
     * until a non-null next pointer is found or we reach level -1.
     *
     * @param level   the starting level to check from
     * @param current the current node to check
     * @return the highest level <= level where current.next[level] is not null, or -1 if none found
     */
    private int checkForCurrentLevel(int level, IntSkipListNode current) {
        int lvl = level;

        while (lvl >= 0 && current.next()[lvl] == null) {
            lvl--;
        }

        return lvl;
    }

    /**
     * Chooses a random level for a new node based on a probability distribution.
     * The method uses precomputed powers to determine the level probabilistically.
     *
     * @return the chosen level for a new node (0 to maximumLevel-1)
     */
    private int chooseLevel() {
        int i;
        int r = (rd.nextInt(Integer.MAX_VALUE) + 1) % powers[maximumLevel - 1] + 1;

        for (i = 1; i < maximumLevel; i++)
            if (r < powers[i])
                return i - 1;
        return i - 1;
    }

    /**
     * Precomputes the powers array used in the probabilistic level selection.
     * The powers array helps determine the probability distribution for node levels.
     */
    private void choosePowers() {
        powers[maximumLevel - 1] = (1 << maximumLevel) - 1; // 2^maximumLevel-1

        for (int i = maximumLevel - 2, j = 0; i >= 0; i--, j++)
            powers[i] = powers[i + 1] - (1 << (j + 1)); // 2^(j+1)
    }

    /**
     * Finds the highest level that has a non-null head reference.
     *
     * @return the highest level with a non-null head reference, or -1 if the list is empty
     */
    private int findMajorNotNullValue() {
        int lvl = maximumLevel - 1;
        while (lvl >= 0 && root[lvl] == null) {
            lvl--;
        }

        return lvl;
    }

    /**
     * Adds an element to the skip list.
     *
     * @param data the integer value to add
     *             <p>
     *             Time Complexity: O(log n) expected
     */
    public void add(Integer data) {
        if (data != null) {
            insert(data);
        }
    }

    /**
     * Inserts a new element into the skip list.
     * If an element with the same key already exists, the method returns without inserting.
     *
     * @param key the integer key to insert
     *            <p>
     *            Time Complexity: O(log n) expected, where n is the number of elements
     */
    public void insert(int key) {
        IntSkipListNode[] previous = new IntSkipListNode[maximumLevel];
        IntSkipListNode[] current = new IntSkipListNode[maximumLevel];
        int currentLevel;

        // Start from the highest level and work our way down
        current[maximumLevel - 1] = root[maximumLevel - 1];
        previous[maximumLevel - 1] = null;

        for (currentLevel = maximumLevel - 1; currentLevel >= 0; currentLevel--) {
            // Move forward at the current level while the next node's key is less than the key to insert
            while (current[currentLevel] != null && current[currentLevel].key() < key) {
                previous[currentLevel] = current[currentLevel];
                current[currentLevel] = current[currentLevel].next()[currentLevel];
            }

            // If we found a node with the same key, don't insert (no duplicates)
            if (current[currentLevel] != null && current[currentLevel].key() == key)
                return;

            // Move down to the next lower level
            if (currentLevel > 0) {
                if (previous[currentLevel] == null) {
                    // We're at the beginning of the level
                    current[currentLevel - 1] = root[currentLevel - 1];
                    previous[currentLevel - 1] = null;
                } else {
                    // Move to the next node at the lower level
                    current[currentLevel - 1] = previous[currentLevel].next()[currentLevel - 1];
                    previous[currentLevel - 1] = previous[currentLevel];
                }
            }
        }

        // Choose a random level for the new node
        currentLevel = chooseLevel();

        // Insert the new node at the chosen level and all lower levels
        insertKeyValue(key, currentLevel, previous, current);
    }

    /**
     * Helper method to insert a new node with the given key at the specified level.
     *
     * @param key          the key value for the new node
     * @param currentLevel the level at which to insert the node (0-based)
     * @param previous     array of nodes that should point to the new node at each level
     * @param current      array of nodes that the new node should point to at each level
     */
    private void insertKeyValue(int key, int currentLevel, IntSkipListNode[] previous, IntSkipListNode[] current) {
        // Create the new node with the appropriate number of levels
        IntSkipListNode newNode = new IntSkipListNode(key, currentLevel + 1);

        // Update the forward pointers at each level from 0 to currentLevel
        for (int i = 0; i <= currentLevel; i++) {
            newNode.next()[i] = current[i];

            // If we're inserting at the beginning of the level, update the head reference
            if (previous[i] == null)
                root[i] = newNode;
            else
                // Otherwise, link the new node after the previous node
                previous[i].next()[i] = newNode;
        }
    }

    /**
     * Tests if this skip list contains no elements.
     *
     * @return true if the skip list is empty, false otherwise
     * <p>
     * Time Complexity: O(1) - direct check of the level 0 head reference
     */
    public boolean isEmpty() {
        return root[0] == null;
    }

    @Override
    public String toString() {
        if (root[0] == null) {
            return "";
        }

        int maxKeyLen = 0;
        IntSkipListNode node = root[0];
        while (node != null) {
            maxKeyLen = Math.max(maxKeyLen, String.valueOf(node.key()).length());
            node = node.next()[0];
        }

        int innerWidth = Math.max(4, maxKeyLen + 2);

        int lvl = findMajorNotNullValue();
        int height = lvl + 1;

        StringBuilder sb = new StringBuilder();

        // Head line
        String headInner = "Head" + " ".repeat(Math.max(0, innerWidth - 4));
        sb.append("[").append(headInner).append("]");
        sb.append(" ── (Height: ").append(height).append(")").append(System.lineSeparator());

        // Data nodes
        node = root[0];
        while (node != null) {
            String keyStr = String.valueOf(node.key());
            String padded = " " + keyStr + " ".repeat(Math.max(0, innerWidth - 1 - keyStr.length()));
            sb.append("[").append(padded).append("]");
            sb.append(" ── ");

            int levelCount = node.next().length;
            for (int i = 0; i < levelCount; i++) {
                if (i > 0) sb.append(" ");
                sb.append("█");
            }
            sb.append(System.lineSeparator());

            node = node.next()[0];
        }

        // Tail line
        String tailInner = "Tail" + " ".repeat(Math.max(0, innerWidth - 4));
        sb.append("[").append(tailInner).append("]");
        sb.append(" ── (Height: ").append(height).append(")");

        return sb.toString();
    }

    public void print() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                this);
    }

    /**
     * Searches for an element with the specified key in the skip list.
     *
     * @param key the key to search for
     * @return the key if found, or 0 if not found or the list is empty
     *
     * Time Complexity: O(log n) expected, where n is the number of elements
     */
    /**
     * Searches for the specified integer value in the skip list.
     *
     * @param data the integer value to search for
     * @return the value if found, or null if not found or the list is empty
     * <p>
     * Time Complexity: O(log n) expected
     */
    public Integer find(Integer data) {
        if (data == null || isEmpty())
            return null;
        int key = data;
        int result = search(key);
        if (result == key) {
            if (key != 0)
                return result;
            return root[0] != null && root[0].key() == 0 ? 0 : null;
        }
        return null;
    }

    /**
     * Deletes the first occurrence of the specified value from the skip list.
     *
     * @param data the integer value to delete
     *             <p>
     *             Time Complexity: O(log n) expected
     */
    public void delete(Integer data) {
        if (data == null || isEmpty())
            return;
        int key = data;

        int lvl = findMajorNotNullValue();
        if (lvl < 0)
            return;

        IntSkipListNode[] previous = new IntSkipListNode[maximumLevel];
        IntSkipListNode current = root[lvl];

        for (int i = lvl; i >= 0; i--) {
            previous[i] = null;

            while (current != null && current.key() < key) {
                previous[i] = current;
                current = current.next()[i];
            }

            if (current != null && current.key() == key) {
                if (previous[i] == null)
                    root[i] = current.next()[i];
                else
                    previous[i].next()[i] = current.next()[i];
            }

            if (i > 0) {
                if (previous[i] == null)
                    current = root[i - 1];
                else
                    current = previous[i].next()[i - 1];
            }
        }
    }

    /**
     * Returns the first element in the skip list without removing it.
     *
     * @return the first element, or null if the list is empty
     * <p>
     * Time Complexity: O(1)
     */
    public Integer first() {
        if (isEmpty())
            return null;
        return root[0].key();
    }

    public int search(int key) {
        // Start from the highest non-empty level
        int lvl = findMajorNotNullValue();

        if (lvl < 0)
            return 0;

        IntSkipListNode previous;
        IntSkipListNode current;

        // Start at the head of the highest non-empty level
        previous = current = root[lvl];

        while (true) {
            // If we found the key, return it
            if (key == current.key())
                return current.key();

                // If the key is less than the current node's key, move down a level
            else if (key < current.key()) {
                if (lvl == 0)
                    return 0;  // We've reached the bottom level and haven't found the key
                else if (current == root[lvl])
                    // We're at the head of this level, move to the head of the lower level
                    current = root[--lvl];
                else
                    // Move to the next node at the lower level
                    current = previous.next()[--lvl];
            } else {
                // The key is greater than the current node's key, move forward at the current level
                previous = current;

                if (current.next()[lvl] != null)
                    // Move forward at the current level
                    current = current.next()[lvl];
                else {
                    // No forward pointer at this level, move down a level
                    lvl--;

                    lvl = checkForCurrentLevel(lvl, current);

                    if (lvl >= 0)
                        // Move forward at the lower level
                        current = current.next()[lvl];
                    else
                        // We've moved below level 0, key not found
                        return 0;
                }
            }
        }
    }

    /**
     * Returns the number of elements in this skip list.
     *
     * @return the number of elements in this skip list
     * <p>
     * Time Complexity: O(n) where n is the number of elements, due to traversal.
     * <p>
     * Note: This implementation does not maintain a size counter, so it requires
     * a full traversal of the level 0 linked list to count elements.
     */
    public int size() {
        int size = 0;
        IntSkipListNode tmp = root[0];

        while (tmp != null) {
            size++;
            tmp = tmp.next()[0];
        }

        return size;
    }
}
