# AGENTS.md

## Build & test

```bash
mvn clean package           # full build + tests + coverage report
mvn test                    # run all tests (854)
mvn test -Dtest=FooTest     # single test class
mvn validate                # checkstyle (Google Checks, fails on error)
mvn jacoco:report           # coverage → target/site/jacoco/
mvn sonar:sonar -Dsonar.token=$TOKEN
```

Checkstyle runs at `validate` phase — `mvn package` will fail style errors.

JaCoCo report auto-generates at `test` phase (no separate step needed for local runs).

## Project

Single-module Maven project. Java 25, JUnit Jupiter 6.0.3, JaCoCo 0.8.14. Two dependencies: JUnit (test) and `moby-names-generator` (SuffixTree uses it for random names).

Source mirrors under `src/test/java/org/drozdek/`. 88 test files, 854 tests, 90% instruction coverage.

Branch is `master`, not `main`.

## Packages

| src/main/org/drozdek/ | Contains |
|---|---|
| `sorting/` `searching/` | Algorithm implementations (static utility classes, most with private constructors) |
| `trees/` + `trees/applications/` | 15 tree types + `nodes/` subpackage + prefix auto-complete application |
| `lists/` + `lists/applications/` | Linked list variants + `nodes/`, `iterators/` + music playlist application |
| `dynamic/` | Knapsack + task scheduling |
| `hashing/` + `hashing/applications/` | Hash table with separate chaining + user session store application |
| `graphs/` + `graphs/algorithms/` + `graphs/applications/` | Graph ADT, algorithm suites, and build dependency resolver application |
| `stacks/` + `stacks/applications/` | Stack implementations + balanced bracket validator application |
| `queues/` + `queues/unlam/` + `queues/applications/` | Queue implementations + FIFO print spooler application |
| `recursion/` | Recursive algorithms |
| `commons/` | `LoggerService`, `ArrayUtils` |

## Skills

The following specialized skills are integrated and should be utilized for ongoing development and maintenance:
- `acquire-codebase-knowledge` — Codebase onboarding, architecture discovery, and structural mapping.
- `java-architect` — Enterprise Java architecture, domain design, and clean code practices.
- `java-docs` — Modern Javadoc best practices and JEP-467 markdown documentation standards.
- `java-junit` — JUnit 5 unit testing, parameterized testing (`@ParameterizedTest`), and assertion patterns.
- `maven` — Maven lifecycle management, plugin configurations, and dependency resolution.

## Conventions

- `LoggerService.logInfo/Error` is the logger (wraps `System.out`). Do not use `System.out` directly.
- Some classes expose a static `test()` method as a manual entry point — not called by unit tests.
- All algorithms are in utility classes with `private` constructors. Trees and data structures use instance methods.
- Google Checks style (120 char lines, 2-space indent, Javadoc on public methods).
- `@SuppressWarnings("java:S...")` is used for SonarQube overrides on complex methods.
- No databases, no external services, no Docker — pure in-memory Java algorithms.

## Documentation Conventions

- All public APIs use modern markdown doc comments (`///`, JEP-467) — no classic `/** */`.
- Class-level docs follow the template: description, **Real-world use case**, **Complexity Analysis** (Time Complexity / Auxiliary Space), optional `@see`.
- Every package has a `package-info.java`.
- Method-level docs are required for all API-relevant public/protected members; trivial getters/setters and restating `@Override`s are exempt.
- Run `mvn javadoc:javadoc` to generate HTML; `mvn validate` enforces checkstyle (120-char lines, 2-space indent).

## Quality Gate Status

SonarCloud quality gate: **PASSED** (rating A across Reliability, Security, Maintainability; 0 open issues, 84.9% coverage, 2.9% duplication, 100% hotspots reviewed).

## Coverage

90% target (instruction). Lowest coverage today: `BruteForceClosestPair` (~16%), `WordSplay` (~74%), `RedBlackTree` (~78%), plus 0%-covered node classes (`DoubleLinkedListNode`, `SingleLinkedListNode`, `IntSkipListNode`) — the highest-ROI targets for new tests.
