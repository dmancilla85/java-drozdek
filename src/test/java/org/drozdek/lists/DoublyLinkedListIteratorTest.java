package org.drozdek.lists;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListIteratorTest {

    @Test
    @DisplayName("Iterator on empty list has no next element")
    void iterator_emptyList() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Iterator<String> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterator next on empty list throws NoSuchElementException")
    void iterator_nextOnEmpty() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Iterator<String> it = list.iterator();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("Iterator traverses all elements in insertion order")
    void iterator_traversal() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterator remove throws UnsupportedOperationException")
    void iterator_remove() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThrows(UnsupportedOperationException.class, it::remove);
    }
}
