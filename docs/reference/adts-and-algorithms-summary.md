# Data Structures and Algorithms in Java (Drozdek, 2nd Ed.) — ADT & Algorithm Reference

A concise, chapter-by-chapter reference of every ADT and algorithm covered in the main text, plus the ADTs/problems raised only in the end-of-chapter exercises.

---

## Chapter 1 — Object-Oriented Programming Using Java

**ADTs**
- **Abstract Data Type (concept)** — introduces the ADT concept itself: a type defined by its behavior (operations) rather than its implementation.
- **Vector (`java.util.Vector`)** — growable array-backed list; covered as a built-in ADT example.

**Algorithms / Techniques**
- Encapsulation, inheritance, and polymorphism as mechanisms for implementing ADTs in Java.
- Random-access file I/O used in a case study to store/retrieve fixed-size records.

**Exercise-only topics**
- OOP mechanics (access modifiers, interfaces, abstract classes, method overriding/overloading) — not ADTs/algorithms per se, but foundational exercises for the rest of the book.

---

## Chapter 2 — Complexity Analysis

**ADTs**
- (None — this chapter is purely analytical.)

**Algorithms / Concepts**
- **Big-O, Ω, and Θ notations** — asymptotic upper, lower, and tight bounds.
- **Best/average/worst-case analysis** — framework for evaluating algorithms.
- **Amortized complexity analysis** — accounting and potential-function methods, illustrated on a dynamic array (stack with doubling).
- **NP-completeness** — introduction to P vs. NP, polynomial reduction.

**Exercise-only topics**
- Practice deriving Big-O for given code fragments; comparing growth rates of common functions; amortized cost of specific data-structure operations.

---

## Chapter 3 — Linked Lists

**ADTs**
- **Singly linked list**
- **Doubly linked list**
- **Circular linked list** (singly and doubly)
- **Skip list**
- **Self-organizing list** (list with dynamic reordering policy)
- **Sparse table** (matrix represented via linked structures)
- **`java.util.LinkedList` / `java.util.ArrayList`** — built-in list ADTs.

**Algorithms**
- Insertion, deletion, and search on singly/doubly linked lists.
- Skip list `find()`/insert/delete using probabilistic "express lanes."
- Self-organizing list heuristics: **move-to-front**, **transpose**, **count**, **ordering** (and their amortized cost analysis).
- Sparse table storage/lookup for mostly-empty two-dimensional data.

**Exercise-only topics**
- Merging two ordered lists; deleting nodes by position across multiple lists; reversing a list in one pass; array-based implementation of a linked list; binary search adapted to linked lists; **move-to-end** and **swapping** self-organizing list variants (Matthews/Rotem/Bretholz; Ng & Oommen).

---

## Chapter 4 — Stacks and Queues

**ADTs**
- **Stack**
- **Queue**
- **Priority queue** (introduced conceptually; implemented later via heaps in Ch. 6)
- **`java.util.Stack`** — built-in stack ADT.

**Algorithms**
- Array-based and linked-list-based stack/queue implementations.
- Expression evaluation using a stack (infix arithmetic, e.g., recursive-descent style expression processing).
- **Maze-exiting algorithm** (case study) — stack-based backtracking search through a grid.
- Java Virtual Machine method-call stack (bibliographic/contextual coverage).

**Exercise-only topics**
- Reversing/sorting stack contents using auxiliary stacks/queues; implementing a stack in terms of a queue and vice versa; modifying the maze algorithm to output the shortest path without dead ends.

---

## Chapter 5 — Recursion

**ADTs**
- (None new — recursion is a technique, applied to the call stack.)

