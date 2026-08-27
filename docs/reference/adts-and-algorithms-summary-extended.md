# Data Structures and Algorithms in Java (Drozdek, 2nd Ed.) — Extended ADT & Algorithm Reference

Detailed, chapter-by-chapter reference covering every ADT and algorithm in the main text (with complexity and operational notes), plus a full listing of every end-of-chapter exercise topic.

---

## Chapter 1 — Object-Oriented Programming Using Java

### ADTs
- **Abstract Data Type (concept).** A type is defined purely by the operations it exposes, decoupled from representation. Sets up the book's recurring approach: specify an ADT's interface, then give one or more concrete implementations with different performance trade-offs.
- **Vector (`java.util.Vector`).** Growable array-backed list. Random access is `O(1)`; append is `O(1)` amortized (occasional `O(n)` resize/copy when capacity is exceeded).

### Algorithms / Techniques
- **Encapsulation, inheritance, polymorphism** as the Java mechanisms used throughout the book to implement ADTs (interfaces for the ADT contract, classes for concrete representations).
- **Random-access file case study.** Demonstrates fixed-length record read/write using `RandomAccessFile`, giving `O(1)` access to the *k*-th record via `seek(k * recordSize)`.

### Exercises (Section 1.8)
1. What type should constructors be declared as.
2. Access-modifier visibility puzzle across five variables and four classes/packages.
3. Method-overriding/hiding resolution puzzle for a class hierarchy with overloaded/overridden methods.
4. Identify errors in an interface extending another interface (illegal method bodies, `private` interface fields, instantiating an interface).
5. Identify errors in an abstract class/derived class/multiple-inheritance/direct-instantiation scenario.
6. Effect of overloading (not overriding) `equals()` with a type-specific signature instead of the `Object` signature.
7–14 (assignment-based, continuing the same theme): further overloading/overriding/interface edge cases, generic class declarations, array covariance issues, wrapper-class boxing behavior. *(Full text of items 7–14 continues in the same vein of OOP correctness puzzles; exact numbering follows the book.)*

---

## Chapter 2 — Complexity Analysis

### ADTs
- None (purely analytical chapter).

### Algorithms / Concepts
- **Big-O notation** — upper bound: `f(n)` is `O(g(n))` iff ∃ c, N > 0 such that `f(n) ≤ c·g(n)` for all `n ≥ N`.
- **Ω (Omega) notation** — lower bound: `f(n)` is `Ω(g(n))` iff `g(n)` is `O(f(n))`.
- **Θ (Theta) notation** — tight bound: `f(n)` is `Θ(g(n))` iff `f` is both `O(g(n))` and `Ω(g(n))`.
- **Complexity classes** illustrated with growth-rate tables: `O(1)` constant, `O(lg n)` logarithmic, `O(n)` linear, `O(n lg n)`, `O(n²)` quadratic, `O(n³)` cubic, `O(2ⁿ)` exponential — with concrete timing tables showing how each scales for n = 10³–10⁶.
- **Best/average/worst-case analysis** framework, illustrated on sequential search and other simple algorithms.
- **Amortized complexity analysis** — the accounting/potential method, worked through a growable-array `push()`: best case `O(1)` (room available), worst case `O(size)` (must resize + copy), but amortized cost stays `O(1)` per operation over a sequence, because resizes double capacity and thus become exponentially rare.
- **NP-completeness** — P vs. NP, polynomial-time reducibility, nondeterministic polynomial algorithms defined via decision trees, and Udi Manber's "double-O" (`OO`) notation for Big-O bounds with practically-too-large constants.

### Exercises (Section 2.11)
1. Interpret meanings of `O(1)`, `Ω(1)`, `n^O(1)`.
2. Prove algebraic properties of `O(·)` given `f₁ = O(g₁)`, `f₂ = O(g₂)` (sum, product, scalar multiplication, constants).
3. Prove specific asymptotic facts: sums of powers are `O(n^{k+1})`; `an^k/lg n` is `O(n^k)` but not `Θ(n^k)`; `n^1.1 + n lg n` is `Θ(n^1.1)`; `2ⁿ` is `O(n!)` but not vice versa; exponent-shift identities for `2ⁿ`.
4. Refute two plausible-looking but false `O(·)` arithmetic rules (subtraction, division) via counterexample.
5. Find `f₁, f₂` both `O(g)` where `f₁` is not `O(f₂)`.
6. True/false questions about Θ and exponentials.
7. Optimize the "longest increasing subarray" algorithm to short-circuit; determine new worst case.
8. Find the complexity of a selection-based "k-th smallest element" routine.
9. Determine complexity of matrix addition, multiplication, and transposition code.
10. Find computational complexity of four nested-loop variants (linear/quadratic/logarithmic combinations).
11. Average-case complexity of sequential search under a skewed access-probability distribution.
12. Amortized analysis of incrementing a binary counter — show `m` increments cost `O(m)` total, not `O(mn)`.
13. Convert a general SAT instance into 3-SAT form.

---

## Chapter 3 — Linked Lists

### ADTs
- **Singly linked list (SLL).** Search `O(n)`; insertion/deletion at a known node `O(1)`; insertion/deletion by position/value `O(n)` (must traverse to find the node).
- **Doubly linked list (DLL).** Same asymptotic costs as SLL, but backward traversal and deletion-given-a-node-reference (without needing the predecessor) are `O(1)`.
- **Circular linked list** (singly and doubly). Same complexities as their linear counterparts; enables continuous round-robin traversal without a null terminator.
- **Skip list.** Probabilistic multi-level linked structure. Expected search/insert/delete `O(lg n)`; worst case `O(n)` (all coin flips unlucky).
- **Self-organizing list.** A plain list (array or linked) reordered by an access heuristic to bring frequently used elements toward the front, improving amortized search cost for skewed access patterns.
- **Sparse table.** Two-array (or list-based) representation of a mostly-empty matrix; avoids the `O(rows×cols)` space of a full 2D array in favor of space proportional to the number of non-empty entries.
- **`java.util.LinkedList` / `java.util.ArrayList`** — built-in DLL and growable-array list ADTs; operation costs mirror their generic counterparts (`LinkedList` get by index `O(n)`, `ArrayList` get by index `O(1)`).

### Algorithms
- **Insertion / deletion / search** on SLL and DLL — pointer rewiring for insert-before/insert-after/delete-node cases (`O(1)` once the position is located, `O(n)` to locate it).
- **Skip list `find()`** — starts at the top "express lane," moving right while the next key is ≤ target, dropping down a level on overshoot; repeated until level 0.
- **Self-organizing heuristics:**
  - **Move-to-front** — relocate the accessed element to the front of the list. Best amortized behavior for skewed/locality-heavy access patterns.
  - **Transpose** — swap the accessed element with its immediate predecessor. Converges more slowly than move-to-front but is stabler under near-uniform access.
  - **Count** — maintain an access-frequency counter per element; keep the list sorted by descending frequency.
  - **Ordering** — a static, precomputed order based on known/estimated frequencies (no runtime reordering).
  - Amortized cost analysis via "number of inversions" before/after an access.
- **Sparse-table storage/lookup** for two-array student-grades case study — cross-referencing two parallel sparse arrays instead of one dense matrix.

