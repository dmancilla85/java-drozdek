package org.drozdek.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SelfOrganizingListTest {

    @Test
    @DisplayName("Insertion places values at the front")
    void insert_placesAtFront() {
        SelfOrganizingList<String> list = new SelfOrganizingList<>();
        list.insert("c");
        list.insert("b");
        list.insert("a");
        assertEquals(List.of("a", "b", "c"), list.snapshot());
    }

    @Test
    @DisplayName("Accessing a non-front element moves it to the front")
    void access_movesToFront() {
        SelfOrganizingList<String> list = new SelfOrganizingList<>();
        list.insert("c");
        list.insert("b");
        list.insert("a");
        assertTrue(list.access("c"));
        assertEquals(List.of("c", "a", "b"), list.snapshot());
    }

    @Test
    @DisplayName("Accessing absent value reports false and changes nothing")
    void access_absentValue() {
        SelfOrganizingList<String> list = new SelfOrganizingList<>();
        list.insert("a");
        assertFalse(list.access("z"));
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Removal deletes the value")
    void remove_deletesValue() {
        SelfOrganizingList<String> list = new SelfOrganizingList<>();
        list.insert("a");
        list.insert("b");
        assertTrue(list.remove("a"));
        assertFalse(list.remove("a"));
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Size tracks entries")
    void size_tracksEntries() {
        SelfOrganizingList<Integer> list = new SelfOrganizingList<>();
        assertEquals(0, list.size());
        list.insert(1);
        list.insert(2);
        assertEquals(2, list.size());
    }
}
