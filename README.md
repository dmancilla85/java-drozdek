# Data Structures and Algorithms in Java

[![Java CI with Maven](https://github.com/dmancilla85/java-drozdek/actions/workflows/maven.yml/badge.svg)](https://github.com/dmancilla85/java-drozdek/actions/workflows/maven.yml)
[![CodeQL](https://github.com/dmancilla85/java-drozdek/actions/workflows/codeql.yml/badge.svg?branch=master)](https://github.com/dmancilla85/java-drozdek/actions/workflows/codeql.yml)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=dmancilla85_java-drozdek)](https://sonarcloud.io/summary/new_code?id=dmancilla85_java-drozdek)

A collection of abstract data types and algorithms initially based on the book *Data Structures and Algorithms in Java* by Adam Drozdek.

## Tech Stack

| | |
|---|---|
| **Language** | Java 25 |
| **Build** | Maven 3.9+ |
| **Testing** | JUnit Jupiter 6.0.3 (801 tests) |
| **Coverage** | JaCoCo 0.8.14 (90% instruction) |
| **Static analysis** | SonarCloud, Qodana, Checkstyle (Google) |
| **CI/CD** | GitHub Actions, Dependabot |

## Modules

- **Sorting** — Bubble, Bucket, Counting, Heap, Insertion, Merge, Quick, Selection, Shell + exercises (Closest Pair, Ternary Merge)
- **Searching** — Binary, Exponential, Fibonacci, Interpolation, Jump, Linear, Sentinel Linear, Ternary
- **Trees** — AVL, BST, Expression Tree, Heap, Max/Min Heap, Red-Black, Simple Tree, Splay, Static Binary, Suffix Tree, Trie, Threaded Tree
- **Lists** — Single/Double/Circular/Double Circular Linked Lists, Skip List
- **Stacks** — Adaptive, Array, Linked List, Stack with format
- **Queues** — Array, Deque, Dynamic, Static, Heap
- **Graphs** — Directed/Undirected/Weighted with Dijkstra, Prim-Jarnik, Kruskal, Floyd-Warshall
- **Hashing** — Hash Table (separate chaining, auto-resize)
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
├── graphs/        graph ADT and graph algorithms
├── hashing/       hash table (separate chaining)
├── lists/         linked lists and skip list
├── queues/        queue implementations
├── recursion/     recursive algorithms
├── searching/     search algorithms
├── sorting/       sorting algorithms
├── stacks/        stack implementations
└── trees/         tree ADTs (BST, Trie, Suffix, etc.)

src/test/java/org/drozdek/
└── (mirrors main structure, 82 test files)
```

## License

MIT
