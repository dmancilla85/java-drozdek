package org.drozdek.stacks.applications;

import org.drozdek.stacks.AdaptiveStack;

/// Tracks an editing history with undo and redo using a pair of adaptive stacks.
///
/// The undo stack records every action as it occurs; undoing pops the most
/// recent action and moves it onto the redo stack, while redoing moves it back.
/// New actions clear the redo history, which is the standard behavior of text
/// editors and design tools. The adaptive stack representation is a natural fit
/// because edit histories grow and shrink dramatically over a session.
///
/// **Real-world use case:** Undo/redo in text editors, image editors, and
/// transactional UI workflows.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized per operation
/// Auxiliary Space: O(n) for the combined history
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see AdaptiveStack
public class UndoRedoManager {

    private final AdaptiveStack<String> undo;
    private final AdaptiveStack<String> redo;
    private int undoSize;
    private int redoSize;

    /// Creates an empty undo/redo history.
    public UndoRedoManager() {
        this.undo = new AdaptiveStack<>();
        this.redo = new AdaptiveStack<>();
        this.undoSize = 0;
        this.redoSize = 0;
    }

    /// Records a new action, invalidating the redo history.
    ///
    /// @param action description of the action
    public void perform(String action) {
        undo.push(action);
        undoSize++;
        redo.clear();
        redoSize = 0;
    }

    /// Reverts the most recent action.
    ///
    /// @return the reverted action, or null if there is nothing to undo
    public String undo() {
        if (undo.isEmpty()) {
            return null;
        }
        String action = undo.pop();
        undoSize--;
        redo.push(action);
        redoSize++;
        return action;
    }

    /// Re-applies the most recently undone action.
    ///
    /// @return the restored action, or null if there is nothing to redo
    public String redo() {
        if (redo.isEmpty()) {
            return null;
        }
        String action = redo.pop();
        redoSize--;
        undo.push(action);
        undoSize++;
        return action;
    }

    /// Returns the number of actions currently available to undo.
    ///
    /// @return undo history depth
    public int undoSize() {
        return undoSize;
    }

    /// Returns the number of actions currently available to redo.
    ///
    /// @return redo history depth
    public int redoSize() {
        return redoSize;
    }
}
