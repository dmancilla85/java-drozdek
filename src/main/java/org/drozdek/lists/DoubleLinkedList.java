package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.iterators.DoubleLinkedListIterator;

import java.util.Iterator;

/**
 * Doubly-linked list data structure with references to both head and tail nodes.
 * 
 * <p>
 * Abstract Data Type: Doubly-linked list
 * 
 * <p>
 * This implementation maintains references to both the head (first) and tail (last) 
 * nodes, enabling efficient O(1) insertions and deletions at both ends of the list.
 * Each node contains references to both its next and previous nodes, allowing 
 * bidirectional traversal.
 * 
 * <p>
 * Time Complexities:
 * <ul>
 *   <li>addToTail(): O(1)</li>
 *   <li>removeFromTail(): O(1)</li>
 *   <li>delete(): O(n) worst case, O(1) for head/tail deletions</li>
 *   <li>find(): O(n)</li>
 *   <li>first()/last(): O(1)</li>
 *   <li>isEmpty(): O(1)</li>
 *   <li>size(): O(n)</li>
 * </ul>
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
 * </ul>
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    protected DoubleLinkedListNode<T> head;
    protected DoubleLinkedListNode<T> tail;

    /**
     * Constructs an empty doubly-linked list.
     * 
     * Time Complexity: O(1)
     */
    public DoubleLinkedList() {
        head = tail = null;
    }

    /**
     * Adds a new element to the tail (end) of the list.
     * 
     * @param data the data value to store in the new node
     * 
     * Time Complexity: O(1) - constant time insertion at the tail
     * 
     * Example:
     * <pre>
     *   DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
     *   list.addToTail(1);  // List: [1]
     *   list.addToTail(2);  // List: [1, 2]
     *   list.addToTail(3);  // List: [1, 2, 3]
     * </pre>
     */
    public void addToTail(T data) {
        if (!isEmpty()) {
            tail = new DoubleLinkedListNode<>(data, null, tail);
            tail.previous.next = tail;
        } else head = tail = new DoubleLinkedListNode<>(data);
    }

    /**
     * Deletes the first node containing the specified data value from the list.
     * If multiple nodes contain the same data, only the first occurrence is deleted.
     * Special handling is implemented for head and tail deletions to maintain references.
     * 
     * @param data the data value to search for and delete
     * 
     * Time Complexity: O(n) in the worst case, where n is the number of elements.
     *                  O(1) for deletions at head or tail.
     * 
     * Note: If the list is empty or the data is not found, this method does nothing.
     */
    public void delete(T data) {
        if (head == null) return;
        if (data.equals(head.data)) {
            head = head.next;
            if (head != null) head.previous = null;
            if (head == null) tail = null;
        } else {
            if (data.equals(tail.data)) { removeFromTail(); }
            else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.next;
                while (tmp != null && !(tmp.data.equals(data))) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                }
                if (tmp != null) {
                    predecessor.next = tmp.next;
                    if (tmp.next != null) tmp.next.previous = predecessor;
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
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null && !data.equals(tmp.data)) {
            tmp = tmp.next;
        }
        if (tmp == null)
            return null;

        else return tmp.data;
    }

    /**
     * Returns the data value of the head node (first element) without removing it.
     * 
     * @return the data value of the head node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the head
     */
    public T first() {
        return head != null ? head.data : null;
    }

    /**
     * Tests if this doubly-linked list contains no elements.
     * 
     * @return true if the list is empty (head is null), false otherwise
     * 
     * Time Complexity: O(1) - constant time check
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * The iterator will traverse the list from head to tail.
     * 
     * @return an iterator over the elements in this list
     * 
     * Time Complexity: O(1) to create the iterator, O(n) for full traversal
     */
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(this);
    }

    /**
     * Returns the data value of the tail node (last element) without removing it.
     * 
     * @return the data value of the tail node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the tail
     */
    public T last() {
        return tail != null ? tail.data : null;
    }

    /**
     * Prints all elements in the list to the logger service in the format [e1 e2 e3 ...].
     * Elements are printed in order from head to tail.
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal and string building.
     * 
     * Note: This method uses LoggerService.logInfo() for output, not System.out.println().
     */
    public void printAll() {
        StringBuilder line = new StringBuilder();
        line.append("[");

        for (T element : this) {
            line.append(element);
            line.append(" ");
        }
        line.append("]");

        LoggerService.logInfo(line.toString());
    }

    /**
     * Prints all elements in the list in reverse order (from tail to head) to the logger service.
     * Each element is printed on a separate line with special formatting to indicate the head.
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal and logging.
     * 
     * Note: This method uses LoggerService.logInfo() for output, not System.out.println().
     */
    public void printReverse() {
        String line;
        for (DoubleLinkedListNode<T> tmp = tail; tmp != null; tmp = tmp.previous) {
            line = tmp == head ? "(*)" + tmp.data : "- " + tmp.data;
            LoggerService.logInfo(line);
        }
    }

    /**
     * Removes and returns the data value of the tail node (last element).
     * 
     * @return the data value of the removed tail node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time removal from the tail
     * 
     * Note: After this operation, the second-to-last node becomes the new tail.
     */
    public T removeFromTail() {
        T el = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        return el;
    }

    /**
     * Returns the number of elements in this doubly-linked list.
     * 
     * @return the number of elements in this list
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal.
     * 
     * Note: This implementation does not maintain a size counter, so it requires
     *        a full traversal to count elements.
     */
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null) {
            ++size;
            tmp = tmp.next;
        }
        return size;
    }

    /**
     * Returns the head node of this doubly-linked list without removing it.
     * 
     * @return the head node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the head reference
     * 
     * Note: This method exposes the internal node structure. Use with caution
     *        as it allows direct manipulation of the list's internal structure.
     */
    public DoubleLinkedListNode<T> viewHeadNode() {
        return head;
    }

    /**
     * Returns the tail node of this doubly-linked list without removing it.
     * 
     * @return the tail node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the tail reference
     * 
     * Note: This method exposes the internal node structure. Use with caution
     *        as it allows direct manipulation of the list's internal structure.
     */
    public DoubleLinkedListNode<T> viewTailNode() {
        return tail;
    }
}
