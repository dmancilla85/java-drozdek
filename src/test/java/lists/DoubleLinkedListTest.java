package lists;

import org.drozdek.lists.DoubleLinkedList;
import org.drozdek.lists.DoubleLinkedListNode;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    void add() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2;

        list.addToTail(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void delete() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
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
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        list.delete(a);
        Integer check = (Integer) list.first();

        assertEquals(0, check.compareTo(b));
    }

    @Test
    void deleteFromTail() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;
        out.println("Testing delete from tail...");
        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        out.println("Removing " + e + "...");
        list.delete(e);
        Integer check = (Integer) list.last();
        out.println("Printing in reverse order:");
        list.printReverse(out);
        assertEquals(0, check.compareTo(d));
    }

    @Test
    void find() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
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
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2, b = 3, c = 4;
        Integer t = 2;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);

        Integer first = (Integer) list.first();
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void isEmpty() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        assertTrue(list.isEmpty());
    }

    @Test
    void printAll() {
        DoubleLinkedList<String> list = new DoubleLinkedList();
        list.addToTail("<H>");
        list.addToTail("43");
        list.addToTail("hello");
        list.addToTail("12.34");
        list.addToTail("56.34");
        list.addToTail("c");
        list.addToTail("<T>");

        out.println("Printing list elements: ");
        list.printAll(out);
        assertNotNull(list);
    }

    @Test
    void removeFromTail() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2, b = 3, c = 4, d = 10, e = 16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        Integer match = list.removeFromTail();
        Integer check = list.find(e);
        out.println("Removed tail is " + match);
        assertEquals(0, match.compareTo(e));
        assertNull(check);
    }

    @Test
    void size() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        Integer a = 2, size = 4;

        list.addToTail(a);
        list.addToTail(a);
        list.addToTail(a);
        list.addToTail(a);

        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void viewHead(){
        DoubleLinkedList<String> list = new DoubleLinkedList();
        String test = "Hi";

        list.addToTail("Hi");
        list.addToTail("5");
        list.addToTail("$234.34");
        list.addToTail("bye");

        DoubleLinkedListNode<String> node = list.viewHeadNode();
        out.println(node);
        assertEquals(node.getData(),test);
    }

    @Test
    void viewTail(){
        DoubleLinkedList<String> list = new DoubleLinkedList();
        String test = "bye";

        list.addToTail("Hi");
        list.addToTail("5");
        list.addToTail("234.34");
        list.addToTail("bye");

        DoubleLinkedListNode<String> node = list.viewTailNode();
        out.println(node);
        assertEquals(node.getData(),test);
    }
}