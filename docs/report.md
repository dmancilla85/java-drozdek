# Drozdek Project — Master Architecture & Change Report

## Overview

A comprehensive repository for abstract data types (ADTs), data structures, and classical algorithms in Java 25, based on *Data Structures and Algorithms in Java* by Adam Drozdek.

- **Build Tool:** Maven 3.9+ (single-module)
- **Language / Runtime:** Java 25
- **Unit Testing:** JUnit Jupiter 6.0.3 — **1176 tests across 149 test files** (100% passing, 0 failures, 0 errors)
- **Code Coverage:** JaCoCo 0.8.14 (~92% instruction coverage)
- **Static Analysis:** SonarCloud Quality Gate **PASSED** (Rating A in Reliability, Security, Maintainability; 0 open issues)
- **Code Style:** Checkstyle Google Java Style (120-character line limit, 2-space indentation)
- **Documentation:** Modern JEP-467 Markdown doc comments (`///`) across all public APIs and 43 `package-info.java` descriptors

---

## 1. Core ADT Applications Catalog

To illustrate the practical value and concrete problem-solving capabilities of each Abstract Data Type, dedicated `applications` subpackages have been introduced across the core data structure modules:

| ADT Module | Package | Application Class | Backing ADT | Real-World Problem Solved | Complexity |
|---|---|---|---|---|---|
| **Stacks** | `org.drozdek.stacks.applications` | `BalancedBracketValidator` | `ArrayStack<Character>` | Delimiter validation `()`, `[]`, `{}` in code/math expressions | $O(n)$ time / $O(n)$ space |
| **Queues** | `org.drozdek.queues.applications` | `PrintSpooler` (`PrintJob` record) | `Queue<PrintJob>` | Multi-user FIFO print spooling and job dispatching | $O(1)$ enqueue/dequeue / $O(n)$ space |
| **Lists** | `org.drozdek.lists.applications` | `MusicPlaylist` (`Track` record) | `DoublyLinkedList<Track>` | Bidirectional audio track stepping & looping playback | $O(1)$ step / $O(n)$ space |
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
- **Backing ADT:** `org.drozdek.lists.DoublyLinkedList<T>`
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

### Application Coverage Expansion (this session)

A further **30 application classes** (plus their JUnit suites) were added across the core modules, lifting ADT/algorithm application coverage from ~14.5% to **76.3% (74 of 97** core ADTs**)**. Each application demonstrates a concrete, real-world use of one or more backing ADTs.

