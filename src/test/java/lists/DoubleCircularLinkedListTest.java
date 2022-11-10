package lists;

import org.drozdek.lists.CircularLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircularLinkedListTest {

    @Test
    void isEmpty() {
        CircularLinkedList list = new CircularLinkedList();
        assertTrue (list.isEmpty());
    }

    @Test
    void first() {
        CircularLinkedList list=new CircularLinkedList();
        Integer a = 2, b=3, c=4;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);

        Integer first= (Integer) list.first();
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void printAll() {
        CircularLinkedList list = new CircularLinkedList();
        list.add(43);
        list.add("hello");
        list.add(12.34);

        list.printAll(System.out);
    }

    @Test
    void add() {
        CircularLinkedList list=new CircularLinkedList();
        Integer a = 2;

        list.add(a);

        assertTrue(!list.isEmpty());
    }

    @Test
    void find() {
        CircularLinkedList list=new CircularLinkedList();
        Integer a = 2, b=3, c=4, d=10, e=16;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        Integer match = (Integer) list.find(t);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void deleteHead() {
        CircularLinkedList list=new CircularLinkedList();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        Integer match = (Integer) list.deleteHead();
        Integer check =(Integer)  list.find(e);
        assertEquals(0, match.compareTo(e));
        assertTrue(check == null);
    }

    @Test
    void delete() {
        CircularLinkedList list=new CircularLinkedList();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.delete(c);
        Integer check =(Integer)  list.find(c);

        assertTrue(check == null);
    }
}