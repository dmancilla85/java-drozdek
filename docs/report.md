# Drozdek Project — Master Architecture & Change Report

## Overview

A comprehensive repository for abstract data types (ADTs), data structures, and classical algorithms in Java 25, based on *Data Structures and Algorithms in Java* by Adam Drozdek.

- **Build Tool:** Maven 3.9+ (single-module)
- **Language / Runtime:** Java 25
- **Unit Testing:** JUnit Jupiter 6.0.3 — **854 tests across 88 test files** (100% passing, 0 failures, 0 errors)
- **Code Coverage:** JaCoCo 0.8.14 (90%+ instruction coverage)
- **Static Analysis:** SonarCloud Quality Gate **PASSED** (Rating A in Reliability, Security, Maintainability; 0 open issues)
- **Code Style:** Checkstyle Google Java Style (120-character line limit, 2-space indentation)
- **Documentation:** Modern JEP-467 Markdown doc comments (`///`) across all public APIs and 27 `package-info.java` descriptors

---

## 1. Core ADT Applications Catalog

To illustrate the practical value and concrete problem-solving capabilities of each Abstract Data Type, dedicated `applications` subpackages have been introduced across all six core data structure modules:

| ADT Module | Package | Application Class | Backing ADT | Real-World Problem Solved | Complexity |
|---|---|---|---|---|---|
| **Stacks** | `org.drozdek.stacks.applications` | `BalancedBracketValidator` | `ArrayStack<Character>` | Delimiter validation `()`, `[]`, `{}` in code/math expressions | $O(n)$ time / $O(n)$ space |
| **Queues** | `org.drozdek.queues.applications` | `PrintSpooler` (`PrintJob` record) | `Queue<PrintJob>` | Multi-user FIFO print spooling and job dispatching | $O(1)$ enqueue/dequeue / $O(n)$ space |
| **Lists** | `org.drozdek.lists.applications` | `MusicPlaylist` (`Track` record) | `DoubleLinkedList<Track>` | Bidirectional audio track stepping & looping playback | $O(1)$ step / $O(n)$ space |
| **Trees** | `org.drozdek.trees.applications` | `PrefixAutoComplete` | `Trie` | Fast dictionary prefix search and query completion | $O(L)$ query / $O(N \cdot L)$ space |
| **Graphs** | `org.drozdek.graphs.applications` | `BuildDependencyResolver` | `DirectedAcyclicGraph` | Build task dependency resolution & cycle detection | $O(V + E)$ topological sort / $O(V)$ space |
| **Hashing** | `org.drozdek.hashing.applications` | `UserSessionStore` (`UserSession` record) | `HashTable<String, UserSession>` | In-memory token verification, TTL expiry, & revocation | $O(1)$ expected lookup / $O(n)$ space |

### Detailed Use Case Breakdown

#### Stacks: Balanced Bracket Validator
- **Package:** `org.drozdek.stacks.applications`
- **Class:** `BalancedBracketValidator`
- **Backing ADT:** `org.drozdek.stacks.ArrayStack<T>`
- **Problem Statement:** Syntax validation of nested delimiters (`()`, `[]`, `{}`) in programming languages, configuration files (JSON), and mathematical formulas.
- **Mechanism:** Scans input strings in $O(n)$ time. Opening brackets are pushed onto the LIFO stack; closing brackets must match the element popped from the top. Returns true if balanced and identifies mismatch indices.

#### Queues: Multi-User Print Spooler
- **Package:** `org.drozdek.queues.applications`
- **Class:** `PrintSpooler` (with `PrintJob` record)
- **Backing ADT:** `org.drozdek.queues.Queue<T>` (`QueueInterface<T>`)
- **Problem Statement:** Asynchronous print job management where requests submitted by multiple network workstations must be buffered and printed in strict First-Come, First-Served (FIFO) order.
- **Mechanism:** Jobs are enqueued at the tail of the FIFO queue without blocking client callers. The printing engine processes and dequeues jobs from the head sequentially, recording cumulative page counts and job completion statistics.

