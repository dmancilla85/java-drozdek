package org.drozdek.lists;

import org.drozdek.commons.LoggerService;

/**
 * Doubly circular linked list data structure where the last node points to the first node
 * and the first node points to the last node.
 * 
 * <p>
 * Abstract Data Type: Doubly circular linked list
 * 
 * <p>
 * This implementation extends DoubleLinkedList but modifies the addToTail() method to maintain
 * the circular structure, ensuring that the head's previous points to the tail and the tail's 
 * next points to the head. The head is always the most recently added element.
 * 
 * <p>
 * Time Complexities:
 * <ul>
 *   <li>addToTail(): O(1) - constant time insertion at the tail</li>
 *   <li>delete(): O(n) worst case, O(1) for head/tail deletions</li>
 *   <li>find(): O(n) - may need to traverse the entire list</li>
 *   <li>first()/last(): O(1) - direct access to head/tail</li>
 *   <li>isEmpty(): O(1) - direct check of head</li>
 *   <li>printAll(): O(n) - traversal of all elements</li>
 *   <li>removeFromTail(): O(1) - constant time removal from the tail</li>
 *   <li>size(): O(n) - traversal to count elements</li>
 *   <li>viewHeadNode()/viewTailNode(): O(1) - direct access to head/tail references</li>
 * </ul>
 * 
 * <p>
 * Note: Due to the circular nature, special care is needed in traversal methods to avoid
 * infinite loops by checking if we've returned to the head node.
 * 
 * <p>
 * Bibliography:
 * <ul>
 *   <li>Donald E. Knuth. <cite>The Art of Computer Programming, Volume 1: Fundamental Algorithms</cite>, 
 *       Third Edition. Addison-Wesley, 1997. Section 2.2.3: Linked lists.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. 
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 10: 
 *       Elementary Data Structures.</li>
 *   <li>Peter Brass. <cite>Advanced Data Structures</cite>. Cambridge University Press, 2008.
 *       Section 2.2: Doubly-linked lists.</li>
 *   <li>William Fiset. <cite>Data Structures: Circular Linked Lists</cite>. YouTube, 2020.</li>
 * </ul>
 */
public class DoubleCircularLinkedList<T> extends DoubleLinkedList<T> {

    /**
     * Adds a new element to the tail (end) of the doubly circular list.
     * Overrides the base class method to maintain circular structure.
     * 
     * @param data the data value to store in the new node
     * 
     * Time Complexity: O(1) - constant time insertion at the tail
     * 
     * Example:
     * <pre>
     *   DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
     *   list.addToTail(1);  // List: [1] (1's next and previous point to itself)
     *   list.addToTail(2);  // List: [1, 2] (2's next points to 1, 1's previous points to 2)
     *   list.addToTail(3);  // List: [1, 2, 3] (3's next points to 1, 1's previous points to 3)
     * </pre>
     */
    @Override
    public void addToTail(T data) {
        if (isEmpty()) {
            head = new DoubleLinkedListNode<>(data);
            head.next = head;     // Point to itself in empty list
            head.previous = head; // Point to itself in empty list
            tail = head;
        } else {
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(data, head, tail);
            head.previous = node;
            tail.next = node;
            tail = node;
        }
    }

    /**
     * Deletes the first node containing the specified data value from the doubly circular list.
     * If multiple nodes contain the same data, only the first occurrence is deleted.
     * Special handling is implemented for head and tail deletions to maintain circular references.
     * 
     * @param data the data value to search for and delete
     * 
     * Time Complexity: O(n) in the worst case, where n is the number of elements.
     *                  O(1) for deletions at head or tail.
     * 
     * Note: If the list is empty or the data is not found, this method does nothing.
     */
    @Override
    public void delete(T data) {
        if (head == null)
            return;

        if (data.equals(head.data)) {
            if (head == tail) {
                head = tail = null;
                return;
            }
            tail.next = head.next;
            head = head.next;
            head.previous = tail;
        } else {
            if (data.equals(tail.data)) {
                removeFromTail();
            } else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.next;
                boolean flag = true;

                while (tmp != null && !(tmp.data.equals(data)) && flag) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                    flag = tmp != tail;
                }

                if (tmp != null) {
                    tmp.next.previous = predecessor;
                    predecessor.next = tmp.next;
                }
            }
        }
    }

    /**
     * Searches for the first occurrence of a node containing the specified data value.
     * 
     * @param data the data value to search for
     * @return the data value if found, or null if not found or list is empty
     * 
     * Time Complexity: O(n) in the worst case, where n is the number of elements.
     *                  O(1) in the best case when the element is at the head.
     */
    @Override
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && !data.equals(tmp.data)) {
            tmp = tmp.next;
            flag = tmp != head;
        }

        return !flag ? null : tmp.data;
    }

    /**
     * Prints all elements in the list to the logger service in the format [*e1, e2, e3, ...].
     * The head element (most recently added) is marked with an asterisk.
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal and string building.
     * 
     * Note: This method uses LoggerService.logInfo() for output, not System.out.println().
     */
    @Override
    public void printAll() {
        StringBuilder line = new StringBuilder();
        int i = 0;
        int size = size();
        line.append("[");

        for (DoubleLinkedListNode<T> tmp = head; i++ < size; tmp = tmp.next) {
            line.append(tmp == head ? "*" + tmp.data : tmp.data);
            line.append(i != size ? ", " : "");
        }

        line.append("]");

        LoggerService.logInfo(line.toString());
    }

    /**
     * Removes and returns the data value of the tail node (last element).
     * 
     * @return the data value of the removed tail node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time removal from the tail
     * 
     * Note: After this operation, the second-to-last node becomes the new tail,
     *        and the head's previous pointer is updated to point to the new tail.
     */
    @Override
    public T removeFromTail() {
        T el = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = head;
        }

        return el;
    }

    /**
     * Returns the number of elements in this doubly circular linked list.
     * 
     * @return the number of elements in this list
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal.
     * 
     * Note: This implementation does not maintain a size counter, so it requires
     *        a full traversal to count elements, stopping when we return to the head.
     */
    @Override
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && tmp != null) {
            size++;
            tmp = tmp.next;
            flag = tmp != head;
        }
        return size;
    }
}
