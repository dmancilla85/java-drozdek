package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

/// Stack backed by an ArrayList with unicode pretty-printing support.
///
/// **Real-world use case:** Educational tools and debug visualizations
/// showing stack state with formatted output.
///
/// Complexity Analysis:
/// Time Complexity: O(1) amortized for push and pop
/// Auxiliary Space: O(n) for storing n elements
///
/// Bibliography:
///
/// - Thomas H. Cormen et al. *Introduction to Algorithms*, 4th ed. MIT Press.
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
public class Stack<T> implements StackInterface<T> {
    private final ArrayList<T> pool;
    private boolean printWithUnicode;

    public void setPrintWithUnicode(boolean enabled) {
        this.printWithUnicode = enabled;
    }

    /// Default constructor.
    public Stack() {
        this(100, new ArrayList<>());
    }

    /// Constructor.
    ///
    /// @param n    size
    /// @param pool Storage pool
    private Stack(int n, ArrayList<T> pool) {
        this.pool = pool;
        this.printWithUnicode = true;
        if (n > 0)
            this.pool.ensureCapacity(n);
    }

    /// Renders the given element strings as a unicode box with TOP and BOTTOM markers.
    ///
    /// The first list item is marked as the bottom of the stack and the last one as the top.
    ///
    /// @param elementStrings string representation of each element, bottom first
    /// @return a boxed multi-line rendering, or an empty string when the list is empty
    static String formatStackBox(List<String> elementStrings) {
        if (elementStrings.isEmpty()) {
            return "";
        }

        int n = elementStrings.size();
        String[] contents = new String[n];
        int contentWidth = 0;
        for (int i = 0; i < n; i++) {
            String suffix;
            if (i == n - 1) {
                suffix = "  <-- [TOP]";
            } else if (i == 0) {
                suffix = "  <-- [BOTTOM]";
            } else {
                suffix = "";
            }
            contents[i] = elementStrings.get(i) + suffix;
            int len = contents[i].length();
            if (len > contentWidth) contentWidth = len;
        }

        int boxWidth = contentWidth + 3;
        String topBorder = "┌" + "─".repeat(boxWidth - 2) + "┐";
        String sepBorder = "├" + "─".repeat(boxWidth - 2) + "┤";
        String botBorder = "└" + "─".repeat(boxWidth - 2) + "┘";

        StringBuilder sb = new StringBuilder();
        sb.append(topBorder).append(System.lineSeparator());

        for (int i = 0; i < n; i++) {
            sb.append("│ ");
            sb.append(contents[i]);
            sb.append(" ".repeat(contentWidth - contents[i].length()));
            sb.append("│").append(System.lineSeparator());

            if (i < n - 1) {
                sb.append(sepBorder).append(System.lineSeparator());
            }
        }

        sb.append(botBorder).append(System.lineSeparator());
        return sb.toString();
    }

    /// Renders the given elements as a table of values, runtime types, and the top marker.
    ///
    /// The last element of the list is displayed at the top of the stack.
    ///
    /// @param elements elements in insertion order, bottom first
    /// @return a formatted multi-line listing, or an empty string when the list is empty
    static String formatStackList(List<?> elements) {
        if (elements.isEmpty()) return "";

        int n = elements.size();
        String[] values = new String[n];
        String[] types = new String[n];
        int maxValueLen = 0;
        int maxTypeLen = 0;

        for (int i = 0; i < n; i++) {
            Object e = elements.get(i);
            values[i] = e.toString();
            types[i] = e.getClass().getSimpleName();
            if (values[i].length() > maxValueLen) maxValueLen = values[i].length();
            if (types[i].length() > maxTypeLen) maxTypeLen = types[i].length();
        }

        int typeColPos = maxValueLen + 6;
        String header = " ─ STACK (" + n + " Item" + (n == 1 ? "" : "s") + ") ";
        int width = computeListWidth(n, values, types, typeColPos, header.length());

        return buildStackRows(values, types, n, typeColPos, width, header);
    }

    private static int computeListWidth(int n, String[] values, String[] types, int typeColPos, int headerLen) {
        int width = headerLen;
        for (int i = 0; i < n; i++) {
            int padLen = Math.max(1, typeColPos - values[i].length());
            int lineLen = 8 + values[i].length() + padLen + 2 + types[i].length();
            if (i == n - 1) lineLen += 7;
            if (lineLen > width) width = lineLen;
        }
        return width;
    }

    private static String buildStackRows(String[] values, String[] types, int n, int typeColPos, int width, String header) {
        StringBuilder sb = new StringBuilder();

        sb.append(header);
        sb.append("─".repeat(width - header.length() - 1));
        sb.append(System.lineSeparator());

        for (int i = n - 1; i >= 0; i--) {
            String marker = (i == n - 1) ? "➔" : "·";
            String topStr = (i == n - 1) ? "  ◄ TOP" : "";

            sb.append(" │  ");
            sb.append(marker).append("\t");
            sb.append(values[i]);

            int padToType = typeColPos - values[i].length();
            if (padToType < 1) padToType = 1;
            sb.append(" ".repeat(padToType));
            sb.append("[").append(types[i]).append("]");

            if (!topStr.isEmpty()) {
                sb.append(topStr);
            }

            sb.append(System.lineSeparator());
        }

        sb.append(" ");
        sb.append("─".repeat(width - 2));
        sb.append(" ");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    /// Clear stack.
    public void clear() {
        pool.clear();
    }

    /// Is stack empty?
    ///
    /// @return True if is empty
    @Override
    public boolean isEmpty() {
        return pool.isEmpty();
    }

    private int lastIndex() {
        if (pool.isEmpty())
            return 0;
        return pool.size() - 1;
    }

    /// Extract the last element.
    ///
    /// @return The element removed
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();

        return pool.remove(lastIndex());
    }

    /// Add an element to stack.
    ///
    /// @param element Element to add
    public void push(T element) {
        pool.add(element);
    }

    /// Convert to string.
    ///
    /// @return The stack as a string
    @Override
    public String toString() {
        return printWithUnicode ? formatStackList(pool) :
                formatStackBox(pool.stream().map(Objects::toString).toList());
    }

    /// View element at top.
    ///
    /// @return Element at top of the stack
    public T topElement() {
        if (isEmpty())
            throw new EmptyStackException();
        return pool.get(lastIndex());
    }
}
