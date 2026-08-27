# Data Structures and Algorithms in Java

[![Java CI with Maven](https://github.com/dmancilla85/java-drozdek/actions/workflows/maven.yml/badge.svg)](https://github.com/dmancilla85/java-drozdek/actions/workflows/maven.yml)
[![CodeQL](https://github.com/dmancilla85/java-drozdek/actions/workflows/codeql.yml/badge.svg?branch=master)](https://github.com/dmancilla85/java-drozdek/actions/workflows/codeql.yml)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=dmancilla85_java-drozdek)](https://sonarcloud.io/summary/new_code?id=dmancilla85_java-drozdek)

A collection of abstract data types and algorithms initially based on the book *Data Structures and Algorithms in Java* by Adam Drozdek.

## Prerequisites

- **JDK 25** (earlier versions will not compile — `preview` features used)
- **Maven 3.9+**

## Quick Start

```bash
# Build and run all tests
mvn clean test

# Run a single algorithm demo (e.g., QuickSort on random data)
mvn exec:java -Dexec.mainClass="org.drozdek.sorting.QuickSort" -q

# Generate Javadoc HTML
mvn javadoc:javadoc
# Open target/reports/apidocs/index.html in a browser
```

## Tech Stack

| | |
|---|---|
| **Language** | Java 25 |
| **Build** | Maven 3.9+ |
| **Testing** | JUnit Jupiter 6.0.3 (854 tests) |
| **Coverage** | JaCoCo 0.8.14 (90% instruction) |
| **Static analysis** | SonarCloud, Qodana, Checkstyle (Google) |
| **CI/CD** | GitHub Actions, Dependabot |

## Modules

- **Sorting** — Bubble, Bucket, Counting, Heap, Insertion, Merge, Quick, Selection, Shell + exercises (Closest Pair, Ternary Merge)
- **Searching** — Binary, Exponential, Fibonacci, Interpolation, Jump, Linear, Sentinel Linear, Ternary
- **Trees** — AVL, BST, Expression Tree, Heap, Max/Min Heap, Red-Black, Simple Tree, Splay, Static Binary, Suffix Tree, Trie, Threaded Tree + `trees.applications` (PrefixAutoComplete)
- **Lists** — Single/Double/Circular/Double Circular Linked Lists, Skip List + `lists.applications` (MusicPlaylist)
- **Stacks** — Adaptive, Array, Linked List, Stack with format + `stacks.applications` (BalancedBracketValidator)
- **Queues** — Array, Deque, Dynamic, Static, Heap + `queues.applications` (PrintSpooler)
- **Graphs** — Directed/Undirected/Weighted with Dijkstra, Prim-Jarnik, Kruskal, Floyd-Warshall (algorithms in `graphs/algorithms/`) + `graphs.applications` (BuildDependencyResolver)
- **Hashing** — Hash Table (separate chaining, auto-resize) + `hashing.applications` (UserSessionStore)
- **Dynamic Programming** — Fractional Knapsack, 0/1 Knapsack, Task Scheduling
- **Recursion** — Alphabetically Sorted, Majority Element, Towers of Hanoi

## Build

```bash
mvn clean package
mvn test
mvn jacoco:report     # coverage report → target/site/jacoco/
```

## Project Structure

```
src/main/java/org/drozdek/
├── commons/       utilities
├── dynamic/       knapsack and scheduling algorithms
├── graphs/        graph ADT, graph algorithms, and build dependency resolver
├── hashing/       hash table and in-memory session store
├── lists/         linked lists, skip list, and music playlist manager
├── queues/        queue implementations and FIFO print spooler
├── recursion/     recursive algorithms
├── searching/     search algorithms
├── sorting/       sorting algorithms
├── stacks/        stack implementations and balanced bracket validator
└── trees/         tree ADTs (BST, Trie, Suffix, etc.) and autocomplete engine

src/test/java/org/drozdek/
└── (mirrors main structure, 88 test files)
```

## Documentation Conventions

All public APIs use modern markdown doc comments (`///`, JEP-467) following a
consistent template (class-level includes **Real-world use case** and **Complexity
Analysis** sections). Javadoc is generated via `mvn javadoc:javadoc`.

Each package contains a `package-info.java` summarizing its scope.
See [docs/report.md](docs/report.md) for a comprehensive architecture and change report.

## License

MIT
