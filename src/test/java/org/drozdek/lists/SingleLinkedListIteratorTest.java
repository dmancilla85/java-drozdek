package org.drozdek.lists;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListIteratorTest {

    @Test
    @DisplayName("Iterator on empty list has no next element")
    void iterator_emptyList() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        Iterator<String> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterator next on empty list throws NoSuchElementException")
    void iterator_nextOnEmpty() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        Iterator<String> it = list.iterator();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("Iterator traverses all elements in LIFO order")
    void iterator_traversal() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterator remove throws UnsupportedOperationException")
    void iterator_remove() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThrows(UnsupportedOperationException.class, it::remove);
    }
}