**Algorithms**
- Recursive definitions (factorial, Fibonacci, etc.).
- **Tail recursion** vs. **non-tail recursion**, and their transformation to iteration.
- **Indirect recursion** and **nested recursion** (e.g., Ackermann's function).
- **Excessive recursion** and inefficiency (e.g., naive recursive Fibonacci) with memoization as a remedy.
- **Backtracking** — general technique, illustrated by the **Eight Queens problem** (`putQueen()`).
- **Recursive-descent interpreter** (case study) — parsing and evaluating expressions/statements recursively.

**Exercise-only topics**
- Converting specific recursive procedures to iterative form; tracing recursive call trees; drawing figures via recursive line-drawing programs; variations on the n-queens backtracking algorithm.

---

## Chapter 6 — Binary Trees

**ADTs**
- **Binary tree**
- **Binary search tree (BST)**
- **AVL tree** (self-balancing BST)
- **Threaded binary tree**
- **Self-adjusting tree / splay tree**
- **Heap** (binary heap, used as a priority queue)
- **Expression tree** (Polish/reverse-Polish notation)

**Algorithms**
- BST search, insertion, deletion (**deletion by merging**, **deletion by copying**).
- Tree traversals: breadth-first (level-order) and depth-first — **preorder**, **inorder**, **postorder** — including recursive, stack-based, and **stackless (Morris) traversal** via threading.
- **DSW algorithm** (Day–Stout–Warren) — tree balancing via backbone transformation.
- **AVL tree rebalancing** — single and double rotations after insertion/deletion.
- **Self-restructuring trees** and **splaying** (move-to-root heuristic with amortized analysis).
- **Heap construction and operations** — `heapEnqueue()`, `heapDequeue()`; **Floyd's method** and **Williams's method** for building a heap from an array; **heapsort** foundation.
- Expression tree construction and evaluation; tree-based symbolic differentiation.
- **Word-frequency counting** (case study) using a BST.

**Exercise-only topics**
- Counting nodes/leaves, computing tree height, checking balance, verifying BST property; mirror-image and traversal-equivalence puzzles; printing trees sideways; threaded-tree insertion/deletion; **Hibbard's deletion algorithm**; **Fibonacci trees** (worst-case AVL trees); **one-sided height-balanced trees** (Zweben & McDonald); **lazy deletion**; comparing Floyd's vs. Williams's heap-construction cost; a hybrid Floyd/Williams heap-build method (McDiarmid & Reed).

---

## Chapter 7 — Multiway Trees

**ADTs**
- **B-tree**
- **B\*-tree**
- **B⁺-tree**
- **Prefix B⁺-tree**
- **Bit-tree**
- **R-tree** (spatial index)
- **2–4 tree**
- **Trie** (including compressed tries)
- **`java.util.TreeSet` / `java.util.TreeMap`** — built-in sorted-map/set ADTs (typically red-black trees).

**Algorithms**
- B-tree search, insertion (node splitting), and deletion (`BTreeInsert()`), including order-*m* variants.
- B\*-tree and B⁺-tree splitting/merging strategies; prefix key compression in prefix B⁺-trees.
- Bit-tree search (`bitTreeSearch()`) using discrimination bits.
- R-tree construction for spatial (rectangle) data.
- 2–4 tree insertion via **flag flipping** (`flagFlipping()`) and node splitting; equivalence with red-black trees (`VHTreeInsert()`).
- Trie search/insert and **trie compression** (`compressTrie()`).
- **Spell-checker** (case study) built on a trie.

**Exercise-only topics**
- Maximum nodes/keys for multiway trees of given order/height; printing B-tree contents in order; root-splitting edge cases in B\*-trees; effect of insertion order on B-tree shape; R-tree range-query algorithm; worst-case 2–4 tree insertion; **digital trees** (bit-level tries).

---

## Chapter 8 — Graphs

**ADTs**
- **Graph** (directed and undirected; adjacency-matrix and adjacency-list representations)
- **Union-Find (disjoint-set)** structure, used for cycle detection

**Algorithms**
- **Breadth-first search** and **depth-first search** graph traversal.
- **Dijkstra's algorithm** — single-source shortest paths (non-negative weights).
- **Ford's algorithm (Bellman–Ford style / D'Esopo-Pape variant)** — shortest paths with possible negative edges; generic shortest-path algorithm framework.
- **Floyd–Warshall-style all-to-all shortest path** approach.
- **Cycle detection** via union-find.
- **Minimum spanning tree** algorithms (Prim's and Kruskal's style approaches; generic spanning-tree construction).
- **Connectivity algorithms** for undirected graphs (articulation points/biconnectivity) and directed graphs (strong connectivity).
- **Topological sort**.
- **Maximum flow** — **Ford-Fulkerson algorithm**, with labeling/augmenting-path approach; **minimum-cost maximum flow**.
- **Matching algorithms** — bipartite matching via augmenting paths; **stable matching problem** (Gale–Shapley style); **assignment problem**; matching in nonbipartite graphs.
- **Eulerian graphs** — Eulerian circuit/path existence and construction; **Chinese Postman Problem**.
- **Hamiltonian graphs** — Hamiltonian cycle search; **Traveling Salesman Problem (TSP)**.
- **Graph coloring** — Brélaz's algorithm (`BrelazColoringAlgorithm()`).
- **NP-complete graph problems**: **Clique problem**, **3-Colorability problem**, **Vertex Cover problem**, **Hamiltonian Cycle problem** (reductions via Boolean expressions/SAT).
- **Distinct representatives** (case study) — system of distinct representatives via bipartite matching.