### Exercises (Section 3.10)
1. Trace pointer changes in a circular doubly linked list after a sequence of dependent pointer-reassignment statements.
2. Determine min/max possible node counts for "the shortest"/"longest" linked list.
3. Rewrite a 3-assignment list construction as a single assignment.
4. Merge two ordered singly linked lists into one ordered list.
5. Delete the *i*-th node of a linked list (with existence check).
6. Delete nodes from list L1 at positions given by list L2.
7. Delete nodes from L1 at positions given by the intersection of L2 and L3.
8. Delete nodes from ordered list L at the positions given by L itself (self-referential deletion).
9. Suggest an array-based implementation of linked lists.
10. Check whether two singly linked lists have identical contents.
11. Reverse a singly linked list in a single pass.
12. Insert a node before/after a given node reference without using a loop.
13. Attach (concatenate) one singly linked list to the end of another.
14. Maintain a singly linked list in ascending order as elements are inserted; use it to find the median.
15. Implement SLL insertion so no null check on `head` is required.
16. Insert a node exactly at the middle of a doubly linked list.
17. Implement `IntCircularSLList` (circular singly linked list) with a standard method set.
18. Implement `IntCircularDLList` (circular doubly linked list) with a standard method set.
19. Discuss how likely the worst case is for skip-list search.
20. Analyze when move-to-front/transpose/count/ordering leave a list unchanged, and when they force exhaustive search.
21. Discuss how list implementation (array, SLL, DLL) affects the efficiency of the four self-organizing heuristics.
22. Analyze **move-to-end** and **swapping** self-organizing variants (Matthews/Rotem/Bretholz 1980; Ng & Oommen 1989) for worst-case behavior under alternating left/right search.
23. Maximum comparisons for optimal search over 14 given letters (per Figure 3.20).
24. Adapt binary search to linked lists; discuss achievable efficiency.
25. Justify (or refute) collapsing two pairs of parallel 2D sparse arrays into two arrays of composite objects.

---

## Chapter 4 — Stacks and Queues

### ADTs
- **Stack (LIFO).** `push`, `pop`, `peek` all `O(1)` for both array- and linked-list-backed implementations (array version has occasional `O(n)` resize like `Vector`).
- **Queue (FIFO).** `enqueue`, `dequeue`, `front` all `O(1)` amortized (circular-array or linked implementation).
- **Priority queue** — introduced conceptually here as a queue ordered by priority rather than arrival order; concrete `O(lg n)` heap-based implementation deferred to Chapter 6.
- **`java.util.Stack`** — Java's built-in `Vector`-based stack; same `O(1)` amortized operation costs.

