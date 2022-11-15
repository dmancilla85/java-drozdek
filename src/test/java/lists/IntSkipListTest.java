package lists;

import org.drozdek.lists.IntSkipList;
import org.drozdek.lists.IntSkipListNode;
import org.drozdek.stacks.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class IntSkipListTest {

    IntSkipList list;

    @BeforeEach
    void setUp() {
        list = new IntSkipList();
    }

    @Test
    @DisplayName("A new list always is empty")
    void isEmpty() {
        assertTrue (list.isEmpty(),"The list should be empty");
    }

    @Test
    @DisplayName("Insert a key in an empty list should work")
    void insert(){

        list.insert(10);

        assertFalse (list.isEmpty(),"The list should contain at least one element");
    }

    @Test
    @DisplayName("Insert many numbers in a empty list should work")
    void insertManyNumbers(){

        list.insert(10);
        list.insert(2);
        list.insert(6);
        list.insert(1);
        list.insert(67);
        list.insert(24);
        list.insert(63);
        list.insert(12);
        list.insert(160);
        list.insert(29);
        list.insert(66);
        list.insert(15);
        list.insert(103);
        list.insert(222);
        list.insert(676);
        list.insert(17);

        out.println("List with inserted elements");
        list.printAll(out);

       assertEquals(16, list.size(), "The list size doesn't match with the expected");
    }

    @Test
    @DisplayName("Search for a specific key should work")
    void search(){

        list.insert(10);
        list.insert(2);
        list.insert(6);
        list.insert(1);

        int match = list.search(6);

        assertEquals(6, match, "The expected key doesn't match with the result");
    }
}
