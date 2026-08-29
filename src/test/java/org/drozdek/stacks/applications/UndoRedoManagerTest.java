package org.drozdek.stacks.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UndoRedoManagerTest {

    @Test
    @DisplayName("Undo reverts the most recent action")
    void undo_reverts() {
        UndoRedoManager manager = new UndoRedoManager();
        manager.perform("type a");
        manager.perform("type b");
        assertEquals("type b", manager.undo());
        assertEquals(1, manager.undoSize());
        assertEquals(1, manager.redoSize());
    }

    @Test
    @DisplayName("Redo restores an undone action")
    void redo_restores() {
        UndoRedoManager manager = new UndoRedoManager();
        manager.perform("type a");
        manager.undo();
        assertEquals("type a", manager.redo());
        assertEquals(1, manager.undoSize());
        assertEquals(0, manager.redoSize());
    }

    @Test
    @DisplayName("A new action clears the redo history")
    void perform_clearsRedo() {
        UndoRedoManager manager = new UndoRedoManager();
        manager.perform("type a");
        manager.undo();
        manager.perform("type b");
        assertEquals(0, manager.redoSize());
        assertNull(manager.redo());
    }

    @Test
    @DisplayName("Undo on an empty history returns null")
    void undo_empty() {
        assertNull(new UndoRedoManager().undo());
    }
}
