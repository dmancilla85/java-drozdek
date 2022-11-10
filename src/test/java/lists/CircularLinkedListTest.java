package lists;

import org.drozdek.lists.CircularLinkedList;
import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.SingleLinkedListNode;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    void add() {
        CircularLinkedList list = new CircularLinkedList();
        Integer a = 2;

        list.add(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void addToTail() {
        CircularLinkedList list = new CircularLinkedList();
        Integer a = 2, b = 3, c = 5, size = 3;

        list.add(a);
        list.add(b);
        list.addToTail(c);
        list.printAll(out);
        out.println("Size is " + list.size());
        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void delete() {
        CircularLinkedList list = new CircularLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        out.println("Before deleting " + c + ":");
        list.printAll(out);

        list.delete(c);

        out.println("After deleting " + c + ":");
        list.printAll(out);

        Integer check = (Integer) list.find(c);
        assertNull(check);
    }

    @Test
    void deleteHead() {
        CircularLinkedList<Integer> list = new CircularLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.printAll(out);
        Integer match =list.deleteHead();

        list.printAll(out);
        Integer check = list.find(a);

        assertEquals(0, match.compareTo(a));
        assertNull(check);
    }

    @Test
    void find() {
        CircularLinkedList<Object> list = new CircularLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.printAll(out);

        Integer match = (Integer) list.find(t);
        assertNotNull(match);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void first() {
        CircularLinkedList list = new CircularLinkedList();
        Integer a = 2, b = 3, c = 4;
        Integer t = 2;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.printAll(out);

        Integer first = (Integer) list.first();
        out.println("First element is " + first + ".");
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void isEmpty() {
        CircularLinkedList list = new CircularLinkedList();
        assertTrue(list.isEmpty());
    }

    @Test
    void printAll() {
        CircularLinkedList list = new CircularLinkedList();
        list.addToTail(43);
        list.addToTail("hello");
        list.addToTail(12.34);

        list.printAll(out);
        assertNotNull(list);
    }

    @Test
    void size() {
        CircularLinkedList list = new CircularLinkedList();
        Integer a = 2, size = 4;

        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);

        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void viewHead(){
        SingleLinkedList list = new SingleLinkedList();
        String test = "bye";

        list.add("Hi");
        list.add(5);
        list.add(234.34);
        list.add("bye");

        SingleLinkedListNode node = list.viewHeadNode();
        out.println(node);
        assertEquals(node.getData(),test);
    }
}