### Algorithms
- **Array-based vs. linked-list-based stack/queue implementations** — trade-offs: fixed capacity + amortized doubling vs. dynamic memory with per-node overhead.
- **Expression evaluation with a stack** — converting/evaluating infix arithmetic expressions using operator/operand stacks (`O(n)` in expression length).
- **Maze-exiting algorithm (case study).** Stack-based depth-first backtracking search over a grid: push the current cell and explore an unvisited neighbor; on dead end, pop back and try alternatives. `O(rows × cols)` in the worst case (visits every cell once).
- Brief coverage of the **JVM call stack** as a real-world stack application underlying method invocation (context for Chapter 5's recursion).

### Exercises (Section 4.5)
1. Reverse the order of stack `S`'s elements: (a) using two extra stacks, (b) using one extra queue, (c) using one extra stack plus scalar variables.
2. Sort stack `S` in ascending order using one extra stack and scalar variables.
3. Transfer elements from `S1` to `S2` preserving order: (a) with one extra stack, (b) with no extra stack.
4. Critique a `LLStack2` implementation that extends `LinkedList` directly (encapsulation/interface-leak problem).
5. Order all elements of a queue using (a) two extra queues, (b) one extra queue, plus scalar variables.
6. Design a common abstract base class for `Stack` and `LLStack`.
7. Implement a stack (`StackQ`) purely in terms of a queue.
8. Implement a queue purely in terms of a stack.
9. Critique a generic queue implemented directly atop `java.util.ArrayList`.
10. Modify the maze case study to output the shortest path (excluding dead ends but permitting detours).
11. Extend the previous modification to visually render the path using dashes/bars for direction changes.

---

## Chapter 5 — Recursion

### ADTs
- None new — recursion is a control-flow technique built on the implicit call stack (from Ch. 4).

### Algorithms
- **Recursive definitions** — factorial, Fibonacci, and other classic examples; naive recursive Fibonacci is `O(2ⁿ)` due to repeated recomputation ("excessive recursion").
- **Method-call implementation of recursion** — how each call pushes an activation record (parameters, locals, return address) onto the runtime stack; unwinds on return.
- **Tail recursion** — the recursive call is the last action; can be mechanically transformed into an iterative loop with no stack growth.
- **Nontail recursion** — work remains after the recursive call returns (e.g., must use the result); requires an explicit or implicit stack to hold pending work.
- **Indirect recursion** — mutual recursion between two or more methods (A calls B, B calls A).
- **Nested recursion** — a recursive call whose argument is itself computed recursively (e.g., Ackermann's function); notoriously hard to bound and prone to explosive growth.
- **Excessive recursion / memoization remedy** — recognizing redundant recomputation (naive Fibonacci) and caching subresults to reduce complexity from exponential to linear.
- **Backtracking — Eight Queens problem (`putQueen()`).** Places queens row by row, recursively trying each column, backtracking on conflict. Worst-case exponential in board size, though pruning makes it practical for small *n*.
- **Recursive-descent interpreter (case study).** Grammar-driven recursive parsing/evaluation of arithmetic expressions and simple statements — each grammar rule maps to a recursive method (`O(n)` in expression length for well-formed input).

### Exercises (Section 5.12)
1. Restrict the natural-number definition to disallow leading zeros.
2. Write a recursive method to compute a linked list's length.
3. Predict output of a modified character-reversal recursive method.
4. Recursively print odd numbers (a) ascending, (b) descending.
5. Recursively format an integer with comma separators (e.g., 1,234,567).
6. Recursively print a **Syracuse (Collatz) sequence**.
7. Multiply two numbers recursively using only addition, subtraction, and comparison.
8. Recursively compute the binomial coefficient via Pascal's-triangle recurrence.
9. Recursively sum the first *n* terms of the alternating harmonic series.
10. Implement `GCD(n, m)` recursively per the Euclidean-style recurrence given.
11. Convert an iterative "print cubes" loop into a recursive method.
12. Implement **Napier's logarithm method** (geometric/arithmetic mean recursion) in three increasingly general versions.
13. Optimize the naive `O(n)`-multiplication power function to `O(lg n)` via repeated squaring (handle odd exponents).
14. Hand-trace `tail()` and `nonTail()` for parameters 0, 2, 4.
15. Recursively check palindromes: (a) a single word, (b) a full sentence ignoring case/punctuation.
16. For a character, recursively: (a) test membership in a string, (b) count occurrences, (c) remove all occurrences.
17. Repeat exercise 16's three operations for substrings instead of single characters.
18. Modify the line-drawing recursive program (Fig. 5.6) to draw a different curve (Fig. 5.21).
19. Build the call tree for a recursive `sin(x)` Taylor-series-style computation.
20. Write recursive and nonrecursive binary-printing methods without bitwise operators.
21. Reformulate the iterative Fibonacci accumulator technique as an equivalent two-method recursive version.
22. Adapt `putQueen()` to an 8×8 board and suppress symmetric solutions; implement `printBoard()`.
23. Finish the hand-trace of `putQueen()` execution from Figure 5.18.
24. Hand-execute the interpreter case study on two sample expressions, tracing method calls.
25. Extend the interpreter to support right-associative exponentiation (`^`) with correct precedence.
26. Modify the interpreter's division operator to match Java's integer-division truncation.
27. Make the interpreter recover from errors instead of terminating.
28. Write the shortest possible program that uses recursion.

---

## Chapter 6 — Binary Trees

### ADTs
- **Binary tree.** No ordering guarantee; traversal `O(n)`.
- **Binary search tree (BST).** Search/insert/delete average `O(lg n)`, worst case `O(n)` (degenerate/linear tree from sorted insertion order).
- **AVL tree.** Self-balancing BST maintaining a balance factor of {-1, 0, +1} at every node via rotations. Guarantees search/insert/delete `O(lg n)` worst case.
- **Threaded binary tree.** Null child pointers repurposed as "threads" to the in-order predecessor/successor, enabling `O(1)`-space traversal (no stack/recursion needed) at `O(n)` total traversal cost.
- **Self-adjusting tree / splay tree.** No strict balance invariant; every access moves the accessed node to the root via rotations ("splaying"). Amortized `O(lg n)` per operation.
- **Heap (binary heap).** Complete binary tree (array-backed) satisfying the heap-order property. `O(lg n)` enqueue/dequeue, `O(1)` peek-min/max, `O(n)` build-from-array (Floyd's method).
- **Expression tree.** Binary tree representing an arithmetic expression (operators as internal nodes, operands as leaves); enables prefix/postfix generation and evaluation in `O(n)`.

### Algorithms
- **BST search / insertion** — `O(h)` where h is tree height (`O(lg n)` average, `O(n)` worst).
- **BST deletion:**
  - **Deletion by merging** — splice the right subtree into the leftmost position of the left subtree.
  - **Deletion by copying (Knuth's algorithm)** — replace the deleted key with its in-order predecessor/successor and delete that node instead; generally produces better-balanced results than Hibbard's original algorithm.
- **Tree traversal:**
  - **Breadth-first (level-order)** — queue-based, `O(n)` time, `O(n)` space.
  - **Depth-first: preorder, inorder, postorder** — recursive or explicit-stack-based, `O(n)` time, `O(h)` space.
  - **Stackless (Morris) traversal** via **threading** — `O(n)` time, `O(1)` extra space, by temporarily wiring/unwiring threads during the walk.
- **DSW algorithm (Day–Stout–Warren)** — rebalances any BST in `O(n)` time and `O(1)` extra space: first flattens the tree into a sorted "backbone" (linked list via right rotations), then performs a series of rotations to reshape the backbone into a complete/perfectly balanced tree.
- **AVL rotations** — single (LL/RR) and double (LR/RL) rotations restore balance after insertion/deletion in `O(1)` per rotation, `O(lg n)` total per operation including the rebalancing walk back to the root.
- **Splaying** — zig, zig-zig, and zig-zag rotation patterns move the accessed node to the root; amortized `O(lg n)` via potential-function analysis.
- **Heap operations:**
  - `heapEnqueue()` — insert at the next free array slot, then "bubble up" — `O(lg n)`.
  - `heapDequeue()` — remove the root, move the last element to the root, then "bubble/sift down" — `O(lg n)`.
  - **Floyd's method** — build a heap from an unordered array bottom-up by sifting down internal nodes — `O(n)` total (tighter than the naive `O(n lg n)`).
  - **Williams's method** — build a heap by repeated `heapEnqueue()` insertion — `O(n lg n)`.
- **Expression-tree construction/evaluation and symbolic differentiation** — recursive tree transformations implementing calculus product/quotient rules, each `O(n)` in expression-tree size.
- **Word-frequency counting (case study)** — BST keyed by word, incrementing a count field on repeated insertion; `O(n lg n)` for *n* words assuming balanced-ish insertion order.

### Exercises (Section 6.12)
1. Adapt the four traversal algorithms into general search procedures for any binary tree (not just BSTs).
2. Write functions: (a) count nodes, (b) count leaves, (c) count right children, (d) find tree height, (e) delete all leaves.
3. Write a method to check whether a binary tree is perfectly balanced.
4. Design an algorithm to verify the BST property.
5. Trace preorder/inorder/postorder on a given tree under four different custom `visit()` definitions.
6. Determine for which trees preorder and inorder traversals yield the same sequence.
7. Determine whether postorder/preorder traversals (like inorder) can coincide for different trees; give an example if so.
8. Draw all distinct BSTs storable for three elements A, B, C.
9. Determine min/max leaf counts for a balanced tree of height *h*.
10. Write a method to produce a binary tree's mirror image.
11. Analyze which of nine equalities hold between "reverse a traversal" and "traverse the mirror image" operations, across preorder/inorder/postorder.
12. Investigate what happens when only leaves are visited under each traversal order.
13. (a) Print a BST sideways with indentation; (b) adapt for threaded trees, printing successor keys where relevant.
14. Outline insert/delete for a threaded tree with leaf-only threads.
15. Determine whether postorder-only threads suffice for threaded preorder/inorder/postorder traversal.
16. Apply `balance()` to the English alphabet to build a balanced tree.
17. Transform the Nicod-Łukasiewicz propositional-logic axiom into an infix expression and build its binary tree.
18. Write an algorithm to print a parenthesized infix expression from an expression tree without redundant parentheses.
19. Compare Hibbard's deletion algorithm to Knuth's `deleteByCopying()`.
20. Define a BST purely in terms of its inorder traversal.
21. Draw **Fibonacci trees** (worst-case AVL trees) for h = 1,2,3,4 and justify the name.
22. Discuss the rationale for **one-sided height-balanced trees** (Zweben & McDonald 1978).
23. Discuss advantages/disadvantages of **lazy deletion** (mark-as-deleted).
24. Determine best-case comparisons/swaps for heap construction via (a) Williams's method, (b) Floyd's method.
25. Trace a hybrid Floyd/Williams heap-construction method (McDiarmid & Reed 1989) on array `[2 8 6 1 10 15 3 12 11]`; determine its worst case.

---

## Chapter 7 — Multiway Trees

### ADTs
- **B-tree (order *m*).** Every node has up to *m*-1 keys / *m* children; all leaves at the same depth. Search/insert/delete `O(lg n)` (base *m*, so very shallow for large *m* — ideal for disk-backed storage).
- **B\*-tree.** Variant of B-tree requiring nodes stay at least ⅔ full (vs. ½ for standard B-trees), reducing space overhead and split frequency via delayed splitting (sibling redistribution first).
- **B⁺-tree.** All data resides in leaves; internal nodes hold only routing keys; leaves are linked for fast sequential range scans. Search `O(lg n)`, range scan `O(lg n + k)` for *k* results.
- **Prefix B⁺-tree.** B⁺-tree variant storing only the shortest distinguishing prefix as a separator key in internal nodes, reducing internal-node space.
- **Bit-tree.** Leaf-level structure using discrimination bits (D-bits) rather than full keys to distinguish neighboring entries, minimizing leaf storage.
- **R-tree.** Spatial index of bounding rectangles; internal nodes bound the union of children's rectangles. Search (range/overlap query) `O(lg n)` average, degrades if rectangles overlap heavily.
- **2–4 tree.** A B-tree of order 4 (2 to 4 children per node); equivalent in balance behavior to a red-black tree. Search/insert/delete `O(lg n)`.
- **Trie.** Tree keyed by successive symbols of a string (not by comparison). Search/insert `O(k)` where *k* is key length, independent of the number of stored keys *n*. Space can be large without compression.
- **`java.util.TreeSet` / `java.util.TreeMap`.** Sorted set/map backed by a balanced BST (red-black tree); `O(lg n)` for add/remove/contains/get.

### Algorithms
- **B-tree search/insertion (`BTreeInsert()`)** — descend to the appropriate leaf; on overflow, split the full node and promote its median key upward, possibly cascading splits to the root (which increases tree height by 1).
- **B-tree deletion** — remove key from a leaf; if underflow occurs, borrow a key from a sibling via the parent, or merge with a sibling, possibly cascading upward (may decrease height).
- **B\*-tree splitting** — delays splitting by first attempting to redistribute keys with a sibling (two nodes split into three when both are full), keeping utilization higher.
- **B⁺-tree splitting/merging** and **prefix key compression** in the internal index layer.
- **Bit-tree search (`bitTreeSearch()`)** — uses discrimination bits to determine which stored key to compare against, minimizing comparisons in dense leaves.
- **R-tree construction** — inserts new rectangles into the child whose bounding box requires the least enlargement; splits on overflow using an area-minimizing heuristic.
- **2–4 tree insertion via flag flipping (`flagFlipping()`)** — preemptively splits full nodes on the way down during insertion (top-down splitting) so the resulting split never has to propagate back up; equivalence with red-black tree color-flipping is demonstrated via `VHTreeInsert()`.
- **Trie search/insert** and **trie compression (`compressTrie()`)** — collapses single-child chains into combined edges/cells to save space, at some added complexity for search.
- **Spell-checker (case study)** — trie-based dictionary lookup, `O(k)` per word check regardless of dictionary size.

### Exercises (Section 7.5)
1. Maximum node count in a multiway tree of height *h*.
2. Number of keys a B-tree of order *m* and height *h* can hold.
3. Print a B-tree's contents in ascending order.
4. Suggest solutions for the B\*-tree root's "no sibling" special case during a split.
5. Construct order-3 B-trees for two different insertion orders of {1,2,3,4,5}; discuss whether B-trees are order-sensitive.
6. Draw all 10 distinct order-3 B-trees storing 15 keys; tabulate node count vs. average nodes visited (Rosenberg & Snyder 1981); analyze correlation.
7. Discuss handling duplicate keys referencing different underlying records in a B-tree.
8. Determine the maximum height of a B⁺-tree with *n* keys.
9. Propose a procedure to keep prefix-B⁺-tree separators as short as possible.
10. Write a method computing the shortest valid separator between two adjacent keys.
11. Discuss whether abbreviated prefixes should be used in prefix-B⁺-tree leaves.
12. Determine the D-bit consistency condition between two equal discrimination bits at different leaf positions.
13. Determine how deleting a key from a bit-tree leaf affects the neighboring D-bit; generalize from examples.
14. Write an algorithm to find all R-tree leaf entries whose rectangles overlap a query rectangle.
15. Explain why only small-order B-trees (not large-order) are typically used despite comparable efficiency to BSTs.
16. Determine the worst case for inserting a key into a 2–4 tree.
17. Determine the worst-case complexity of `compressTrie()`.
18. Determine whether compressed-trie leaves can still hold abbreviated word remnants.
19. Propose a scheme to handle mixed-case words in a 26-letter trie without doubling the alphabet.
20. Discuss whether a **digital tree** (bit-level trie) suits a spell-checking application.

---

## Chapter 8 — Graphs

### ADTs
- **Graph** (directed/undirected; adjacency matrix `O(V²)` space / `O(1)` edge lookup, or adjacency list `O(V+E)` space / `O(degree)` edge lookup).
- **Union-Find (disjoint-set) structure.** `find()` and `union()` operations, used for cycle detection and Kruskal-style spanning trees; with path compression and union-by-rank, amortized cost is nearly `O(1)` (inverse-Ackermann) per operation.

### Algorithms
- **Breadth-first search (BFS)** — `O(V+E)`, explores level by level via a queue; used for shortest paths in unweighted graphs, connectivity checks.
- **Depth-first search (DFS)** — `O(V+E)`, explores as deep as possible via recursion/stack; underlies cycle detection, topological sort, connectivity.
- **Dijkstra's algorithm** — single-source shortest paths for graphs with non-negative edge weights; `O(V²)` with a simple array-based priority queue, `O((V+E) lg V)` with a binary heap.
- **Ford's algorithm (Bellman–Ford-style / D'Esopo-Pape variant)** — handles negative edge weights (but not negative cycles); `O(V·E)` worst case; can loop indefinitely if a negative cycle exists unless guarded.
- **Generic shortest-path algorithm framework** — a relaxation-based template unifying Dijkstra's and Ford's approaches.
- **All-to-all shortest paths** (Floyd–Warshall-style dynamic programming) — `O(V³)`.
- **Cycle detection via union-find** — `O(E · α(V))` (α = inverse Ackermann, effectively constant).
- **Minimum spanning tree** — Prim's-style (grow one tree, `O(V²)` or `O(E lg V)` with a heap) and Kruskal's-style (sort edges, union-find to avoid cycles, `O(E lg E)`) approaches via a generic spanning-tree construction template.
- **Connectivity algorithms:**
  - **Undirected graphs** — articulation-point/biconnected-component detection via DFS, `O(V+E)`.
  - **Directed graphs** — strong-connectivity checks (mutual reachability), `O(V+E)`.
- **Topological sort** — `O(V+E)`, via repeated removal of in-degree-0 vertices or DFS finish-order reversal; only defined for DAGs.
- **Maximum flow — Ford-Fulkerson algorithm** — repeatedly finds augmenting paths (via BFS/DFS) in the residual graph and pushes flow along them; `O(E · max_flow)` in the naive form (worse with poor path choice, as the case study's inefficiency example shows); labeling scheme tracks `parent`/`slack` per vertex.
- **Minimum-cost maximum flow** — augments along the cheapest augmenting path each iteration (shortest-path-in-residual-graph by cost), tracking `parent`/`flow`/`cost` labels.
- **Matching algorithms:**
  - **Bipartite matching via augmenting paths** — `O(V·E)`.
  - **Stable matching problem (Gale–Shapley-style)** — `O(n²)` for *n* pairs, always terminates with a stable matching.
  - **Assignment problem** — optimal bipartite matching under weighted costs (Hungarian-algorithm family).
  - **Matching in nonbipartite graphs** — more involved (blossom-style) augmenting-path search.
- **Eulerian graphs** — existence check (all vertices even degree, or exactly two odd for a path) `O(V+E)`; circuit construction via Hierholzer-style edge-following. **Chinese Postman Problem** — find minimum-cost route traversing every edge at least once, by adding minimum-weight matching edges to make the graph Eulerian.
- **Hamiltonian graphs** — Hamiltonian cycle search is NP-complete in general; **Traveling Salesman Problem (TSP)** — NP-hard optimization variant seeking the minimum-weight Hamiltonian cycle; exact search is exponential, so heuristics/approximations are discussed.
- **Graph coloring — Brélaz's algorithm (`BrelazColoringAlgorithm()`)** — greedy heuristic coloring vertices in order of "saturation degree" (number of distinctly colored neighbors); not guaranteed optimal, but effective in practice.
- **NP-complete graph problems** (each reduced to/from SAT or an earlier NP-complete problem):
  - **Clique problem** — does a graph contain a complete subgraph of size *k*?
  - **3-Colorability problem** — can the graph be properly colored with 3 colors?
  - **Vertex Cover problem** — does a set of *k* vertices cover all edges?
  - **Hamiltonian Cycle problem** — does a Hamiltonian cycle exist?
- **Distinct representatives (case study)** — finding a system of distinct representatives for a family of sets, solved via bipartite matching.

### Exercises (Section 8.14)
1. Identify a respect in which graphs are "more specific" than trees.
2. Relationship between the sum of vertex degrees and the number of edges.
3. Complexity of `breadthFirstSearch()`.
4. Show a simple graph is connected if it has a spanning tree.
5. Show a tree with *n* vertices has *n*-1 edges.
6. Adapt `DijkstraAlgorithm()` to undirected graphs.
7. Modify `DijkstraAlgorithm()` to stop early for an a→b shortest-path query.
8. Analyze whether omitting a re-add-to-queue clause from `DijkstraAlgorithm()` (present in the generic template) causes problems.
9. Modify `FordAlgorithm()` to avoid infinite loops on graphs with negative cycles.
10. Determine digraphs for which `FordAlgorithm()`'s while loop iterates exactly once or twice.
11. Determine whether `FordAlgorithm()` applies to undirected graphs.
12. Adapt `FordAlgorithm()` for the all-to-one shortest-path problem; apply it to a given graph and vertex.
13. Analyze the exponential worst case of the D'Esopo-Pape algorithm using Kershenbaum's (1981) pathological-graph construction. *(Continues with further parts on constructing/analyzing these graphs.)*
14–33 (remaining exercises continue through spanning trees, connectivity, topological sort, network flow, matching, Eulerian/Hamiltonian graphs, coloring, and NP-completeness reductions, mirroring the chapter's full scope).

---

## Chapter 9 — Sorting

### ADTs
- None new — operates on arrays/lists in place or via merging.

### Algorithms
- **Insertion sort** — `O(n²)` worst/average, `O(n)` best (already sorted); stable; adaptive (fast on nearly-sorted input); in-place, `O(1)` extra space.
- **Selection sort** — `O(n²)` in all cases (always scans remaining unsorted portion for the minimum); not stable as typically implemented; in-place.
- **Bubble sort** — `O(n²)` worst/average, `O(n)` best with an early-exit flag; stable; in-place.
- **Decision trees** — used to prove the `Ω(n lg n)` lower bound for any comparison-based sorting algorithm.
- **Shell sort** — generalizes insertion sort using a shrinking gap sequence; complexity depends on gap sequence, typically `O(n^1.25)`–`O(n^1.5)`; not stable; in-place.
- **Heapsort** — build a heap `O(n)` (Floyd's method), then repeatedly extract the max `O(lg n)` each: total `O(n lg n)` worst/average/best; not stable; in-place, `O(1)` extra space.
- **Quicksort** — average `O(n lg n)`, worst `O(n²)` (already-sorted or adversarial pivot choice); not stable (as typically implemented); in-place (`O(lg n)` stack space for recursion).
- **Mergesort** — `O(n lg n)` in all cases; stable; requires `O(n)` auxiliary space for merging.
- **Radix sort** — `O(d·(n+k))` where *d* = number of digits/keys, *k* = digit range (e.g., 10); stable; not comparison-based, so it bypasses the `Ω(n lg n)` lower bound.
- **`java.util` built-in sorting** — `Arrays.sort`/`Collections.sort`, typically a tuned mergesort variant (Timsort) for objects and a dual-pivot quicksort variant for primitives.
- **Adding polynomials (case study)** — represents each polynomial as a sorted linked list of (coefficient, exponents) terms; merging two polynomials in sorted-exponent order is `O(m+n)` for terms *m*, *n*.

### Exercises (Section 9.7)
1. Identify which of five listed operations (anagram check, min-finding, average, median, mode) benefit from pre-sorted data.
2. Add an early-exit flag to `bubblesort()` to skip unnecessary passes once sorted.
3. Analyze correctness/complexity if `bubblesort()`'s inner-loop bound is changed to always scan to index 0.
4. Modify bubble sort to bubble the largest element down instead of the smallest up.
5. Implement **cocktail shaker sort** (Knuth) — bidirectional bubble sort — and analyze its complexity.
6. Replace insertion sort's linear scan with binary search for the insertion point; determine resulting complexity.
7. Draw decision trees for the elementary sorts on array `[a b c d]`.
8. Determine which sorting algorithms adapt easily to singly/doubly linked lists.
9. Determine min/max comparisons and movements to sort 4 elements using Shell sort, heapsort, quicksort, and mergesort.
10. Implement and test `mergesort()`.
11. Prove the mergesort comparison-count formula `C(n) = n lg n − 2^{lg n} + 1`.
12. Implement and analyze a **bottom-up (nonrecursive) mergesort**.
13. Implement a **natural mergesort** that merges only pre-existing runs, vs. straight merging; investigate complexity.
14. Implement mergesort on a linked list instead of an array to avoid double workspace; discuss when this is advantageous.
15. Identify which sorting algorithms are **stable**.
16. Analyze the complexity of a **slow sorting** algorithm (Julstrom 1992) applying selection sort to progressively finer element subsets.

---

## Chapter 10 — Hashing

### ADTs
- **Hash table.** Average-case `O(1)` search/insert/delete under a good hash function and reasonable load factor; worst case `O(n)` (all keys collide).
- **`java.util.HashMap` / `HashSet` / `Hashtable`.** Chaining-based hash implementations; `O(1)` average for `get`/`put`/`contains`, `O(n)` worst case.

### Algorithms
- **Hash functions:**
  - **Division** — `h(K) = K mod TableSize` (choose TableSize prime to reduce clustering).
  - **Folding** — split the key into parts and combine (add/XOR) them.
  - **Mid-square** — square the key and extract middle digits.
  - **Extraction** — use only selected digits/characters of the key.
  - **Radix transformation** — reinterpret the key in a different base/radix.
- **Collision resolution:**
  - **Open addressing** — linear probing, quadratic probing, double hashing; all `O(1)` average, degrade as load factor approaches 1; requires careful **deletion** handling (tombstones) to avoid breaking probe chains.
  - **Chaining** — each bucket holds a linked list (or BST) of colliding entries; `O(1 + α)` average where α is the load factor; naturally supports deletion without tombstones.
  - **Bucket addressing** — group table slots into buckets holding multiple entries before overflow, reducing collision frequency for a given load.
- **Perfect hash functions:**
  - **Cichelli's method** — assigns letter values so each word's hash (via first+last letter value) is unique and minimal (occupies a contiguous range); words pre-sorted by first/last-letter frequency to speed up the backtracking search.
  - **FHCD algorithm** — a different technique for constructing perfect hash functions, dependent on a parameter *r* controlling the size of the auxiliary structure.
- **Hashing for extendible files:**
  - **Extendible hashing (`extendibleHashingInsert()`)** — a directory of pointers to buckets, indexed by the first *depth* bits of `h(K)`; the directory doubles (and *depth* increments) when a bucket overflows and cannot be resolved by a local split.
  - **Linear hashing** — grows the table by splitting buckets in a fixed, predetermined order (not tied to which bucket overflowed) with an overflow area absorbing interim collisions.
- **Hashing with buckets (case study)** — a phone-directory application combining a hash function with bucket-based collision handling.

### Exercises (Section 10.8)
1. Minimum number of keys hashed to their home positions under linear probing; give a 5-cell example.
2. Analyze a quotient/remainder-based probing sequence (Bell & Kaman 1970).
3. Discuss whether BSTs improve on linked lists for separate chaining.
4. Explain why Cichelli's method pre-sorts words by first/last-letter frequency despite the search not referencing that order directly.
5. Trace Cichelli's searching algorithm with Max = 3.
6. Identify the case where Cichelli's method cannot guarantee a minimal perfect hash function.
7. Apply the FHCD algorithm to the nine Muses with r = 4 and r = 2; compare impact of *r*.
8. Explain in what sense extendible hashing's hash function itself dynamically changes.
9. Analyze an extendible-hashing variant restricting each bucket to a single directory reference.
10. Determine how the directory updates if the *last* (not first) `depth` bits of `h(K)` are used as the index.
11. Compare similarities/differences between extendible hashing and B⁺-trees.
12. Discuss the impact of uniform key distribution on split frequency in extendible hashing.
13. Apply linear hashing to insert {12,24,36,48,60,72,84} into a 3-bucket table with a 3-cell overflow area; identify the resulting problem.
14. Outline a key-deletion algorithm for linear hashing.
15. Discuss replacing XOR with AND/OR in the case study's folding `hash()` function.

---

## Chapter 11 — Data Compression

### ADTs
- **Huffman tree.** Binary tree where leaf depth is inversely related to symbol frequency; guarantees an optimal (minimum expected length) prefix code. Construction `O(n lg n)` for *n* distinct symbols (priority-queue-based).

### Algorithms
- **Huffman coding (`Huffman()`, `createHuffmanTree()`)** — repeatedly merges the two lowest-frequency nodes/subtrees into a new parent until one tree remains; `O(n lg n)`.
- **Adaptive Huffman coding** — builds and updates the code tree on the fly as symbols are seen, maintaining the **sibling property** (nodes ordered by weight, siblings adjacent) so the tree can be incrementally re-balanced in `O(lg n)` per symbol without transmitting a static frequency table.
- **Run-length encoding (RLE)** — replaces runs of a repeated symbol with a (count, symbol) pair; `O(n)`, effective only for data with long repeated runs.
- **Ziv–Lempel (LZW) coding (`LZWcompress()` / `LZWdecompress()`)** — builds a dictionary of previously seen substrings on the fly, replacing repeated substrings with dictionary indices; `O(n)` for both compression and decompression under typical dictionary-size assumptions.
- **Huffman + run-length encoding combined (case study)** — applies RLE as a pre-pass to exploit repeated runs, then Huffman-codes the result for further compression.

### Exercises (Section 11.6)
1. Determine which symbol-probability distribution maximizes/minimizes average Huffman code length.
2. Compute `L_ave` for three symbols with given probabilities; compare to `L_Huf` for single letters vs. letter pairs.
3. Assess the complexity of all Huffman-algorithm implementations discussed.
4. Determine relative codeword lengths for the least-probable messages.
5. Discuss whether adaptive Huffman coding could update the table *before* issuing the codeword, and why/why not.
6. Explain why `createCodewords()` and `transformTreeToArrayOfLists()` don't need an explicit null check despite accessing `p.left` first.
7. Analyze the problem with reordering RLE triples as ⟨count, symbol⟩ vs. ⟨count, char, n⟩ variants.
8. Discuss how choosing power-of-two window sizes (l₁=l₂=16) simplifies an LZ77 implementation.
9. Identify best-case/worst-case scenarios for LZ77 compression.
10. Describe LZ77 decoding; decode a specific given codeword sequence.
11. Decode a specific LZW-coded string given an initial three-letter dictionary.

---

## Chapter 12 — Memory Management

### ADTs
- **Free list.** Implicit linked structure of unallocated memory blocks, threading through sequential-fit and buddy-system schemes.

### Algorithms
- **Sequential-fit methods** — **first-fit** (`O(n)` worst case scanning the free list), **best-fit** (`O(n)`, minimizes leftover fragment but risks many tiny unusable fragments), **worst-fit** (`O(n)`, leaves larger, more reusable fragments).
- **Nonsequential-fit / buddy systems** — memory divided into power-of-two-sized blocks; on free, a block is merged ("coalesced") with its "buddy" if that buddy is also free, restoring larger blocks; allocation/deallocation `O(lg(max size))`. The **Fibonacci buddy system (`reserveFib()`)** uses Fibonacci-sized blocks instead of powers of two, trading exact power-of-two convenience for finer size granularity.
- **Garbage collection:**
  - **Mark-and-sweep** — *marking* phase traverses all reachable objects from roots (`O(live objects + pointers)`), setting a mark bit; *sweep* phase (`sweep()`) scans the entire heap `O(heap size)` reclaiming unmarked blocks; optional *compaction* phase relocates live objects to eliminate fragmentation, `O(heap size)`.
  - **Copying methods** — divides heap into two semispaces; live objects are copied from the active ("fromspace") to the inactive ("tospace") semispace, automatically compacting as a side effect; cost proportional to the volume of *live* data, not total heap size.
  - **Incremental garbage collection** — interleaves small increments of collection work with mutator (program) execution to bound pause times; both copying-based (e.g., Baker's algorithm) and noncopying/reference-counting-based approaches (e.g., Lisp's `cons`-based `createRootPtr()`) are covered.
- **In-place garbage collector (case study)** — demonstrates a concrete mark-and-sweep-with-compaction implementation operating without extra heap space.

### Exercises (Section 12.6)
1. Analyze first-fit's behavior when applied to a size-ordered free list.
2. Discuss how block-list ordering affects coalescing effort in sequential-fit methods, and how to mitigate issues.
3. Discuss the efficiency dependencies of the **optimal-fit** method (Campbell 1971) and compare it to other sequential-fit methods.
4. Determine when the size-list in the adaptive exact-fit method can be empty, and its maximum size.
5. Explain why buddy systems use doubly, not singly, linked block lists.
6. Give an algorithm for returning blocks to the pool under the Fibonacci buddy system.
7. Apply `markingWithStack()` to left- and right-degenerate list structures (Fig. 12.17); count `push()`/`pop()` calls and identify optimization opportunities.
8. Discuss advantages/disadvantages of **reference-counting** garbage collection.
9. In Baker's algorithm, determine the scan-rate parameter *k* needed to finish scanning before `bottom` reaches `top`; discuss impact of doubling *k*.
10. Propose a solution to the object-crosses-page-boundary problem in a page-based variant of Baker's algorithm (Ellis, Li & Appel 1988).

---

## Chapter 13 — String Matching

### ADTs
- **Suffix trie / suffix tree.** Represents all suffixes of a text for `O(m)` substring search (*m* = pattern length) after `O(n)` (Ukkonen) or `O(n²)` (brute-force) construction, *n* = text length.
- **Suffix array.** Sorted array of suffix start-indices; more space-efficient than a suffix tree; supports `O(m lg n)` search via binary search (or `O(m + lg n)` with an auxiliary LCP array).

### Algorithms
- **Straightforward (brute-force) string matching** — `O(nm)` worst case, checking every alignment.
- **Knuth–Morris–Pratt (KMP)** — precomputes a failure function (`next`/`nextS`) in `O(m)`, then scans the text in `O(n)` without re-examining matched characters — `O(n+m)` total.
- **Boyer–Moore algorithm** — precomputes bad-character and good-suffix shift tables (`O(m + alphabet size)`), then scans right-to-left within each alignment, often skipping large sections of text; average case sub-linear, worst case `O(nm)` for the naive shift rule (improved to `O(n+m)` with the **Boyer-Moore-Galil** variant that tracks periods, or with **Boyer-Moore-Horspool**'s single-table simplification). The **Sunday (quickSearch)** variant examines the character just past the current window for a simpler, often faster-in-practice shift rule.
- **Multiple-pattern search** — extending single-pattern shift-based techniques to search for several patterns concurrently.
- **Bit-oriented (shift-and/shift-or) matching** — uses bitwise operations (`shiftAnd()`/`shiftOr()`) to track partial matches across all alignments simultaneously in a single machine word; `O(n)` for patterns up to word length.
- **Aho–Corasick algorithm (`AhoCorasick()`)** — builds a trie of all keywords with failure links (à la KMP, generalized to a set), then scans the text once, reporting all keyword occurrences — `O(n + m_total + z)` where z = number of matches.
- **Regular expression matching** — converts a regex to an automaton (NFA/DFA) and simulates it against the text.
- **Suffix tree construction** — **brute-force (`bruteForceSuffixTree()`)**, `O(n²)`; **Ukkonen's algorithm (`UkkonenSuffixTree()`)**, `O(n)` online construction.
- **Suffix arrays** as a more space-efficient alternative to suffix trees for many of the same queries.
- **Approximate string matching:**
  - **String similarity / edit distance** — dynamic-programming (Wagner-Fischer) computation of minimum insert/delete/substitute operations to transform one string into another; `O(nm)` time and space (reducible to `O(min(n,m))` space).
  - **String matching with *k* errors** — a modified edit-distance table (Sellers 1980) that allows a match to start anywhere in the reference text, flags any end-of-string entry ≤ *k* as an approximate match location.
- **Longest common substring (case study)** — typically solved via suffix structures or dynamic programming, `O(n+m)` with a generalized suffix tree or `O(nm)` with a DP table.

### Exercises (Section 13.4)
1. Apply KMP with `next` and then `nextS` to given pattern/text strings.
2. Determine positions i, j, k where `findNextS()` chains equal `nextS` values for a given string.
3. Determine the total comparison count for the worst-case partial-suffix phase of `computeDelta2ByBruteForce()`.
4. Determine the minimum comparisons when searching for a pattern absent from the text, for (a) KMP, (b) Boyer-Moore.
5. Explain the symmetry between brute-force and Boyer-Moore-Simple worst-case pattern/text examples.
6. Propose a better shift rule for `BoyerMooreSimple()` that aligns the mismatched text character with its nearest occurrence to the left of the mismatch in the pattern; outline an implementation.
7. Apply **Boyer-Moore-Horspool** (single-table variant) and `BoyerMooreSimple()` to given pattern/text strings.
8. Implement the `period()` function used by `BoyerMooreGalil()`.
9. Restructure `BoyerMooreGalil()` to preprocess for periods once and dispatch to a period-free fast path.
10. Adapt `quickSearch()` (Sunday's algorithm) to match right-to-left.
11. Give an example where `BoyerMooreSimple()` shifts further than Sunday's `quickSearch()`.
12. Rewrite `shiftAnd()` as `shiftOr()` by reversing the bit-role convention (Baeza-Yates & Gonnet 1992), reducing to three bitwise ops per iteration.
13. Determine the maximum possible size of an Aho-Corasick `output(state)` set.
14. Draw Ukkonen suffix tries for eight given 4-character strings.
15. Determine how to count pattern occurrences in a text using its suffix tree.
16. Determine how a suffix tree can find all substrings of Q that are not substrings of R.
17. Adapt the Wagner-Fischer edit-distance algorithm (Sellers 1980) for k-difference matching with modified boundary conditions; build the edit table for given Q and R strings.

---

## Appendix A — Computing Big-O
- **Harmonic series approximation** — bounding ∑(1/i) by ln(n).
- **Stirling-style approximation of lg(n!)** — used to derive `Θ(n lg n)` bounds for comparison-sort-related quantities.
- **Average-case Big-O derivation for quicksort** — formal derivation of the `O(n lg n)` average-case bound.
- **Node-count bounds for AVL trees** — derives the minimum node count for an AVL tree of height *h* (linked to the Fibonacci-tree exercise in Chapter 6), establishing the `O(lg n)` height guarantee.

## Appendix B — NP-Completeness
- **Cook's theorem** — proof that Boolean satisfiability (SAT) is NP-complete, the foundational reduction target underlying every other NP-completeness argument in Chapters 2 and 8 (3-SAT, Clique, 3-Colorability, Vertex Cover, Hamiltonian Cycle).

---

## Summary Index of All ADTs Covered (with typical complexity)

| ADT | Chapter(s) | Typical Search/Access | Typical Insert/Delete |
|---|---|---|---|
| Vector / ArrayList | 1, 3 | O(1) index access | O(1) amortized append |
| Singly/doubly/circular linked list | 3 | O(n) | O(1) at known node |
| Skip list | 3 | O(lg n) expected | O(lg n) expected |
| Self-organizing list | 3 | O(n) worst, better amortized | O(1)–O(n) |
| Sparse table | 3 | O(1)–O(k) (k = nonzero entries) | O(1)–O(k) |
| Stack / Queue | 4 | O(1) top/front | O(1) push/pop, enqueue/dequeue |
| Priority queue (heap-based) | 4, 6 | O(1) peek | O(lg n) |
| Binary tree / BST | 6 | O(n) / O(lg n) avg, O(n) worst | O(lg n) avg, O(n) worst |
| AVL tree | 6 | O(lg n) worst | O(lg n) worst |
| Threaded binary tree | 6 | O(n) traversal, O(1) space | O(lg n)-ish, more bookkeeping |
| Splay tree | 6 | O(lg n) amortized | O(lg n) amortized |
| Heap | 6 | O(1) peek | O(lg n) |
| Expression tree | 6 | O(n) evaluate/traverse | O(n) build |
| B-tree / B\*/B⁺/prefix B⁺ | 7 | O(lg n) (small constant, large branching) | O(lg n) |
| Bit-tree | 7 | O(lg n)-ish, compact leaves | O(lg n)-ish |
| R-tree | 7 | O(lg n) avg spatial query | O(lg n) avg |
| 2–4 tree | 7 | O(lg n) | O(lg n) |
| Trie (incl. compressed) | 7 | O(k), k = key length | O(k) |
| TreeSet / TreeMap | 7 | O(lg n) | O(lg n) |
| Graph (adjacency list/matrix) | 8 | O(1)–O(V) edge check | O(1)–O(E) |
| Union-Find | 8 | O(α(n)) amortized find | O(α(n)) amortized union |
| Hash table (all variants) | 10 | O(1) avg, O(n) worst | O(1) avg, O(n) worst |
| HashMap / HashSet / Hashtable | 10 | O(1) avg | O(1) avg |
| Huffman tree | 11 | O(code length) per symbol | O(n lg n) build |
| Free list / buddy system | 12 | O(n) or O(lg max) | O(n) or O(lg max) |
| Suffix trie / suffix tree | 13 | O(m) substring search | O(n) build (Ukkonen) |
| Suffix array | 13 | O(m lg n) search | O(n lg n) build (typical) |

## Summary Index of Named Algorithms (with complexity)

| Algorithm | Chapter | Complexity |
|---|---|---|
| Amortized-cost accounting (dynamic array) | 2 | O(1) amortized per op |
| Insertion sort | 9 | O(n²) worst/avg, O(n) best |
| Selection sort | 9 | O(n²) always |
| Bubble sort | 9 | O(n²) worst/avg, O(n) best (w/ flag) |
| Shell sort | 9 | ~O(n^1.25)–O(n^1.5), gap-dependent |
| Heapsort | 9 | O(n lg n) always |
| Quicksort | 9 | O(n lg n) avg, O(n²) worst |
| Mergesort | 9 | O(n lg n) always, O(n) space |
| Radix sort | 9 | O(d(n+k)) |
| Self-organizing list heuristics | 3 | O(n) worst, improved amortized |
| Backtracking (Eight Queens) | 5 | Exponential worst case, pruned in practice |
| DSW algorithm | 6 | O(n) time, O(1) space |
| AVL rotations | 6 | O(1) per rotation, O(lg n) per op |
| Splaying | 6 | O(lg n) amortized |
| Floyd's heap construction | 6 | O(n) |
| Williams's heap construction | 6 | O(n lg n) |
| Flag flipping (2–4 trees) | 7 | O(lg n) |
| Trie compression | 7 | O(n) (n = trie size) |
| BFS / DFS | 8 | O(V+E) |
| Dijkstra's algorithm | 8 | O(V²) or O((V+E) lg V) |
| Ford's (Bellman-Ford/D'Esopo-Pape) algorithm | 8 | O(V·E) |
| All-to-all shortest paths (Floyd-Warshall style) | 8 | O(V³) |
| Prim's / Kruskal's-style MST | 8 | O(V²) / O(E lg V) / O(E lg E) |
| Topological sort | 8 | O(V+E) |
| Ford-Fulkerson (max flow) | 8 | O(E · max_flow) naive |
| Min-cost max flow | 8 | O(max_flow · shortest-path cost) |
| Stable matching (Gale-Shapley style) | 8 | O(n²) |
| Brélaz graph coloring | 8 | O(V²) typical greedy |
| Division / folding / mid-square / extraction hashing | 10 | O(1) per hash computation |
| Cichelli's method | 10 | Exponential search worst case, practical for small sets |
| FHCD algorithm | 10 | Depends on parameter r |
| Extendible hashing | 10 | O(1) avg access, O(1) amortized directory doubling |
| Linear hashing | 10 | O(1) avg access |
| Huffman coding (static) | 11 | O(n lg n) |
| Adaptive Huffman coding | 11 | O(lg n) per symbol |
| Run-length encoding | 11 | O(n) |
| LZW (Ziv-Lempel) coding | 11 | O(n) |
| Sequential-fit allocation | 12 | O(n) (free-list length) |
| Buddy systems | 12 | O(lg max size) |
| Mark-and-sweep GC | 12 | O(live objects + heap size) |
| Copying GC | 12 | O(live data volume) |
| Knuth-Morris-Pratt | 13 | O(n+m) |
| Boyer-Moore / Sunday / Horspool | 13 | O(n+m) avg, sub-linear typical |
| Aho-Corasick | 13 | O(n + total pattern length + matches) |
| Ukkonen's suffix tree algorithm | 13 | O(n) |
| Edit distance (Wagner-Fischer) | 13 | O(nm) time/space |
| k-error approximate matching (Sellers) | 13 | O(nm) |

---

## Full Exercise Index by Chapter (topic-only quick reference)

| Chapter | # Exercises Listed | Representative Topics |
|---|---|---|
| 1. OOP in Java | 6+ core (assignments continue similarly) | Access modifiers, overriding/overloading, interfaces, abstract classes, `equals()` semantics |
| 2. Complexity Analysis | 13 | Big-O/Ω/Θ proofs, loop complexity derivation, amortized binary-counter analysis, SAT→3-SAT reduction |
| 3. Linked Lists | 25 | List merging/splitting/deletion variants, circular list implementations, self-organizing list variants, sparse-table design |
| 4. Stacks and Queues | 11 | Stack/queue reordering with auxiliary structures, mutual stack/queue implementation, maze path reconstruction |
| 5. Recursion | 28 | Recursive numeric/string routines, Napier's logarithm method, power-by-squaring, n-queens variants, interpreter extensions |
| 6. Binary Trees | 25 | Tree metrics and mirroring, traversal equivalences, threaded-tree operations, Fibonacci trees, heap-construction analysis |
| 7. Multiway Trees | 20 | B-tree capacity/height bounds, B\*/B⁺-tree edge cases, bit-tree D-bit behavior, R-tree overlap queries, trie compression |
| 8. Graphs | 30+ | Graph/tree relationships, shortest-path algorithm adaptations, spanning trees, connectivity, flow/matching, coloring, NP-completeness reductions |
| 9. Sorting | 16 | Bubble/selection sort variants, decision trees, mergesort variants (bottom-up, natural), stability, "slow sort" analysis |
| 10. Hashing | 15 | Probing sequences, chaining vs. BSTs, Cichelli's/FHCD tracing, extendible/linear hashing mechanics |
| 11. Data Compression | 11 | Huffman code-length bounds, adaptive Huffman ordering, RLE triple ordering, LZ77/LZW encode-decode tracing |
| 12. Memory Management | 10 | Fit-method behavior, buddy-system list structure, mark-and-sweep tracing, reference counting, Baker's algorithm tuning |
| 13. String Matching | 17 | KMP/Boyer-Moore tracing and shift-rule design, bit-oriented matching, Aho-Corasick bounds, suffix-tree applications, k-error matching |

*(Where an exercise list continues beyond the excerpted numbering above — e.g., Chapters 1 and 8 — the pattern of topics continues consistently with what's shown; refer to the source PDF/markdown directly for the exhaustive verbatim wording of every sub-item.)*
