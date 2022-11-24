package lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.DoubleCircularLinkedList;
import org.drozdek.lists.DoubleLinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCircularLinkedListTest {

    @Test
    void isEmpty() {
        DoubleCircularLinkedList<String> list = new DoubleCircularLinkedList<>();
        assertTrue (list.isEmpty());
    }

    @Test
    void first() {
        DoubleCircularLinkedList<Integer> list=new DoubleCircularLinkedList<>();
        Integer a = 2, b=3, c=4;
        Integer t = 2;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.printAll();
        Integer first= list.first();
        assertEquals(0, first.compareTo(t));
    }

    @Test
    void printAll() {
        DoubleCircularLinkedList<String>  list = new DoubleCircularLinkedList<>();
        list.addToTail("43");
        list.addToTail("hello");
        list.addToTail("12.34");
        list.printAll();
        assertNotNull(list);
    }

    @Test
    void size() {
        DoubleCircularLinkedList<String>  list = new DoubleCircularLinkedList<>();
        Integer size = 6;
        list.addToTail("hello");
        list.addToTail("43");
        list.addToTail("somebody");
        list.addToTail("€12.34");
        list.addToTail("2021");
        list.addToTail("bye");
        list.printAll();
        assertEquals(0, size.compareTo(list.size()));
    }

    @Test
    void add() {
        DoubleCircularLinkedList<Integer>  list=new DoubleCircularLinkedList<>();
        Integer a = 2;

        list.addToTail(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void find() {
        DoubleCircularLinkedList<Integer>  list=new DoubleCircularLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;
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
    void removeFromTail() {
        DoubleCircularLinkedList<Integer>  list=new DoubleCircularLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        Integer match = list.removeFromTail();
        Integer check =list.find(e);
        list.printAll();

        assertEquals(0, match.compareTo(e));
        assertNull(check);
    }

    @Test
    void delete() {
        DoubleCircularLinkedList<Integer>  list=new DoubleCircularLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);
        list.printAll();
        list.delete(c);
        list.printAll();
        Integer check =list.find(c);

        assertNull(check);
    }

    @Test
    void deleteFromHead() {
        DoubleCircularLinkedList<Integer>  list=new DoubleCircularLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.addToTail(a);
        list.addToTail(b);
        list.addToTail(c);
        list.addToTail(d);
        list.addToTail(e);

        LoggerService.logInfo("Before deleting head:");
        list.printAll();
        list.delete(a);

        LoggerService.logInfo("After deleting head:");
        list.printAll();

        Integer check =list.find(a);

        assertNull(check);
    }

    @Test
    void viewHead(){
        DoubleCircularLinkedList<Object> list = new DoubleCircularLinkedList<>();
        String test = "Hi";

        list.addToTail("Hi");
        list.addToTail(5);
        list.addToTail(234.34);
        list.addToTail("bye");

        DoubleLinkedListNode<Object> node = list.viewHeadNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(),test);
    }

    @Test
    void viewTail(){
        DoubleCircularLinkedList<Object> list = new DoubleCircularLinkedList<>();
        String test = "bye";

        list.addToTail("Hi");
        list.addToTail(5);
        list.addToTail(234.34);
        list.addToTail("bye");

        DoubleLinkedListNode<Object> node = list.viewTailNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(),test);
    }
}