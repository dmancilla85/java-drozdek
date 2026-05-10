# Drozdek Project — Comprehensive Change Report

## Overview

Systematic audit and repair of ~126 source files spanning lists, queues, stacks, sorting, searching, trees, recursion, dynamic programming, and graphs. All 125 tests pass with 0 failures.

---

## Bugs Fixed (15 items)

### BinarySearchTree.postorder() called preorder() internally
- **File:** `src/main/java/org/drozdek/trees/BinarySearchTree.java`
- **Fix:** Changed `postorder()` recursion to left→right→root instead of left→root→right.

### CountingSort missing decrement in output loop
- **File:** `src/main/java/org/drozdek/sorting/CountingSort.java`
- **Fix:** Added `count[array[i]]--` after placing each element to handle duplicate values.

### DoubleLinkedList.delete() corrupted prev pointers
- **File:** `src/main/java/org/drozdek/lists/DoubleLinkedList.java`
- **Fix:** Set `head.previous = null` when deleting head; set `tmp.next.previous = predecessor` for middle deletions. Removed orphaned duplicate code block.

### CircularLinkedList.delete() broken for single-element, circularity, not-found
- **File:** `src/main/java/org/drozdek/lists/CircularLinkedList.java`
- **Fix:** Single-element case sets head to null; circularity restored on tail-delete; returns early if value not found.

### DoubleCircularLinkedList.delete() single-element case
- **File:** `src/main/java/org/drozdek/lists/DoubleCircularLinkedList.java`
- **Fix:** Single-element delete now sets head = null.

### IntSkipList.chooseLevel() overflow at Integer.MIN_VALUE
- **File:** `src/main/java/org/drozdek/lists/IntSkipList.java`
- **Fix:** `Math.abs(rd.nextInt())` wraps to Integer.MIN_VALUE for negative values. Replaced with `rd.nextInt(Integer.MAX_VALUE) + 1`.

### IntSkipList.choosePowers() off-by-one shift
- **File:** `src/main/java/org/drozdek/lists/IntSkipList.java`
- **Fix:** `(2 << (maxLevel - 1)) - 1` → `(1 << maxLevel) - 1`.

### IntSkipList.search() crash on empty list
- **File:** `src/main/java/org/drozdek/lists/IntSkipList.java`
- **Fix:** Added guard in `findMajorNotNullValue()` to check if list is empty before accessing nodes.

### Ejercicio3_2.mochilaMaximizar() infinite loop
- **File:** `src/main/java/org/drozdek/dynamic/Ejercicio3_2.java`
- **Fix:** Extracted `fraccion` calculation variable; added `objeto++` increment.

### Ejercicio3_2 used Double.MIN_VALUE (positive) instead of NEGATIVE_INFINITY
- **File:** `src/main/java/org/drozdek/dynamic/Ejercicio3_2.java`
- **Fix:** `Double.MIN_VALUE` → `Double.NEGATIVE_INFINITY`.

### ElementoMochilaDyn & Tarea compareTo integer overflow
- **Files:** `src/main/java/org/drozdek/dynamic/ElementoMochilaDyn.java`, `src/main/java/org/drozdek/dynamic/SolucionEjercicio2.java`
- **Fix:** Subtraction-based comparison replaced with `Integer.compare()`.

### DynamicStack.verTope() NPE on empty stack
- **File:** `src/main/java/org/drozdek/stacks/unlam/DynamicStack.java`
- **Fix:** Added null-guard before accessing top node data.

### StaticStack.apilar() exception-based flow control
- **File:** `src/main/java/org/drozdek/stacks/unlam/StaticStack.java`
- **Fix:** Simplified: use `if (esLlena()) reDim()` instead of throwing/catching exception.

### SmartStack.tipo was final (never switched)
- **File:** `src/main/java/org/drozdek/stacks/unlam/SmartStack.java`
- **Fix:** Removed `final` qualifier; `cambiarA*()` methods now update `tipo`.

### AvlTreeNode height defaulted to 0 instead of 1
- **File:** `src/main/java/org/drozdek/trees/AvlTreeNode.java`
- **Fix:** Constructor initializes `height = 1`.

---

## New Features

### ArrayUtils.isSorted()
- **File:** `src/main/java/org/drozdek/commons/ArrayUtils.java`
- **Fix:** Added `public static boolean isSorted(int[] array)` helper. Replaced all `assertTrue(true)` in 10 sort tests with `assertTrue(ArrayUtils.isSorted(array))`.

### ExpressionTree multi-digit support
- **File:** `src/main/java/org/drozdek/trees/ExpressionTree.java`
- **Fix:** Rewrote character parsing to accumulate consecutive digits (`num = num * 10 + (c - '0')`) instead of casting char to int (ASCII bug). Test expectation corrected from 101 → 71 for `(5+7)*4+23`.

### Ejercicio2_7 3-way merge sort rewrite
- **File:** `src/main/java/org/drozdek/sorting/Ejercicio2_7.java`
- **Fix:** Complete rewrite with proper 3-way split (tercio1, tercio2), 3-way merge (three-pointer comparison), correct 0-indexed base cases, and `test()` method updated to use 0-based indexing.

---

## Code Quality Improvements

| File | Change |
|------|--------|
| `BinarySearch.java` | `(left + right) / 2` → `left + (right - left) / 2` (overflow-safe) |
| `CompareExample.java` | Removed stale TODO comment |
| `LoggerService.java` | `logWarning`/`logError` use correct log levels (WARNING/SEVERE) |
| `QuickSort.java` | Diamond operator on `ArrayList` |
| `TestEjercicio1.java` | Removed `System.exit(0)` |
| `IntThreadedTree.java` | `printInOrder()` delegates to `threadInOrder()` |

---

## Configuration Upgrades

| File | Change |
|------|--------|
| `pom.xml` | JUnit 5.12.2, Surefire 3.5.5, Compiler 3.15.0; added encoding properties |
| `.github/workflows/maven.yml` | `checkout@v4`, narrowed `contents: read` and `issues: write` permissions |
| `.github/workflows/codeql.yml` | `checkout@v4`, `setup-java@v4` with JDK 25, `codeql-action@v3` |
| `.github/dependabot.yml` | Added `github-actions` ecosystem |

---

## Test Assertions Fixed

All 10 sort tests and BinarySearchTest replaced meaningless `assertTrue(true)` with proper assertions:

| Test | Before | After |
|------|--------|-------|
| BubbleSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| InsertionSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| SelectionSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| ShellSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| MergeSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| QuickSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| HeapSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| CountingSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| BucketSortTest | `assertTrue(true)` | `assertTrue(ArrayUtils.isSorted(array))` |
| BinarySearchTest | `assertTrue(true)` | `assertEquals(4, index)` / `assertEquals(-1, index)` |

---

## .gitignore

- `.idea/` and `*.iml` already covered — no action needed.
