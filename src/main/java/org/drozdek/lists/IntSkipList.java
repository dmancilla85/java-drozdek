package org.drozdek.lists;

import org.drozdek.commons.LoggerService;

import java.util.Random;

/**
 * Skip list for integer values.
 */
public class IntSkipList {
    private final int maximumLevel;
    private final IntSkipListNode[] root;
    private final int[] powers;
    private final Random rd = new Random();

    /**
     * Default constructor.
     */
    public IntSkipList() {
        this(4);
    }

    /**
     * Constructor.
     *
     * @param maxLevel Maximum level allowed
     */
    public IntSkipList(int maxLevel) {
        maximumLevel = maxLevel;
        root = new IntSkipListNode[maximumLevel];
        powers = new int[maximumLevel];

        for (int j = 0; j < maximumLevel; j++)
            root[j] = null;

        choosePowers();
    }

    private int checkForCurrentLevel(int level, IntSkipListNode current) {
        int lvl = level;

        while (lvl >= 0 && current.next[lvl] == null) {
            lvl--;
        }

        return lvl;
    }

    /**
     * Choose randomly between the superior level or a level minor than superior.
     *
     * @return chosen level
     */
    private int chooseLevel() {
        int i;
        int r = Math.abs(rd.nextInt()+1) % powers[maximumLevel - 1] + 1;

        for (i = 1; i < maximumLevel; i++)
            if (r < powers[i])
                return i - 1;
        return i - 1;
    }

    /**
     *
     */
    private void choosePowers() {
        powers[maximumLevel - 1] = (2 << (maximumLevel - 1)) - 1; // 2^maximumLevel-1

        for (int i = maximumLevel - 2, j = 0; i >= 0; i--, j++)
            powers[i] = powers[i + 1] - (2 << j); // 2^(j+1)
    }

    /**
     * Finds the major not null value.
     *
     * @return
     */
    private int findMajorNotNullValue() {
        int lvl = maximumLevel - 1;
        while (lvl >= 0 && root[lvl] == null) {
            lvl--;
        }

        return lvl;
    }

    /**
     * Insert new node.
     *
     * @param key Data value
     */
    public void insert(int key) {
        IntSkipListNode[] previous = new IntSkipListNode[maximumLevel];
        IntSkipListNode[] current = new IntSkipListNode[maximumLevel];
        int currentLevel;

        current[maximumLevel - 1] = root[maximumLevel - 1];
        previous[maximumLevel - 1] = null;

        for (currentLevel = maximumLevel - 1; currentLevel >= 0; currentLevel--) {

            while (current[currentLevel] != null && current[currentLevel].key < key) {
                previous[currentLevel] = current[currentLevel];
                current[currentLevel] = current[currentLevel].next[currentLevel];
            }

            if (current[currentLevel] != null && current[currentLevel].key == key)
                return;

            if (currentLevel > 0) {
                if (previous[currentLevel] == null) {
                    current[currentLevel - 1] = root[currentLevel - 1];
                    previous[currentLevel - 1] = null;
                } else {
                    current[currentLevel - 1] = previous[currentLevel].next[currentLevel - 1];
                    previous[currentLevel - 1] = previous[currentLevel];
                }
            }

        }

        currentLevel = chooseLevel();

        insertKeyValue(key, currentLevel, previous, current);
    }

    private void insertKeyValue(int key, int currentLevel, IntSkipListNode[] previous, IntSkipListNode[] current) {
        IntSkipListNode newNode = new IntSkipListNode(key, currentLevel + 1);

        for (int i = 0; i <= currentLevel; i++) {
            newNode.next[i] = current[i];

            if (previous[i] == null)
                root[i] = newNode;
            else
                previous[i].next[i] = newNode;
        }
    }

    /**
     * List is empty?
     *
     * @return True if list is empty
     */
    public boolean isEmpty() {
        return root[0] == null;
    }

    /**
     * Print all elements in the list
     *
     */
    public void printAll() {
        IntSkipListNode tmp = root[0];
        StringBuilder line=new StringBuilder();

        while (tmp != null) {
            line.append("(key: ");
            line.append(tmp.key);
            line.append(", next: ");
            line.append(tmp.next[0]);
            line.append(")");
            line.append(System.lineSeparator());
            tmp = tmp.next[0];
        }

        LoggerService.logInfo(line.toString());
    }

    /**
     * Search by key.
     *
     * @param key Data value
     * @return The key found
     */
    public int search(int key) {
        int lvl;
        IntSkipListNode previous;
        IntSkipListNode current;

        lvl = findMajorNotNullValue();

        previous = current = root[lvl];

        while (true) {
            if (key == current.key)
                return current.key;

            else if (key < current.key) {
                if (lvl == 0)
                    return 0;
                else if (current == root[lvl])
                    current = root[--lvl];
                else
                    current = previous.next[--lvl];
            } else {
                previous = current;

                if (current.next[lvl] != null)
                    current = current.next[lvl];
                else {
                    lvl--;

                    lvl = checkForCurrentLevel(lvl, current);

                    if (lvl >= 0)
                        current = current.next[lvl];
                    else
                        return 0;
                }
            }
        }
    }

    /**
     * List size.
     *
     * @return Number of elements in list.
     */
    public int size() {
        int size = 0;
        IntSkipListNode tmp = root[0];

        while (tmp != null) {
            size++;
            tmp = tmp.next[0];
        }

        return size;
    }
}
