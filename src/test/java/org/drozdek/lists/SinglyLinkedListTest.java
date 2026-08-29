package org.drozdek.lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.nodes.SinglyLinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void isEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue (list.isEmpty());
    }

    @Test
    void first() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2, b=3, c=4;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);

        Integer first= list.first();
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void printAll() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("43");
        list.add("hello");
        list.add("12.34");

        int sizeBefore = list.size();
        String firstBefore = list.first();

        list.print();

        assertEquals(sizeBefore, list.size(), "List size unchanged after printAll");
        assertEquals(firstBefore, list.first(), "First element unchanged after printAll");
    }

    @Test
    void add() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2;

        list.add(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void size() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2,size=4;

        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);

        assertEquals(0,size.compareTo(list.size()));
    }

    @Test
    void find() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.print();

        Integer match = list.find(t);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void deleteHead() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        LoggerService.logInfo("Before deleting head: ");
        list.print();
        Integer match = list.deleteHead();

        LoggerService.logInfo("After deleting head: ");
        list.print();

        Integer check =list.find(e);
        assertEquals(0, match.compareTo(e));
        assertNull(check);
    }

    @Test
    void delete() {
        SinglyLinkedList<Integer> list=new SinglyLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        LoggerService.logInfo("Before deleting " + c + ": ");
        list.print();
        list.delete(c);

        LoggerService.logInfo("After deleting " + c + ": ");
        list.print();

        Integer check = list.find(c);

        assertNull(check);
    }

    @Test
    void viewHead(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        String test = "bye";

        list.add("Hi");
        list.add("5");
        list.add("234.34");
        list.add("bye");

        SinglyLinkedListNode<String> node = list.viewHeadNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(), test);
    }
}
