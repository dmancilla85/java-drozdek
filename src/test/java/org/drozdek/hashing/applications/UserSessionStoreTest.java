package org.drozdek.hashing.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserSessionStore Tests")
class UserSessionStoreTest {

  private UserSessionStore sessionStore;
  private final long baseTime = 1_000_000L;

  @BeforeEach
  void setUp() {
    sessionStore = new UserSessionStore();
  }

  @Test
  @DisplayName("UserSession record validation")
  void testUserSessionValidation() {
    assertThrows(IllegalArgumentException.class,
        () -> new UserSession(null, "u1", "alice", 100, 200));
    assertThrows(IllegalArgumentException.class,
        () -> new UserSession("s1", null, "alice", 100, 200));
    assertThrows(IllegalArgumentException.class,
        () -> new UserSession("s1", "u1", null, 100, 200));
    assertThrows(IllegalArgumentException.class,
        () -> new UserSession("s1", "u1", "alice", 200, 100));
    assertThrows(IllegalArgumentException.class,
        () -> new UserSession("s1", "u1", "alice", 200, 200));

    UserSession valid = new UserSession("token-1", "user-42", "john", 100, 500);
    assertFalse(valid.isExpired(499));
    assertTrue(valid.isExpired(500));
    assertTrue(valid.isExpired(600));
  }

  @Test
  @DisplayName("Session creation, retrieval, and validation")
  void testCreateAndGetSession() {
    String token = sessionStore.createSession("user-1", "alice", 5000L, baseTime);
    assertNotNull(token);
    assertEquals(1, sessionStore.size());

    assertTrue(sessionStore.isValid(token, baseTime + 1000));
    UserSession session = sessionStore.getSession(token, baseTime + 1000);
    assertNotNull(session);
    assertEquals("user-1", session.userId());
    assertEquals("alice", session.username());

    assertThrows(IllegalArgumentException.class,
        () -> sessionStore.createSession("user-2", "bob", 0L, baseTime));
  }

  @Test
  @DisplayName("Expired session auto-purging on get")
  void testSessionExpiration() {
    String token = sessionStore.createSession("user-1", "alice", 1000L, baseTime);

    // Valid before expiration
    assertTrue(sessionStore.isValid(token, baseTime + 500));

    // Expired at and after baseTime + 1000
    assertFalse(sessionStore.isValid(token, baseTime + 1000));
    assertNull(sessionStore.getSession(token, baseTime + 1000));
    assertEquals(0, sessionStore.size());
  }

  @Test
  @DisplayName("Session revocation (logout)")
  void testRevokeSession() {
    String token = sessionStore.createSession("user-1", "alice", 5000L, baseTime);

    assertTrue(sessionStore.revokeSession(token));
    assertFalse(sessionStore.isValid(token, baseTime));
    assertFalse(sessionStore.revokeSession(token));
    assertFalse(sessionStore.revokeSession(null));
  }

  @Test
  @DisplayName("Revoke all sessions for a specific user")
  void testRevokeAllUserSessions() {
    String t1 = sessionStore.createSession("user-1", "alice", 5000L, baseTime);
    String t2 = sessionStore.createSession("user-1", "alice", 5000L, baseTime);
    String t3 = sessionStore.createSession("user-2", "bob", 5000L, baseTime);

    assertEquals(3, sessionStore.size());
    int revoked = sessionStore.revokeAllUserSessions("user-1");
    assertEquals(2, revoked);

    assertFalse(sessionStore.isValid(t1, baseTime));
    assertFalse(sessionStore.isValid(t2, baseTime));
    assertTrue(sessionStore.isValid(t3, baseTime));
    assertEquals(1, sessionStore.size());

    assertEquals(0, sessionStore.revokeAllUserSessions(null));
  }

  @Test
  @DisplayName("Batch cleaning of expired sessions")
  void testCleanExpiredSessions() {
    sessionStore.createSession("u1", "a", 1000L, baseTime); // exp 1_001_000
    sessionStore.createSession("u2", "b", 2000L, baseTime); // exp 1_002_000
    sessionStore.createSession("u3", "c", 5000L, baseTime); // exp 1_005_000

    assertEquals(3, sessionStore.size());
    int cleaned = sessionStore.cleanExpiredSessions(baseTime + 1500);
    assertEquals(1, cleaned);
    assertEquals(2, sessionStore.size());

    cleaned = sessionStore.cleanExpiredSessions(baseTime + 3000);
    assertEquals(1, cleaned);
    assertEquals(1, sessionStore.size());
  }

  @Test
  @DisplayName("Clear resets entire session store")
  void testClear() {
    sessionStore.createSession("u1", "a", 5000L, baseTime);
    sessionStore.createSession("u2", "b", 5000L, baseTime);
    sessionStore.clear();

    assertEquals(0, sessionStore.size());
  }
}
