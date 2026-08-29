/// Dynamic-memory management: allocation strategies for variable-size blocks
/// and garbage-collection algorithms for reclaiming unreachable memory.
///
/// ## Real-world use case
/// These algorithms underlie memory managers in languages without automatic
/// storage (C's malloc heap), buddy allocators in the Linux kernel, and the
/// mark-and-sweep collectors in JVM/.NET runtimes.
///
/// ## Contents
/// - `SequentialFitAllocator` — first-fit / best-fit / worst-fit placement
/// - `BuddySystemAllocator` — power-of-two block splitting and coalescing
/// - `MarkAndSweepCollector` — reachability-based reclamation
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 12.
/// - D.E. Knuth. *The Art of Computer Programming*, Vol. 1 (buddy systems).
///
/// @since 1.3
package org.drozdek.memory;