**Exercise-only topics**
- Degree-sum/edge-count relationships; complexity of `breadthFirstSearch()`; adapting Dijkstra's algorithm to undirected graphs or a-to-b queries; modifying Ford's algorithm for negative cycles or all-to-one paths; pathological graph construction for the D'Esopo–Pape algorithm (Kershenbaum).

---

## Chapter 9 — Sorting

**ADTs**
- (None new — operates on arrays/lists.)

**Algorithms**
- **Insertion sort**
- **Selection sort**
- **Bubble sort**
- **Decision trees** for lower-bound analysis of comparison sorts.
- **Shell sort**
- **Heapsort**
- **Quicksort**
- **Mergesort**
- **Radix sort**
- Built-in sorting in `java.util` (e.g., `Arrays.sort`/`Collections.sort`).
- **Adding polynomials** (case study) using a sorted linked-list representation of terms.

**Exercise-only topics**
- Comparing algorithm behavior on nearly-sorted or reverse-sorted input; deriving decision-tree lower bounds for specific input sizes; analyzing gap sequences in Shell sort.

---

## Chapter 10 — Hashing

**ADTs**
- **Hash table** (open addressing, chaining, and bucket addressing variants)
- **`java.util.HashMap` / `HashSet` / `Hashtable`** — built-in hash-based ADTs.

**Algorithms**
- Hash functions: **division**, **folding**, **mid-square**, **extraction**, **radix transformation**.
- Collision resolution: **open addressing** (linear probing, quadratic probing, double hashing), **chaining**, **bucket addressing**.
- **Deletion in open-addressing hash tables** (tombstone/lazy-deletion handling).
- **Perfect hash functions** — **Cichelli's method**; **FHCD algorithm**.
- Hashing for extendible files: **extendible hashing** (`extendibleHashingInsert()`); **linear hashing**.
- **Hashing with buckets** (case study) — a phone-directory application.

**Exercise-only topics**
- Comparing collision-resolution strategies under load factor; behavior of deletion in probing schemes; designing perfect hash functions for small key sets.

---

## Chapter 11 — Data Compression

**ADTs**
- **Huffman tree** (variable-length prefix-code tree)

**Algorithms**
- **Huffman coding** (`Huffman()`, `createHuffmanTree()`) — static, optimal prefix-code construction.
- **Adaptive Huffman coding** — dynamically updated code tree, maintaining the **sibling property**.
- **Run-length encoding (RLE)**.
- **Ziv–Lempel (LZW) coding** — `LZWcompress()` / `LZWdecompress()`.
- **Huffman + run-length encoding combined** (case study).

**Exercise-only topics**
- Computing compression ratios for given symbol distributions; tracing adaptive Huffman tree updates for sample messages; comparing RLE vs. Huffman on specific data.

---

## Chapter 12 — Memory Management

**ADTs**
- **Free list** (implicit, via sequential-fit and buddy-system schemes)

**Algorithms**
- **Sequential-fit methods** — first-fit, best-fit, worst-fit allocation.
- **Nonsequential-fit / buddy systems** — binary buddy system, **Fibonacci buddy system** (`reserveFib()`).
- **Garbage collection**:
  - **Mark-and-sweep** (marking, space reclamation, compaction).
  - **Copying methods** (e.g., semispace/Cheney-style copying collection).
  - **Incremental garbage collection** (copying-based and noncopying/reference-counting-based, e.g., Lisp `cons`).
- **In-place garbage collector** (case study).

**Exercise-only topics**
- Tracing free-list state after sequences of allocations/deallocations; comparing fragmentation under different fit strategies; tracing buddy-system splits/merges.

---

## Chapter 13 — String Matching

**ADTs**
- **Suffix trie / suffix tree**
- **Suffix array**

