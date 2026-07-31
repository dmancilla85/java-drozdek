# AGENTS.md

## Build & test

```bash
mvn clean package           # full build + tests + coverage report
mvn test                    # run all tests (795)
mvn test -Dtest=FooTest     # single test class
mvn validate                # checkstyle (Google Checks, fails on error)
mvn jacoco:report           # coverage → target/site/jacoco/
mvn sonar:sonar -Dsonar.token=$TOKEN
```

Checkstyle runs at `validate` phase — `mvn package` will fail style errors.

JaCoCo report auto-generates at `test` phase (no separate step needed for local runs).

## Project

Single-module Maven project. Java 25, JUnit Jupiter 6.0.3, JaCoCo 0.8.14. Two dependencies: JUnit (test) and `moby-names-generator` (SuffixTree uses it for random names).

Source mirrors under `src/test/java/org/drozdek/`. 82 test files, 795 tests, 90% instruction coverage.

Branch is `master`, not `main`.

## Packages

| src/main/org/drozdek/ | Contains |
|---|---|
| `sorting/` `searching/` | Algorithm implementations (static utility classes, most with private constructors) |
| `trees/` | 15 tree types + `nodes/` subpackage for node classes |
| `lists/` | Linked list variants + `nodes/` and `iterators/` |
| `dynamic/` | Knapsack + task scheduling |
| `hashing/` | Hash table with separate chaining |
| `graphs/unlam/` | Graph ADT, algorithms (Dijkstra, Prim-Jarnik, Kruskal, Floyd-Warshall) |
| `stacks/` | Stack implementations |
| `queues/` + `queues/unlam/` | Queue implementations |
| `recursion/` | Recursive algorithms |
| `commons/` | `LoggerService`, `ArrayUtils` |

## Conventions

- `LoggerService.logInfo/Error` is the logger (wraps `System.out`). Do not use `System.out` directly.
- Some classes expose a static `test()` method as a manual entry point — not called by unit tests.
- All algorithms are in utility classes with `private` constructors. Trees and data structures use instance methods.
- Google Checks style (120 char lines, 2-space indent, Javadoc on public methods).
- `@SuppressWarnings("java:S...")` is used for SonarQube overrides on complex methods.
- No databases, no external services, no Docker — pure in-memory Java algorithms.

## Coverage

90% target (instruction). Lowest coverage today: `BruteForceClosestPair` (~16%), `RedBlackTree` (~77%), `Deque` (~76%), plus 0%-covered node classes (`DoubleLinkedListNode`, `SingleLinkedListNode`, `IntSkipListNode`) — the highest-ROI targets for new tests.
