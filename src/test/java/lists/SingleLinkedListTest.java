package lists;

import org.drozdek.commons.LoggerService;
import org.drozdek.lists.SingleLinkedList;
import org.drozdek.lists.SingleLinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    @Test
    void isEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertTrue (list.isEmpty());
    }

    @Test
    void first() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
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
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("43");
        list.add("hello");
        list.add("12.34");

        list.printAll();
        assertNotNull(list);
    }

    @Test
    void add() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
        Integer a = 2;

        list.add(a);

        assertFalse(list.isEmpty());
    }

    @Test
    void size() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
        Integer a = 2,size=4;

        list.add(a);
        list.add(a);
        list.add(a);
        list.add(a);

        assertEquals(0,size.compareTo(list.size()));
    }

    @Test
    void find() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;
        Integer t = 4;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.printAll();

        Integer match = list.find(t);
        assertEquals(0, match.compareTo(t));
    }

    @Test
    void deleteHead() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        LoggerService.logInfo("Before deleting head: ");
        list.printAll();
        Integer match = list.deleteHead();

        LoggerService.logInfo("After deleting head: ");
        list.printAll();

        Integer check =list.find(e);
        assertEquals(0, match.compareTo(e));
        assertNull(check);
    }

    @Test
    void delete() {
        SingleLinkedList<Integer> list=new SingleLinkedList<>();
        Integer a = 2, b=3, c=4, d=10, e=16;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        LoggerService.logInfo("Before deleting " + c + ": ");
        list.printAll();
        list.delete(c);

        LoggerService.logInfo("After deleting " + c + ": ");
        list.printAll();

        Integer check = list.find(c);

        assertNull(check);
    }

    @Test
    void viewHead(){
        SingleLinkedList<String> list = new SingleLinkedList<>();
        String test = "bye";

        list.add("Hi");
        list.add("5");
        list.add("234.34");
        list.add("bye");

        SingleLinkedListNode<String> node = list.viewHeadNode();
        LoggerService.logInfo(node.toString());
        assertEquals(node.getData(), test);
    }
}