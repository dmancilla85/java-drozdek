package lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.CircularLinkedList;
import org.drozdek.lists.SingleLinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    void add() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2;

        list.add(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void addToTail() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, b = 3, c = 5, size = 3;

        list.add(a);
        list.add(b);
        list.addToTail(c);
        list.printAll();
        LoggerService.logInfo("Size is " + list.size());
        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void delete() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        LoggerService.logInfo("Before deleting " + c + ":");
        list.printAll();

        list.delete(c);

        LoggerService.logInfo("After deleting " + c + ":");
        list.printAll();

        Integer check = list.find(c);
        assertNull(check);
    }

    @Test
    void deleteHead() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.printAll();
        Integer match =list.deleteHead();

        list.printAll();
        Integer check = list.find(a);

        assertEquals(0, match.compareTo(a));
        assertNull(check);
    }

    @Test
    void find() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.printAll();

        Integer match = list.find(t);
        assertNotNull(match);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void first() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, b = 3, c = 4;
        Integer t = 2;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.printAll();

        Integer first = list.first();
        LoggerService.logInfo("First element is " + first + ".");
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void isEmpty() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void printAll() {
        CircularLinkedList<Object> list = new CircularLinkedList<>();
        list.addToTail(43);
        list.addToTail("hello");
        list.addToTail(12.34);

        list.printAll();
        assertNotNull(list);
    }

    @Test
    void size() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Integer a = 2, size = 4;

        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);

        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void viewHead(){
        CircularLinkedList<Object> list = new CircularLinkedList<>();
        String test = "Hi";

        list.add("Hi");
        list.add(5);
        list.add(234.34);
        list.add("bye");

        SingleLinkedListNode<Object> node = list.viewHeadNode();
        LoggerService.logInfo(node.toString());
        assertEquals(test, node.getData());
    }
}