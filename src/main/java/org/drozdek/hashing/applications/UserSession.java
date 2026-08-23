package org.drozdek.hashing.applications;

/// Immutable user authentication session record stored in an in-memory session store.
///
/// @param sessionId       unique session token / identifier
/// @param userId          unique user identity ID
/// @param username        display username or email
/// @param createdAtMillis creation timestamp in milliseconds
/// @param expiresAtMillis expiration timestamp in milliseconds
///
/// @since 1.3
public record UserSession(
    String sessionId,
    String userId,
    String username,
    long createdAtMillis,
    long expiresAtMillis) {

  /// Validates session parameters upon creation.
  public UserSession {
    if (sessionId == null || sessionId.isBlank()) {
      throw new IllegalArgumentException("sessionId cannot be null or blank");
    }
    if (userId == null || userId.isBlank()) {
      throw new IllegalArgumentException("userId cannot be null or blank");
    }
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("username cannot be null or blank");
    }
    if (expiresAtMillis <= createdAtMillis) {
      throw new IllegalArgumentException("expiresAtMillis must be greater than createdAtMillis");
    }
  }

  /// Checks if this session is expired relative to a given point in time.
  ///
  /// @param currentMillis timestamp in milliseconds to test against
  /// @return true if expired; false otherwise
  public boolean isExpired(long currentMillis) {
    return currentMillis >= expiresAtMillis;
  }
}
