package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.interfaces.ListInterface;
import org.drozdek.lists.iterators.SingleLinkedListIterator;

import java.util.Iterator;

/**
 * Singly-linked list data structure with head pointing to the most recently added element.
 * 
 * <p>
 * Abstract Data Type: Singly-linked list (LIFO - Last In, First Out)
 * 
 * <p>
 * This implementation maintains a reference only to the head node, which always points
 * to the most recently added element. This makes add() and deleteHead() operations O(1),
 * while find() and delete() operations are O(n) in the worst case.
 * 
 * <p>
 * Bibliography:
 * <ul>
 *   <li>Donald E. Knuth. <cite>The Art of Computer Programming, Volume 1: Fundamental Algorithms</cite>, 
 *       Third Edition. Addison-Wesley, 1997. Section 2.2.3: Linked lists.</li>
 *   <li>Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. 
 *       <cite>Introduction to Algorithms</cite>, Third Edition. MIT Press, 2009. Chapter 10: 
 *       Elementary Data Structures.</li>
 *   <li>William Fiset. <cite>Data Structures: Linked Lists</cite>. YouTube, 2020.</li>
 * </ul>
 */
public class SingleLinkedList<T> implements Iterable<T>, ListInterface<T> {
    protected SingleLinkedListNode<T> head;

    /**
     * Constructs an empty singly-linked list.
     * 
     * Time Complexity: O(1)
     */
    public SingleLinkedList() {
        head = null;
    }

    /**
     * Adds a new element to the front of the list (as the new head).
     * This implements a LIFO (Last-In, First-Out) behavior.
     * 
     * @param data the data value to store in the new node
     * 
     * Time Complexity: O(1) - constant time insertion at the head
     * 
     * Example:
     * <pre>
     *   SingleLinkedList<Integer> list = new SingleLinkedList<>();
     *   list.add(1);  // List: [1]
     *   list.add(2);  // List: [2, 1]
     *   list.add(3);  // List: [3, 2, 1]
     * </pre>
     */
    public void add(T data) {
        head = new SingleLinkedListNode<>(data, head);
    }

    /**
     * Deletes the first node containing the specified data value from the list.
     * If multiple nodes contain the same data, only the first occurrence is deleted.
     * 
     * @param data the data value to search for and delete
     * 
     * Time Complexity: O(n) in the worst case, where n is the number of elements.
     *                  O(1) in the best case when the element to delete is at the head.
     * 
     * Note: If the list is empty or the data is not found, this method does nothing.
     */
    public void delete(T data) {
        if (head != null) {
            if (data.equals(head.data)) {
                deleteHead();
            } else {
                SingleLinkedListNode<T> predecessor = head;
                SingleLinkedListNode<T> tmp = head.next;
                while (tmp != null && !(tmp.data.equals(data))) {
                    predecessor = predecessor.next;
                    tmp = tmp.next;
                }

                if (tmp != null)
                    predecessor.next = tmp.next;
            }
        }
    }

    /**
     * Deletes and returns the data value of the head node (most recently added element).
     * 
     * @return the data value of the removed head node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time removal from the head
     * 
     * Note: After this operation, the second node becomes the new head.
     */
    public T deleteHead() {
        T el = head.data;
        head = head.next;
        return el;
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
        SingleLinkedListNode<T> tmp = head;
        while (tmp != null && !data.equals(tmp.data)) {
            tmp = tmp.next;
        }
        if (tmp == null)
            return null;

        else return tmp.data;
    }

    /**
     * Returns the data value of the head node (most recently added element) without removing it.
     * 
     * @return the data value of the head node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the head
     */
    public T first() {
        return head != null ? head.data : null;
    }

    /**
     * Tests if this singly-linked list contains no elements.
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
     * The iterator will traverse the list from head to tail (most recently added to least recently added).
     * 
     * @return an iterator over the elements in this list
     * 
     * Time Complexity: O(1) to create the iterator, O(n) for full traversal
     */
    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>(this);
    }

    /**
     * Prints all elements in the list to the logger service in the format [e1 e2 e3 ...].
     * Elements are printed in order from head to tail (most recently added to least recently added).
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
     * Returns the number of elements in this singly-linked list.
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
        SingleLinkedListNode<T> tmp = head;
        while (tmp != null) {
            ++size;
            tmp = tmp.next;
        }
        return size;
    }

    /**
     * Returns the head node of this singly-linked list without removing it.
     * 
     * @return the head node, or null if the list is empty
     * 
     * Time Complexity: O(1) - constant time access to the head reference
     * 
     * Note: This method exposes the internal node structure. Use with caution
     *        as it allows direct manipulation of the list's internal structure.
     */
    public SingleLinkedListNode<T> viewHeadNode() {
        return head;
    }
}