| Module | Application Class | Backing ADT(s) | Use Case |
|---|---|---|---|
| **Searching** | `LogTimestampSeeker` | `BinarySearch` | Locate log entries by timestamp |
| | `UniformCatalogLookup` | `InterpolationSearch`/`ExponentialSearch` | Catalogue item lookup |
| | `FirmwareCalibrationTable` | `SentinelLinearSearch` | Firmware calibration scan |
| | `JumpLookupTable` | `JumpSearch` | Fixed-step index lookup |
| | `TriSectionLookup` | `TernarySearch`/`FibonacciSearch` | Tri-section value search |
| **Dynamic** | `CargoLoadOptimizer` | `ZeroOneKnapsack` | 0/1 knapsack cargo loading |
| | `CpuTaskScheduler` | `ScheduledTask` | Interval task scheduling |
| **Stacks** | `RpnCalculator` | `LinkedStack` | Reverse-Polish-Notation calculator |
| | `UndoRedoManager` | `AdaptiveStack` | Undo/redo command history |
| **Queues** | `SlidingWindowRateLimiter` | `ArrayQueue` | Token-bucket rate limiting |
| | `TaskWorkStealingDeque` | `Deque` | Work-stealing task pool |
| | `PriorityTaskDispatcher` | `Heap` (unlam) | Priority job dispatch |
| **Lists** | `LruCache` | `SelfOrganizingList` | LRU-style access cache |
| | `StockPriceRangeLookup` | `SparseTable` | Range-minimum price queries |
| | `RoundRobinScheduler` | `CircularLinkedList` | Round-robin process scheduling |
| **Trees** | `ExpressionEvaluator` | `ExpressionTree` | Parse & evaluate infix arithmetic |
| | `MedianStreamTracker` | `MinimumHeap`/`MaximumHeap` | Running-median of a stream |
| | `DiskBlockIndexer` | `TwoFourTree` | Ordered disk-block index |
| | `PlagiarismDetector` | `SuffixTree` | Substring/plagiarism detection |
| **Sorting** | `DistributedLogMerger` | `MergeSort.sort/merge` | Merge sorted log streams |
| | `TopKRanker` | `QuickSort`/`HeapSort` | Top-K rank extraction |
| | `IpAddressSorter` | `RadixSort` | Sort IPv4 addresses numerically |
| | `ReportSorter` | `Bubble/Cocktail/Insertion/Selection/Shell/Counting/Bucket` | Configurable report sort |
| **Hashing** | `CompilerKeywordTable` | `CichelliHash` | Perfect-hash keyword table |
| | `VirtualDiskHashIndex` | `ExtendibleHashing` | Growable disk-page index |
| | `SessionCache` | `OpenAddressingHashTable` | Flat session/API token cache |
| **Graphs** | `GpsNavigationRouter` | `WeightedDigraph` + `ShortestPathAlgorithms` | Dijkstra shortest travel times |
| | `MaxBandwidthRouter` | `FlowNetwork` + `FordFulkersonAlgorithm` | Max-flow bandwidth provisioning |
| | `HospitalResidentMatcher` | `GaleShapleyAlgorithm` | Stable matching |
| | `ExamTimetableScheduler` | `BrelazColoringAlgorithm` | Graph-colouring exam slots |
| | `CircuitBoardDrilling` | `EulerianCircuit` | Euler-tour drill path planning |
| | `SocialNetworkConnectivity` | `DirectedGraph` + `DisjointSetUtils` | Follow graph + union-find lookup |
| | `WaypointDistanceTable` | `WeightedGraph` + `ShortestPathAlgorithms` | All-pairs waypoint distances |
| **Strings** | `VirusSignatureScanner` | `AhoCorasick` | Multi-pattern threat scanning |
| | `FuzzySpellChecker` | `WagnerFischerEditDistance` | Typo-tolerant spell checking |
| | `GeneSequenceSearch` | `KnuthMorrisPratt`/`BoyerMoore` | Pattern search in genomes |
| | `ApproximatePatternSearcher` | `ShiftAndMatcher` | Bit-parallel short-pattern search |
| **Recursion** | `LexicographicOrderChecker` | `AlphabeticallySorted` | Sorted-sequence validation |
| | `ChessPuzzleSolver` | `EightQueens` | N-queens solution counting |
| | `ElectionTallyCounter` | `MajorityElement` | Majority-vote detection |
| | `SpreadsheetFormulaEngine` | `RecursiveDescentInterpreter` | Recursive-descent formula eval |
| | `HanoiBackupRotation` | `TowersOfHanoi` | Backup tape rotation planning |
| **Memory** | `HeapMemoryManager` | `BuddySystemAllocator` + `SequentialFitAllocator` | Buddy + sequential-fit heaps |
| **Compression** | `LzwFileCompressor` | `LzwCompression` | LZW lossless text compression |

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
1. **Class-level Headers:** Every class across all 43 packages is documented following Pattern M.
2. **Method-level Javadocs:** All API-relevant public methods, constructors, and factory methods have full parameter, return, and exception tags.
3. **Package Descriptors:** Every package contains a dedicated `package-info.java`.
4. **Delimiters:** Converted all legacy `/** ... */` HTML comments in `sorting/` and `searching/` to `///`.

---

## 3. Historical Bug Fixes & Refactoring Highlights

### Core Data Structure Fixes
- **`BinarySearchTree.postorder()`:** Fixed recursion traversal to left → right → root (previously invoked preorder).
- **`CountingSort`:** Added decrement `count[array[i]]--` to correctly handle duplicate values.
- **`DoublyLinkedList.delete()`:** Repaired predecessor/successor pointer integrity on arbitrary node deletion.
- **`CircularLinkedList.delete()`:** Fixed single-element deletions, tail pointer circularity restore, and missing-element guards.
- **`DoublyCircularLinkedList.delete()`:** Fixed single-element head removal logic.
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

## 3b. Phased Expansion (Drozdek Chapters 3–13)

The repository was expanded in three phases to cover the full ADT and algorithm
scope of *Data Structures and Algorithms in Java*, uniformizing the package
layout as `root` (core ADTs), `.applications` (real-world systems) and
`.exercises` (solved textbook problems), all documented with JEP-467 `///`.

**Phase 1 — New packages** `compression`, `memory`, `strings`:
- `compression`: Huffman, Run-Length Encoding, LZW encoders/decoders + apps/exercises.
- `memory`: `SequentialFitAllocator`, `BuddySystemAllocator`, `MarkAndSweepCollector` + apps/exercises.
- `strings`: KMP, Boyer-Moore, Shift-And, Aho-Corasick pattern matchers, `WagnerFischerEditDistance` + apps/exercises.

**Phase 2 — Missing ADTs in existing packages** (each with tests):
- `lists`: `SparseTable`, `SelfOrganizingList` (move-to-front).
- `recursion`: `EightQueens`, `RecursiveDescentInterpreter`.
- `trees`: `BTree`, `TwoFourTree`, `DswAlgorithm` (dag-to-straight-line).
- `graphs`: `BrelazColoringAlgorithm` (DSATUR), `EulerianCircuit` (Hierholzer),
  `FordFulkersonAlgorithm` (Edmonds-Karp), `GaleShapleyAlgorithm`.
- `sorting`: `RadixSort` (LSD), `CocktailShakerSort`.
- `hashing`: `OpenAddressingHashTable` (linear probing + tombstones), `CichelliHash`,
  `ExtendibleHashing`.

