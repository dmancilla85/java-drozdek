package org.drozdek.lists;

import org.drozdek.lists.nodes.DoubleLinkedListNode;

/// Doubly circular linked list data structure where the last node points to the first node
/// and the first node points to the last node.
///
/// Abstract Data Type: Doubly circular linked list
///
/// This implementation extends DoubleLinkedList but modifies the addToTail() method to maintain
/// the circular structure, ensuring that the head's previous points to the tail and the tail's
/// next points to the head. The head is always the most recently added element.
///
/// Time Complexities:
///
/// - addToTail(): O(1) - constant time insertion at the tail
/// - delete(): O(n) worst case, O(1) for head/tail deletions
/// - find(): O(n) - may need to traverse the entire list
/// - first()/last(): O(1) - direct access to head/tail
/// - isEmpty(): O(1) - direct check of head
/// - printAll(): O(n) - traversal of all elements
/// - removeFromTail(): O(1) - constant time removal from the tail
/// - size(): O(n) - traversal to count elements
/// - viewHeadNode()/viewTailNode(): O(1) - direct access to head/tail references
///
/// Note: Due to the circular nature, special care is needed in traversal methods to avoid
/// infinite loops by checking if we've returned to the head node.
///
/// Bibliography:
///
/// - Donald E. Knuth. *The Art of Computer Programming, Volume 1: Fundamental Algorithms*,
///   Third Edition. Addison-Wesley, 1997. Section 2.2.3: Linked lists.
/// - Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.
///   *Introduction to Algorithms*, Third Edition. MIT Press, 2009. Chapter 10:
///   Elementary Data Structures.
/// - Peter Brass. *Advanced Data Structures*. Cambridge University Press, 2008.
///   Section 2.2: Doubly-linked lists.
/// - William Fiset. *Data Structures: Circular Linked Lists*. YouTube, 2020.
public class DoubleCircularLinkedList<T> extends DoubleLinkedList<T> {

    /// Adds a new element to the tail (end) of the doubly circular list.
    /// Overrides the base class method to maintain circular structure.
    ///
    /// @param data the data value to store in the new node
    ///
    /// Time Complexity: O(1) - constant time insertion at the tail
    ///
    /// Example:
    /// ```java
    /// DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
    /// list.addToTail(1);  // List: [1] (1's next and previous point to itself)
    /// list.addToTail(2);  // List: [1, 2] (2's next points to 1, 1's previous points to 2)
    /// list.addToTail(3);  // List: [1, 2, 3] (3's next points to 1, 1's previous points to 3)
    /// ```
    @Override
    public void addToTail(T data) {
        if (isEmpty()) {
            head = new DoubleLinkedListNode<>(data);
            head.setNext(head);     // Point to itself in empty list
            head.setPrevious(head); // Point to itself in empty list
            tail = head;
        } else {
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(data, head, tail);
            head.setPrevious(node);
            tail.setNext(node);
            tail = node;
        }
    }

    /// Deletes the first node containing the specified data value from the doubly circular list.
    /// If multiple nodes contain the same data, only the first occurrence is deleted.
    /// Special handling is implemented for head and tail deletions to maintain circular references.
    ///
    /// @param data the data value to search for and delete
    ///
    /// Time Complexity: O(n) in the worst case, where n is the number of elements.
    /// O(1) for deletions at head or tail.
    ///
    /// Note: If the list is empty or the data is not found, this method does nothing.
    @Override
    public void delete(T data) {
        if (head == null)
            return;

        if (data.equals(head.getData())) {
            if (head == tail) {
                head = tail = null;
                return;
            }
            tail.setNext(head.getNext());
            head = head.getNext();
            head.setPrevious(tail);
        } else {
            if (data.equals(tail.getData())) {
                removeFromTail();
            } else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.getNext();
                boolean flag = true;

                while (tmp != null && !tmp.getData().equals(data) && flag) {
                    predecessor = predecessor.getNext();
                    tmp = tmp.getNext();
                    flag = tmp != tail;
                }

                if (tmp != null) {
                    tmp.getNext().setPrevious(predecessor);
                    predecessor.setNext(tmp.getNext());
                }
            }
        }
    }

    /// Searches for the first occurrence of a node containing the specified data value.
    ///
    /// @param data the data value to search for
    /// @return the data value if found, or null if not found or list is empty
    ///
    /// Time Complexity: O(n) in the worst case, where n is the number of elements.
    /// O(1) in the best case when the element is at the head.
    @Override
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && !data.equals(tmp.getData())) {
            tmp = tmp.getNext();
            flag = tmp != head;
        }

        return !flag ? null : tmp.getData();
    }

    @Override
    public String toString() {
        int s = size();
        if (s <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        DoubleLinkedListNode<T> tmp = head;

        sb.append("┌⇄  ");
        while (count < s) {
            sb.append(tmp.getData());
            count++;
            if (count < s) {
                sb.append("  ⇄  ");
            }
            tmp = tmp.getNext();
        }
        sb.append("  ⇄┐");
        String top = sb.toString();

        return top + System.lineSeparator()
                + "└" + "─".repeat(top.length() - 2) + "┘";
    }

    /// Removes and returns the data value of the tail node (last element).
    ///
    /// @return the data value of the removed tail node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time removal from the tail
    ///
    /// Note: After this operation, the second-to-last node becomes the new tail,
    /// and the head's previous pointer is updated to point to the new tail.
    @Override
    public T removeFromTail() {
        T el = tail.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(head);
        }

        return el;
    }

    /// Returns the number of elements in this doubly circular linked list.
    ///
    /// @return the number of elements in this list
    ///
    /// Time Complexity: O(n) where n is the number of elements, due to traversal.
    ///
    /// Note: This implementation does not maintain a size counter, so it requires
    /// a full traversal to count elements, stopping when we return to the head.
    @Override
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        boolean flag = true;

        while (flag && tmp != null) {
            size++;
            tmp = tmp.getNext();
            flag = tmp != head;
        }
        return size;
    }
}
