package org.drozdek.hashing.applications;

import org.drozdek.hashing.OpenAddressingHashTable;

/// In-memory session cache backed by an open-addressing hash table.
///
/// Session tokens map to user identifiers through a linear-probing hash table,
/// giving flat, cache-friendly lookups with no per-entry linked buckets.
///
/// **Real-world use case:** Web-session stores, API token indexes, and
/// interning caches where a low-overhead flat table is preferred.
///
/// Complexity Analysis:
/// Time Complexity: O(1) average for register, lookup, and revoke
/// Auxiliary Space: O(n) for the open-addressing table
///
/// Bibliography:
///
/// - Open addressing. *Wikipedia*. https://en.wikipedia.org/wiki/Open_addressing
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
///
/// @see OpenAddressingHashTable
public class SessionCache {

    private final OpenAddressingHashTable<String, Long> index;

    /// Creates a session cache with the default capacity.
    public SessionCache() {
        this.index = new OpenAddressingHashTable<>();
    }

    /// Creates a session cache with the given initial capacity.
    ///
    /// @param capacity initial number of slots
    public SessionCache(int capacity) {
        this.index = new OpenAddressingHashTable<>(capacity);
    }

    /// Registers a session token for a user.
    ///
    /// @param token  session token
    /// @param userId associated user identifier
    public void register(String token, long userId) {
        index.put(token, userId);
    }

    /// Returns whether a session token is active.
    ///
    /// @param token session token
    /// @return true if present
    public boolean contains(String token) {
        return index.containsKey(token);
    }

    /// Returns the user identifier for a session token, or null if absent.
    ///
    /// @param token session token
    /// @return user id or null
    public Long userId(String token) {
        return index.get(token);
    }

    /// Revokes a session token, returning its user id, or null if absent.
    ///
    /// @param token session token
    /// @return the revoked user id or null
    public Long revoke(String token) {
        return index.remove(token);
    }

    /// Returns the number of active sessions.
    ///
    /// @return active session count
    public int activeSessions() {
        return index.size();
    }
}