**Algorithms**
- **Straightforward (brute-force) string matching**.
- **Knuth–Morris–Pratt (KMP) algorithm**.
- **Boyer–Moore algorithm** (and the **Sunday algorithm** variant).
- **Multiple-pattern search** techniques.
- **Bit-oriented (shift-and/shift-or) string matching**.
- **Aho–Corasick algorithm** — matching a set of keywords simultaneously (`AhoCorasick()`).
- **Regular expression matching**.
- **Suffix tree construction** — brute-force (`bruteForceSuffixTree()`) and **Ukkonen's algorithm** (`UkkonenSuffixTree()`); suffix arrays as a space-efficient alternative.
- **Approximate string matching** — **string similarity** (edit distance) and **string matching with *k* errors**.
- **Longest common substring** (case study), typically via suffix structures/dynamic programming.

**Exercise-only topics**
- Tracing KMP failure functions and Boyer-Moore bad-character/good-suffix shifts on sample strings; complexity questions about suffix-tree construction variants.

---

## Appendix A — Computing Big-O
- Harmonic series approximation; Stirling's approximation of lg(n!); average-case Big-O derivation for quicksort; node-count bounds for AVL trees. (Mathematical derivations, not standalone ADTs/algorithms.)

## Appendix B — NP-Completeness
- **Cook's theorem** (SAT is NP-complete) — foundational reduction proof underlying the NP-completeness discussions in Chapters 2 and 8.

---

## Summary Index of All ADTs Covered

| ADT | Chapter(s) |
|---|---|
| Vector / ArrayList | 1, 3 |
| Singly/doubly/circular linked list | 3 |
| Skip list | 3 |
| Self-organizing list | 3 |
| Sparse table | 3 |
| Stack | 4 |
| Queue | 4 |
| Priority queue | 4, 6 |
| Binary tree / BST | 6 |
| AVL tree | 6 |
| Threaded binary tree | 6 |
| Splay (self-adjusting) tree | 6 |
| Heap | 6 |
| Expression tree | 6 |
| B-tree, B\*-tree, B⁺-tree, prefix B⁺-tree | 7 |
| Bit-tree | 7 |
| R-tree | 7 |
| 2–4 tree | 7 |
| Trie (incl. compressed) | 7 |
| TreeSet / TreeMap | 7 |
| Graph (directed/undirected) | 8 |
| Union-Find / disjoint-set | 8 |
| Hash table (open addressing/chaining/bucket) | 10 |
| HashMap / HashSet / Hashtable | 10 |
| Huffman tree | 11 |
| Free list / buddy system | 12 |
| Suffix trie / suffix tree | 13 |
| Suffix array | 13 |

## Summary Index of Named Algorithms

| Algorithm | Chapter |
|---|---|
| Amortized-cost accounting | 2 |
| Insertion / selection / bubble sort | 9 |
| Shell sort, heapsort, quicksort, mergesort, radix sort | 9 |
| Self-organizing list heuristics (move-to-front, transpose, count, ordering) | 3 |
| Backtracking (Eight Queens) | 5 |
| DSW algorithm | 6 |
| AVL rotations | 6 |
| Splaying | 6 |
| Floyd's / Williams's heap construction | 6 |
| Flag flipping (2–4 trees) | 7 |
| Trie compression | 7 |
| BFS / DFS | 8 |
| Dijkstra's algorithm | 8 |
| Ford's (Bellman-Ford/D'Esopo-Pape) algorithm | 8 |
| Prim's / Kruskal's-style spanning tree | 8 |
| Topological sort | 8 |
| Ford-Fulkerson (max flow), min-cost max flow | 8 |
| Stable matching (Gale-Shapley style) | 8 |
| Brélaz graph coloring | 8 |
| Division / folding / mid-square / extraction hashing | 10 |
| Cichelli's method, FHCD algorithm | 10 |
| Extendible hashing, linear hashing | 10 |
| Huffman coding (static & adaptive) | 11 |
| Run-length encoding | 11 |
| LZW (Ziv-Lempel) coding | 11 |
| Sequential-fit allocation, buddy systems | 12 |
| Mark-and-sweep, copying GC, incremental GC | 12 |
| Knuth-Morris-Pratt | 13 |
| Boyer-Moore / Sunday | 13 |
| Aho-Corasick | 13 |
| Ukkonen's suffix tree algorithm | 13 |
| Edit distance / k-error approximate matching | 13 |
