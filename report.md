# Abstract Data Type (ADT) Applications Report

## Executive Summary

To illustrate the practical value and concrete problem-solving capabilities of the core Abstract Data Types (ADTs) in the `java-drozdek` project, dedicated `applications` subpackages have been introduced across all six core data structure modules:

1. **Stacks** (`org.drozdek.stacks.applications`) — `BalancedBracketValidator`
2. **Queues** (`org.drozdek.queues.applications`) — `PrintSpooler`
3. **Lists** (`org.drozdek.lists.applications`) — `MusicPlaylist`
4. **Trees** (`org.drozdek.trees.applications`) — `PrefixAutoComplete`
5. **Graphs** (`org.drozdek.graphs.applications`) — `BuildDependencyResolver`
6. **Hashing** (`org.drozdek.hashing.applications`) — `UserSessionStore`

Each application is self-contained, adheres strictly to project code standards (Google Java Style, 120-character line length, Java 25 features, modern `///` Markdown Javadoc), and is backed by a dedicated test suite in `src/test/java/org/drozdek/<adt>/applications/`.

---

## ADT Applications Catalog

### 1. Stacks: Balanced Bracket Validator
- **Package:** `org.drozdek.stacks.applications`
- **Class:** `BalancedBracketValidator`
- **Backing ADT:** `org.drozdek.stacks.ArrayStack<T>`
- **Problem Statement:** Syntax validation of nested delimiters (`()`, `[]`, `{}`) in programming languages, configuration files (JSON), and mathematical formulas.
- **Mechanism:** Scans input strings in $O(n)$ time. Opening brackets are pushed onto the LIFO stack; closing brackets must match the element popped from the top.
- **Complexity:**
  - *Time:* $O(n)$ single-pass scan
  - *Auxiliary Space:* $O(n)$ worst-case nesting depth

### 2. Queues: Multi-User Print Spooler
- **Package:** `org.drozdek.queues.applications`
- **Class:** `PrintSpooler` (with `PrintJob` record)
- **Backing ADT:** `org.drozdek.queues.Queue<T>` (`QueueInterface<T>`)
- **Problem Statement:** Asynchronous print job management where requests submitted by multiple network workstations must be buffered and printed in strict First-Come, First-Served (FIFO) order.
- **Mechanism:** Jobs are enqueued at the tail of the FIFO queue without blocking client callers. The printing engine processes and dequeues jobs from the head sequentially, recording cumulative page counts and job completion statistics.
- **Complexity:**
  - *Time:* $O(1)$ job submission (enqueue), dispatch (dequeue), and peek
  - *Auxiliary Space:* $O(n)$ for $n$ queued print jobs

### 3. Lists: Continuous Music Playlist Manager
- **Package:** `org.drozdek.lists.applications`
- **Class:** `MusicPlaylist` (with `Track` record)
- **Backing ADT:** `org.drozdek.lists.DoubleLinkedList<T>`
- **Problem Statement:** Sequential and bidirectional audio track navigation in a media player with track addition, deletion, and optional infinite loop playback.
- **Mechanism:** Tracks are stored as nodes in a doubly-linked list. Pointers allow instantaneous $O(1)$ stepping forward (`nextTrack()`) and backward (`previousTrack()`), wrapping around boundaries when looping is active.
- **Complexity:**
  - *Time:* $O(1)$ cursor advance/rewind, head/tail insertion; $O(n)$ value-based track search/deletion
  - *Auxiliary Space:* $O(n)$ track nodes

### 4. Trees: Prefix Search Auto-Complete Engine
- **Package:** `org.drozdek.trees.applications`
- **Class:** `PrefixAutoComplete`
- **Backing ADT:** `org.drozdek.trees.Trie`
- **Problem Statement:** Interactive query suggestion in search boxes and IDEs that retrieves vocabulary terms matching a partial input prefix.
- **Mechanism:** Terms inserted into the Trie share prefix nodes. Lookups traverse character paths in $O(L)$ time where $L$ is word length, irrespective of total vocabulary size.
- **Complexity:**
  - *Time:* $O(L)$ exact match and insertion; $O(L + K)$ prefix suggestions ($K$ matches)
  - *Auxiliary Space:* $O(N \cdot L)$ node space for $N$ vocabulary words

### 5. Graphs: Build System Dependency Resolver
- **Package:** `org.drozdek.graphs.applications`
- **Class:** `BuildDependencyResolver`
- **Backing ADT:** `org.drozdek.graphs.DirectedAcyclicGraph`
- **Problem Statement:** Determining valid compilation or execution schedules for software modules with prerequisite dependencies (e.g., Maven, Gradle), while preventing cyclic deadlock dependencies.
- **Mechanism:** Modules are mapped to vertices in a DAG. Adding a dependency adds a directed arc from prerequisite to dependent; any arc that introduces a path back to the origin is rejected as a circular dependency. Topological sorting generates the linear build order.
- **Complexity:**
  - *Time:* $O(V + E)$ cycle check and topological ordering
  - *Auxiliary Space:* $O(V)$ for vertex mappings and traversal queues

### 6. Hashing: In-Memory User Session Store
- **Package:** `org.drozdek.hashing.applications`
- **Class:** `UserSessionStore` (with `UserSession` record)
- **Backing ADT:** `org.drozdek.hashing.HashTable<K, V>`
- **Problem Statement:** High-throughput HTTP bearer token validation, TTL expiration enforcement, and session revocation in web APIs and microservices.
- **Mechanism:** Cryptographic session tokens map to `UserSession` state within a hash table with separate chaining. Authentication checks and revocations execute in expected $O(1)$ constant time.
- **Complexity:**
  - *Time:* $O(1)$ average expected token lookup, validation, and revocation
  - *Auxiliary Space:* $O(n)$ for $n$ active user sessions

---

## Verification & Test Results

| Verification Step | Command | Status | Metrics |
|---|---|---|---|
| **Checkstyle** | `mvn validate` | **PASSED** | 0 violations (Google Checks, 120-char limit) |
| **Unit Tests** | `mvn test` | **PASSED** | 854 tests executed, 0 failures, 0 errors |
| **Javadoc HTML** | `mvn javadoc:javadoc` | **PASSED** | 0 errors, generated at `target/reports/apidocs/` |
| **Full Build** | `mvn clean package` | **PASSED** | Complete build & packaging successful |

---

## Package Architecture Reference

```
org.drozdek.
├── commons/
├── dynamic/
├── graphs/
│   ├── algorithms/
│   └── applications/        <-- NEW: BuildDependencyResolver
├── hashing/
│   └── applications/        <-- NEW: UserSessionStore, UserSession
├── lists/
│   ├── interfaces/
│   ├── iterators/
│   ├── nodes/
│   └── applications/        <-- NEW: MusicPlaylist, Track
├── queues/
│   ├── interfaces/
│   ├── unlam/
│   └── applications/        <-- NEW: PrintSpooler, PrintJob
├── recursion/
├── searching/
├── sorting/
│   └── exercises/
├── stacks/
│   ├── interfaces/
│   └── applications/        <-- NEW: BalancedBracketValidator
└── trees/
    ├── interfaces/
    ├── nodes/
    └── applications/        <-- NEW: PrefixAutoComplete
```
