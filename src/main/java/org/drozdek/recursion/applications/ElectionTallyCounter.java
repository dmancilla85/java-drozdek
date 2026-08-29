package org.drozdek.recursion.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.recursion.MajorityElement;

/// Election tally service that reports the majority candidate.
///
/// Votes are scanned with the recursive majority-element search to determine
/// whether any candidate received more than half the votes, and which one.
///
/// **Real-world use case:** Election result reporting, quorum detection, and
/// majority-based consensus in distributed systems.
///
/// Complexity Analysis:
/// Time Complexity: O(n^2) worst case for candidate rescans
/// Auxiliary Space: O(n) recursion depth
///
/// Bibliography:
///
/// - Boyer-Moore majority vote. *Wikipedia*. https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 5.
///
/// @see MajorityElement
public final class ElectionTallyCounter {

    private ElectionTallyCounter() {
        // do nothing
    }

    /// Returns the candidate with more than half the votes, or null if none.
    ///
    /// @param votes list of candidate ids cast in the election
    /// @return the majority candidate id, or null when no majority exists
    public static Integer majorityCandidate(List<Integer> votes) {
        return MajorityElement.run(new ArrayList<>(votes), 0);
    }
}
