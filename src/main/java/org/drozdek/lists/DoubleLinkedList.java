package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.interfaces.ListInterface;
import org.drozdek.lists.iterators.DoubleLinkedListIterator;
import org.drozdek.lists.nodes.DoubleLinkedListNode;

import java.util.Iterator;

/// Doubly-linked list data structure with references to both head and tail nodes.
///
/// Abstract Data Type: Doubly-linked list
///
/// This implementation maintains references to both the head (first) and tail (last)
/// nodes, enabling efficient O(1) insertions and deletions at both ends of the list.
/// Each node contains references to both its next and previous nodes, allowing
/// bidirectional traversal.
///
/// Time Complexities:
///
/// - addToTail(): O(1)
/// - removeFromTail(): O(1)
/// - delete(): O(n) worst case, O(1) for head/tail deletions
/// - find(): O(n)
/// - first()/last(): O(1)
/// - isEmpty(): O(1)
/// - size(): O(n)
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
public class DoubleLinkedList<T> implements Iterable<T>, ListInterface<T> {
    protected DoubleLinkedListNode<T> head;
    protected DoubleLinkedListNode<T> tail;

    /// Constructs an empty doubly-linked list.
    ///
    /// Time Complexity: O(1)
    public DoubleLinkedList() {
        head = tail = null;
    }

    /// Adds a new element to the tail (end) of the list.
    ///
    /// @param data the data value to store in the new node
    ///
    /// Time Complexity: O(1) - constant time insertion at the tail
    ///
    /// Example:
    /// ```java
    /// DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
    /// list.addToTail(1);  // List: [1]
    /// list.addToTail(2);  // List: [1, 2]
    /// list.addToTail(3);  // List: [1, 2, 3]
    /// ```
    public void addToTail(T data) {
        if (!isEmpty()) {
            tail = new DoubleLinkedListNode<>(data, null, tail);
            tail.getPrevious().setNext(tail);
        } else head = tail = new DoubleLinkedListNode<>(data);
    }

    /// Deletes the first node containing the specified data value from the list.
    /// If multiple nodes contain the same data, only the first occurrence is deleted.
    /// Special handling is implemented for head and tail deletions to maintain references.
    ///
    /// @param data the data value to search for and delete
    ///
    /// Time Complexity: O(n) in the worst case, where n is the number of elements.
    /// O(1) for deletions at head or tail.
    ///
    /// Note: If the list is empty or the data is not found, this method does nothing.
    public void delete(T data) {
        if (head == null) return;
        if (data.equals(head.getData())) {
            head = head.getNext();
            if (head != null) head.setPrevious(null);
            if (head == null) tail = null;
        } else {
            if (data.equals(tail.getData())) {
                removeFromTail();
            } else {
                DoubleLinkedListNode<T> predecessor = head;
                DoubleLinkedListNode<T> tmp = head.getNext();
                while (tmp != null && !tmp.getData().equals(data)) {
                    predecessor = predecessor.getNext();
                    tmp = tmp.getNext();
                }
                if (tmp != null) {
                    predecessor.setNext(tmp.getNext());
                    if (tmp.getNext() != null) tmp.getNext().setPrevious(predecessor);
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
    public T find(T data) {
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null && !data.equals(tmp.getData())) {
            tmp = tmp.getNext();
        }
        if (tmp == null)
            return null;

        else return tmp.getData();
    }

    /// Returns the data value of the head node (first element) without removing it.
    ///
    /// @return the data value of the head node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time access to the head
    public T first() {
        return head != null ? head.getData() : null;
    }

    /// Tests if this doubly-linked list contains no elements.
    ///
    /// @return true if the list is empty (head is null), false otherwise
    ///
    /// Time Complexity: O(1) - constant time check
    public boolean isEmpty() {
        return head == null;
    }

    /// Returns an iterator over the elements in this list in proper sequence.
    /// The iterator will traverse the list from head to tail.
    ///
    /// @return an iterator over the elements in this list
    ///
    /// Time Complexity: O(1) to create the iterator, O(n) for full traversal
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<T>(this);
    }

    /// Returns the data value of the tail node (last element) without removing it.
    ///
    /// @return the data value of the tail node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time access to the tail
    public T last() {
        return tail != null ? tail.getData() : null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NULL <-> ");
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null) {
            sb.append(tmp.getData());
            if (tmp.getNext() != null) {
                sb.append(" <-> ");
            }
            tmp = tmp.getNext();
        }
        sb.append(" <-> NULL");
        return sb.toString();
    }

    /// Returns a string representation of this list in reverse order (tail to head).
    ///
    /// @return a string in the format "NULL <-> data_n <-> ... <-> data_1 <-> NULL"
    ///
    /// Time Complexity: O(n) where n is the number of elements.
    public String toStringReverse() {
        StringBuilder sb = new StringBuilder();
        sb.append("NULL <-> ");
        DoubleLinkedListNode<T> tmp = tail;
        while (tmp != null) {
            sb.append(tmp.getData());
            if (tmp.getPrevious() != null) {
                sb.append(" <-> ");
            }
            tmp = tmp.getPrevious();
        }
        sb.append(" <-> NULL");
        return sb.toString();
    }

    /// Prints all elements in the list in reverse order (from tail to head) to the logger service.
    ///
    /// Time Complexity: O(n) where n is the number of elements, due to traversal and logging.
    ///
    /// Note: This method uses LoggerService.logInfo() for output, not System.out.println().
    public void printReverse() {
        LoggerService.logInfo(this.showId() +
                System.lineSeparator() +
                toStringReverse());
    }

    /// Removes and returns the data value of the tail node (last element).
    ///
    /// @return the data value of the removed tail node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time removal from the tail
    ///
    /// Note: After this operation, the second-to-last node becomes the new tail.
    public T removeFromTail() {
        T el = tail.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }

        return el;
    }

    /// Returns the number of elements in this doubly-linked list.
    ///
    /// @return the number of elements in this list
    ///
    /// Time Complexity: O(n) where n is the number of elements, due to traversal.
    ///
    /// Note: This implementation does not maintain a size counter, so it requires
    /// a full traversal to count elements.
    public int size() {
        int size = 0;
        DoubleLinkedListNode<T> tmp = head;
        while (tmp != null) {
            ++size;
            tmp = tmp.getNext();
        }
        return size;
    }

    /// Returns the head node of this doubly-linked list without removing it.
    ///
    /// @return the head node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time access to the head reference
    ///
    /// Note: This method exposes the internal node structure. Use with caution
    /// as it allows direct manipulation of the list's internal structure.
    public void add(T data) {
        addToTail(data);
    }

    public DoubleLinkedListNode<T> viewHeadNode() {
        return head;
    }

    /// Returns the tail node of this doubly-linked list without removing it.
    ///
    /// @return the tail node, or null if the list is empty
    ///
    /// Time Complexity: O(1) - constant time access to the tail reference
    ///
    /// Note: This method exposes the internal node structure. Use with caution
    /// as it allows direct manipulation of the list's internal structure.
    public DoubleLinkedListNode<T> viewTailNode() {
        return tail;
    }
}