**Phase 3 — Applications/exercises subpackages completed** so every module has both:
- `lists/exercises`: `JosephusProblem`.
- `recursion/applications`: `MazeSolver`; `recursion/exercises`: `StringPermutations`.
- `trees/exercises`: `TreeDiameter`.
- `graphs/exercises`: `KnightsTour` (Warnsdorff's heuristic).
- `sorting/applications`: `ScoreSorter`.
- `hashing/exercises`: `DoubleHashingTable`.

Result: **1176 tests across 149 test files, ~92% instruction coverage, checkstyle clean.**

Notable correctness fixes from Phase 2:
- `ExtendibleHashing` directory doubling extends by the low bit (`new[i]` and `new[i + 2^gd]` both keep `old[i]`), preserving bucket alignment.
- `TwoFourTree` 4-node split keeps the left node as `[a]` (removes keys from the end, not the front).
- `BTree.splitChild` removes `MIN_DEGREE` keys from the end of the full child, leaving the median for the parent.
- `MazeSolver` checks the target cell is passable before declaring a path found.

---

## 4. Package Architecture Reference

```
org.drozdek.
├── commons/                     # Shared utilities (LoggerService, ArrayUtils, DataTypeInterface)
├── compression/                 # Data-compression ADTs (Huffman, RLE, LZW)
│   ├── applications/            # Compressor/Decompressor apps
│   └── exercises/               # Solved compression exercises
├── dynamic/                     # Knapsack and task scheduling algorithms
├── graphs/                      # Graph ADTs (Graph, Digraph, DirectedGraph, WeightedGraph, FlowNetwork)
│   ├── algorithms/              # Graph suites (ShortestPath, Construction, Structural)
│   ├── applications/            # BuildDependencyResolver
│   └── exercises/               # Knight's-tour (Warnsdorff) exercise
├── hashing/                     # HashTable (separate chaining), open addressing, Cichelli, Extendible
│   ├── applications/            # UserSessionStore, UserSession
│   └── exercises/               # DoubleHashingTable exercise
├── lists/                       # List ADTs (Singly, Doubly, Circular, DoublyCircular, IntSkipList, SparseTable, SelfOrganizingList)
│   ├── interfaces/              # ListInterface
│   ├── iterators/               # Single/DoublyLinkedListIterator
│   ├── nodes/                   # Internal list nodes
│   ├── applications/            # MusicPlaylist, Track
│   └── exercises/               # Josephus problem
├── memory/                      # Memory-management ADTs (SequentialFit, BuddySystem, MarkAndSweep)
│   ├── applications/            # Allocator apps
│   └── exercises/               # Solved memory exercises
├── queues/                      # Queue ADTs (Queue, ArrayQueue, Deque)
│   ├── interfaces/              # QueueInterface, UnlamQueue
│   ├── unlam/                   # Academic queue variants (Static, Dynamic, Heap)
│   └── applications/            # PrintSpooler, PrintJob
├── recursion/                   # Recursive algorithms (TowersOfHanoi, EightQueens, RecursiveDescentInterpreter)
│   ├── applications/            # MazeSolver
│   └── exercises/               # StringPermutations
├── searching/                   # Search algorithms (Binary, Jump, Interpolation, Exponential, Fibonacci, etc.)
├── sorting/                     # Sorting algorithms (Bubble, Quick, Merge, Heap, Counting, Bucket, Radix, CocktailShaker, etc.)
│   ├── applications/            # ScoreSorter ranking app
│   └── exercises/               # Closest Pair, Ternary Merge Sort, Point
├── stacks/                      # Stack ADTs (ArrayStack, LinkedStack, AdaptiveStack, Stack)
│   ├── interfaces/              # StackInterface, StackIterable
│   └── applications/            # BalancedBracketValidator
├── strings/                     # String ADTs (KMP, Boyer-Moore, Aho-Corasick, edit distance, Shift-And)
│   ├── applications/            # Pattern-matching apps
│   └── exercises/               # Solved string exercises
└── trees/                       # Tree ADTs (BST, AVL, RedBlack, Splay, Trie, Heap, BTree, TwoFour, SuffixTree, etc.)
    ├── interfaces/              # TreeInterface
    ├── nodes/                   # Internal tree nodes
    ├── applications/            # PrefixAutoComplete
    └── exercises/               # TreeDiameter
```

---

## 5. Verification Matrix

| Verification Target | Maven Lifecycle Command | Status | Notes |
|---|---|---|---|
| **Checkstyle Gate** | `mvn validate` | **PASSED** | 0 violations (Google Java Style, 120 chars) |
| **Unit Test Suite** | `mvn test` | **PASSED** | 1176 tests executed across 149 test files (0 failures, 0 errors) |
| **Javadoc Compiler** | `mvn javadoc:javadoc` | **PASSED** | 0 errors, generated at `target/reports/apidocs/index.html` |
| **Full Build & JAR** | `mvn clean package` | **PASSED** | Full clean compilation, tests, JaCoCo report, and JAR package |