#### Lists: Continuous Music Playlist Manager
- **Package:** `org.drozdek.lists.applications`
- **Class:** `MusicPlaylist` (with `Track` record)
- **Backing ADT:** `org.drozdek.lists.DoubleLinkedList<T>`
- **Problem Statement:** Sequential and bidirectional audio track navigation in a media player with track addition, deletion, and optional infinite loop playback.
- **Mechanism:** Tracks are stored as nodes in a doubly-linked list. Pointers allow instantaneous $O(1)$ stepping forward (`nextTrack()`) and backward (`previousTrack()`), wrapping around boundaries when looping is active.

#### Trees: Prefix Search Auto-Complete Engine
- **Package:** `org.drozdek.trees.applications`
- **Class:** `PrefixAutoComplete`
- **Backing ADT:** `org.drozdek.trees.Trie`
- **Problem Statement:** Interactive query suggestion in search boxes and IDEs that retrieves vocabulary terms matching a partial input prefix.
- **Mechanism:** Terms inserted into the Trie share prefix nodes. Lookups traverse character paths in $O(L)$ time where $L$ is word length, irrespective of total vocabulary size.

#### Graphs: Build System Dependency Resolver
- **Package:** `org.drozdek.graphs.applications`
- **Class:** `BuildDependencyResolver`
- **Backing ADT:** `org.drozdek.graphs.DirectedAcyclicGraph`
- **Problem Statement:** Determining valid compilation or execution schedules for software modules with prerequisite dependencies (e.g., Maven, Gradle), while preventing cyclic deadlock dependencies.
- **Mechanism:** Modules are mapped to vertices in a DAG. Adding a dependency adds a directed arc from prerequisite to dependent; any arc that introduces a path back to the origin is rejected as a circular dependency. Topological sorting generates the linear build order.

#### Hashing: In-Memory User Session Store
- **Package:** `org.drozdek.hashing.applications`
- **Class:** `UserSessionStore` (with `UserSession` record)
- **Backing ADT:** `org.drozdek.hashing.HashTable<K, V>`
- **Problem Statement:** High-throughput HTTP bearer token validation, TTL expiration enforcement, and session revocation in web APIs and microservices.
- **Mechanism:** Cryptographic session tokens map to `UserSession` state within a hash table with separate chaining. Authentication checks and revocations execute in expected $O(1)$ constant time.

---

## 2. Documentation Modernization (JEP-467)

All public APIs have been upgraded to modern Markdown doc comments (`///`, JEP-467).

### Canonical Documentation Template (Pattern M)
```java
/// Brief single-sentence summary ending with a period.
///
/// Detailed description of the algorithm or abstract data type structure.
///
/// **Real-world use case:** Concrete industrial or academic application scenario.
///
/// Complexity Analysis:
/// Time Complexity: O(...) description
/// Auxiliary Space: O(...) description
///
/// @see <a href="...">External Reference</a>
```

### Scope of Modernization
1. **Class-level Headers:** Every class across all 27 packages is documented following Pattern M.
2. **Method-level Javadocs:** All API-relevant public methods, constructors, and factory methods have full parameter, return, and exception tags.
3. **Package Descriptors:** Every package contains a dedicated `package-info.java`.
4. **Delimiters:** Converted all legacy `/** ... */` HTML comments in `sorting/` and `searching/` to `///`.

---

## 3. Historical Bug Fixes & Refactoring Highlights

