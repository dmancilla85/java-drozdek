package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.nodes.DoublyLinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void add() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2;

        list.addToTail(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void delete() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        list.delete(c);
        Integer check = list.find(c);

        assertNull(check);
    }

    @Test
    void deleteFromHead() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        list.delete(a);
        Integer check = list.first();

        assertEquals(0, check.compareTo(b));
    }

    @Test
    void deleteFromTail() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;
        LoggerService.logInfo("Testing delete from tail...");
        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);
        list.print();
        LoggerService.logInfo("Removing " + e + "...");
        list.delete(e);
        Integer check = list.last();
        list.print();
        LoggerService.logInfo("Printing in reverse order:");
        list.printReverse();
        assertEquals(0, check.compareTo(d));
    }

    @Test
    void find() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;
        Integer t = 4;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        Integer match = list.find(t);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void first() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4;
        Integer t = 2;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);

        Integer first = list.first();
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void isEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void print() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addToTail("<H>");
        list.addToTail("43");
        list.addToTail("hello");
        list.addToTail("12.34");
        list.addToTail("56.34");
        list.addToTail("c");
        list.addToTail("<T>");

        int sizeBefore = list.size();
        String firstBefore = list.first();

        list.print();

        assertEquals(sizeBefore, list.size(), "List size unchanged after printAll");
        assertEquals(firstBefore, list.first(), "First element unchanged after printAll");
    }

    @Test
    void removeFromTail() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        Integer match = list.removeFromTail();
        Integer check = list.find(e);
        LoggerService.logInfo("Removed tail is " + match);
        assertEquals(0, match.compareTo(e));
        assertNull(check);
    }

    @Test
    void size() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Integer a = 2, size = 4;

        list.addToTail(a);
        list.addToTail(a);
        list.addToTail(a);
        list.addToTail(a);

        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void viewHead(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        String test = "Hi";

        list.addToTail("Hi");
        list.addToTail("5");
        list.addToTail("$234.34");
        list.addToTail("bye");

        DoublyLinkedListNode<String> node = list.viewHeadNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(),test);
    }

    @Test
    void viewTail(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        String test = "bye";

        list.addToTail("Hi");
        list.addToTail("5");
        list.addToTail("234.34");
        list.addToTail("bye");

        DoublyLinkedListNode<String> node = list.viewTailNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(),test);
    }
}
