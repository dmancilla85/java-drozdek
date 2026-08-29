package org.drozdek.hashing.applications;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.drozdek.hashing.HashTable;

/// Fast in-memory user authentication session store and token validator powered
/// by a Hash Table (`HashTable`) with separate chaining.
///
/// Web servers, APIs, and microservices validate bearer tokens on every inbound
/// HTTP request. By mapping cryptographic session tokens to user state within a
/// hash table, token lookups, TTL validation, and revocations execute in constant
/// O(1) expected time without database roundtrips.
///
/// **Real-world use case:** Web API bearer token authentication, OAuth2 session
/// caching, Redis-like in-memory session stores, single sign-on (SSO) token
/// validation, and shopping cart session state.
///
/// Complexity Analysis:
/// Time Complexity: O(1) expected average for session creation, validation,
///                  and revocation; O(n) worst-case during bucket collisions
/// Auxiliary Space: O(n) where n is the number of active sessions
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 10.
///
/// @see HashTable
public class UserSessionStore {

  private HashTable<String, UserSession> sessionTable;
  private final List<String> activeKeys;

  /// Creates a new, empty user session store.
  public UserSessionStore() {
    this.sessionTable = new HashTable<>();
    this.activeKeys = new ArrayList<>();
  }

  /// Generates a new authenticated session for a user with the specified time-to-live.
  ///
  /// @param userId        unique ID of the authenticated user
  /// @param username      username or email of the user
  /// @param ttlMillis     session duration in milliseconds
  /// @param currentMillis current timestamp in milliseconds
  /// @return generated unique session token
  /// @throws IllegalArgumentException if ttlMillis is not positive
  public String createSession(String userId, String username, long ttlMillis, long currentMillis) {
    if (ttlMillis <= 0) {
      throw new IllegalArgumentException("ttlMillis must be positive");
    }
    String sessionId = UUID.randomUUID().toString();
    long expiresAt = currentMillis + ttlMillis;
    UserSession session = new UserSession(sessionId, userId, username, currentMillis, expiresAt);
    sessionTable.put(sessionId, session);
    activeKeys.add(sessionId);
    return sessionId;
  }

  /// Retrieves an active session by token. If the session has expired relative
  /// to `currentMillis`, it is automatically purged and null is returned.
  ///
  /// @param sessionId     session token to look up
  /// @param currentMillis current timestamp in milliseconds
  /// @return the active UserSession, or null if missing or expired
  public UserSession getSession(String sessionId, long currentMillis) {
    if (sessionId == null || !sessionTable.containsKey(sessionId)) {
      return null;
    }
    UserSession session = sessionTable.get(sessionId);
    if (session == null) {
      return null;
    }
    if (session.isExpired(currentMillis)) {
      sessionTable.remove(sessionId);
      activeKeys.remove(sessionId);
      return null;
    }
    return session;
  }

  /// Checks whether a given session token is valid and unexpired.
  ///
  /// @param sessionId     session token
  /// @param currentMillis current timestamp in milliseconds
  /// @return true if valid and active
  public boolean isValid(String sessionId, long currentMillis) {
    return getSession(sessionId, currentMillis) != null;
  }

  /// Revokes (logs out) a specific session token immediately.
  ///
  /// @param sessionId token to revoke
  /// @return true if the session was found and removed; false otherwise
  public boolean revokeSession(String sessionId) {
    if (sessionId == null || !sessionTable.containsKey(sessionId)) {
      return false;
    }
    sessionTable.remove(sessionId);
    activeKeys.remove(sessionId);
    return true;
  }

  /// Revokes all active sessions for a given user (e.g. on password change).
  ///
  /// @param userId user ID whose sessions should be invalidated
  /// @return number of sessions revoked
  public int revokeAllUserSessions(String userId) {
    if (userId == null) {
      return 0;
    }
    List<String> toRevoke = new ArrayList<>();
    for (String key : activeKeys) {
      UserSession s = sessionTable.get(key);
      if (s != null && userId.equals(s.userId())) {
        toRevoke.add(key);
      }
    }
    for (String key : toRevoke) {
      sessionTable.remove(key);
      activeKeys.remove(key);
    }
    return toRevoke.size();
  }

  /// Scans and purges all expired sessions from the store.
  ///
  /// @param currentMillis current timestamp in milliseconds
  /// @return number of expired sessions cleaned up
  public int cleanExpiredSessions(long currentMillis) {
    List<String> expiredKeys = new ArrayList<>();
    for (String key : activeKeys) {
      UserSession s = sessionTable.get(key);
      if (s != null && s.isExpired(currentMillis)) {
        expiredKeys.add(key);
      }
    }
    for (String key : expiredKeys) {
      sessionTable.remove(key);
      activeKeys.remove(key);
    }
    return expiredKeys.size();
  }

  /// Returns the total number of stored session entries (including any unpurged expired ones).
  ///
  /// @return total session count
  public int size() {
    return sessionTable.size();
  }

  /// Clears all sessions from the store.
  public void clear() {
    this.sessionTable = new HashTable<>();
    this.activeKeys.clear();
  }
}