### Core Data Structure Fixes
- **`BinarySearchTree.postorder()`:** Fixed recursion traversal to left → right → root (previously invoked preorder).
- **`CountingSort`:** Added decrement `count[array[i]]--` to correctly handle duplicate values.
- **`DoubleLinkedList.delete()`:** Repaired predecessor/successor pointer integrity on arbitrary node deletion.
- **`CircularLinkedList.delete()`:** Fixed single-element deletions, tail pointer circularity restore, and missing-element guards.
- **`DoubleCircularLinkedList.delete()`:** Fixed single-element head removal logic.
- **`IntSkipList`:** Fixed level generation overflow at `Integer.MIN_VALUE` and off-by-one bitshift in `choosePowers()`.
- **`DynamicKnapsackItem` & `ScheduledTask`:** Corrected `compareTo` integer overflow bugs using `Integer.compare()`.
- **`AvlTreeNode`:** Initialized height default to 1.
- **`ExpressionTree`:** Added multi-digit number parsing accumulation.
- **`TernaryMergeSort`:** Complete rewrite with 3-way split, 3-pointer merge, and 0-indexed bounds.

### Code Quality & Standards
- **BinarySearch:** Midpoint calculation changed to `left + (right - left) / 2` to prevent 32-bit signed integer overflow.
- **LoggerService:** Standardized on `logInfo()`, `logWarning()`, and `logError()` wrapping `java.util.logging.Logger` with ANSI formatting.
- **Dead References:** Replaced broken/dead academic DOIs with valid persistent identifiers and Wikipedia links.

---

## 4. Package Architecture Reference

```
org.drozdek.
├── commons/                     # Shared utilities (LoggerService, ArrayUtils, DataTypeInterface)
├── dynamic/                     # Knapsack and task scheduling algorithms
├── graphs/                      # Graph ADTs (Graph, Digraph, DirectedGraph, WeightedGraph, FlowNetwork)
│   ├── algorithms/              # Graph suites (ShortestPath, Construction, Structural)
│   └── applications/            # BuildDependencyResolver
├── hashing/                     # HashTable (separate chaining)
│   └── applications/            # UserSessionStore, UserSession
├── lists/                       # List ADTs (Single, Double, Circular, DoubleCircular, IntSkipList)
│   ├── interfaces/              # ListInterface
│   ├── iterators/               # Single/DoubleLinkedListIterator
│   ├── nodes/                   # Internal list nodes
│   └── applications/            # MusicPlaylist, Track
├── queues/                      # Queue ADTs (Queue, ArrayQueue, Deque)
│   ├── interfaces/              # QueueInterface, UnlamQueue
│   ├── unlam/                   # Academic queue variants (Static, Dynamic, Heap)
│   └── applications/            # PrintSpooler, PrintJob
├── recursion/                   # Recursive algorithms (TowersOfHanoi, MajorityElement, AlphabeticallySorted)
├── searching/                   # Search algorithms (Binary, Jump, Interpolation, Exponential, Fibonacci, etc.)
├── sorting/                     # Sorting algorithms (Bubble, Quick, Merge, Heap, Counting, Bucket, etc.)
│   └── exercises/               # Closest Pair, Ternary Merge Sort, Point
├── stacks/                      # Stack ADTs (ArrayStack, LinkedStack, AdaptiveStack, Stack)
│   ├── interfaces/              # StackInterface, StackIterable
│   └── applications/            # BalancedBracketValidator
└── trees/                       # Tree ADTs (BST, AVL, RedBlack, Splay, Trie, Heap, SuffixTree, etc.)
    ├── interfaces/              # TreeInterface
    ├── nodes/                   # Internal tree nodes
    └── applications/            # PrefixAutoComplete
```

---

## 5. Verification Matrix

| Verification Target | Maven Lifecycle Command | Status | Notes |
|---|---|---|---|
| **Checkstyle Gate** | `mvn validate` | **PASSED** | 0 violations (Google Java Style, 120 chars) |
| **Unit Test Suite** | `mvn test` | **PASSED** | 854 tests executed across 88 test files (0 failures, 0 errors) |
| **Javadoc Compiler** | `mvn javadoc:javadoc` | **PASSED** | 0 errors, generated at `target/reports/apidocs/index.html` |
| **Full Build & JAR** | `mvn clean package` | **PASSED** | Full clean compilation, tests, JaCoCo report, and JAR package |
