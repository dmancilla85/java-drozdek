package org.drozdek.lists;

import org.drozdek.commons.LoggerService;

/**
 * Circular singly-linked list data structure where the last node points back to the first node.
 * 
 * <p>
 * Abstract Data Type: Circular singly-linked list
 * 
 * <p>
 * This implementation extends SingleLinkedList but modifies the add() method to add elements
 * to the tail instead of the head, and ensures the last node points back to the first node,
 * creating a circular structure. The head node still refers to the most recently added element.
 * 
 * <p>
 * Time Complexities:
 * <ul>
 *   <li>add(): O(n) - must traverse to find the tail</li>
 *   <li>delete(): O(n) - may need to traverse the entire list</li>
 *   <li>deleteHead(): O(n) - must find the node that points to head</li>
 *   <li>find(): O(n) - may need to traverse the entire list</li>
 *   <li>first(): O(1) - direct access to head</li>
 *   <li>isEmpty(): O(1) - direct check of head</li>
 *   <li>printAll(): O(n) - traversal of all elements</li>
 *   <li>size(): O(n) - traversal to count elements</li>
 *   <li>viewHeadNode(): O(1) - direct access to head</li>
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
 *   <li>William Fiset. <cite>Data Structures: Circular Linked Lists</cite>. YouTube, 2020.</li>
 * </ul>
 */
public class CircularLinkedList<T> extends SingleLinkedList<T> {

    /**
     * Adds a new element to the tail (end) of the circular list.
     * Overrides the base class method to maintain circular structure.
     * 
     * @param data the data value to store in the new node
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal to find the tail
     * 
     * Example:
     * <pre>
     *   CircularLinkedList<Integer> list = new CircularLinkedList<>();
     *   list.add(1);  // List: [1] (1 points to itself)
     *   list.add(2);  // List: [1, 2] (2 points to 1)
     *   list.add(3);  // List: [1, 2, 3] (3 points to 1)
     * </pre>
     */
    @Override
    public void add(T data) {
        addToTail(data);
    }

    /**
     * Adds a new element to the tail (end) of the circular list.
     * 
     * @param el the data value to store in the new node
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal to find the tail
     */
    public void addToTail(T el) {
        if (isEmpty()) {
            head = new SingleLinkedListNode<>(el);
            head.next = head;  // Point to itself in empty list
        } else {
            SingleLinkedListNode<T> tmp = head;

            // Traverse to find the last node (the one pointing to head)
            while (tmp.next != head)
                tmp = tmp.next;

            // Insert new node after tmp, pointing to head
            tmp.next = new SingleLinkedListNode<>(el, head);
        }
    }

    /**
     * Deletes the first node containing the specified data value from the circular list.
     * If multiple nodes contain the same data, only the first occurrence is deleted.
     * 
     * @param data the data value to search for and delete
     * 
     * Time Complexity: O(n) in the worst case, where n is the number of elements.
     *                  O(1) in the best case when the element to delete is the head and there's only one element.
     * 
     * Note: If the list is empty or the data is not found, this method does nothing.
     */
    @Override
    public void delete(T data) {
        if (head == null) return;

        if (data.equals(head.data)) {
            if (head.next == head) {
                head = null;
                return;
            }
            SingleLinkedListNode<T> tmp = head;
            while (tmp.next != head) tmp = tmp.next;
            head = head.next;
            tmp.next = head;
        } else {
            SingleLinkedListNode<T> predecessor = head;
            SingleLinkedListNode<T> tmp = head.next;
            boolean flag = true;

            while (flag && !(tmp.data.equals(data))) {
                predecessor = predecessor.next;
                tmp = tmp.next;
                flag = tmp != head;
            }

            if (flag) {
                predecessor.next = tmp.next;
            }
        }
    }

    /**
     * Deletes and returns the data value of the head node (most recently added element).
     * 
     * @return the data value of the removed head node, or null if the list is empty
     * 
     * Time Complexity: O(n) where n is the number of elements, due to traversal to find the node that points to head
     * 
     * Note: After this operation, the second-most recently added node becomes the new head,
     *        and the last node's next pointer is updated to point to the new head.
     */
    @Override
    public T deleteHead() {
        T el = head.data;
        SingleLinkedListNode<T> tmp = head;

        while (tmp.next != head)
            tmp = tmp.next;

        head = head.next;
        tmp.next = head;

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
    @Override
    public T find(T data) {
        SingleLinkedListNode<T> tmp = head;
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

        for (SingleLinkedListNode<T> tmp = head; i++ < size; tmp = tmp.next) {
            line.append(tmp == head ? "*" + tmp.data : tmp.data);
            line.append(i != size ? ", " : "");
        }
        line.append("]");

        LoggerService.logInfo(line.toString());
    }

    /**
     * Returns the number of elements in this circular singly-linked list.
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
        SingleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && tmp != null) {
            size++;
            tmp = tmp.next;
            flag = tmp != head;
        }
        return size;
    }
}
