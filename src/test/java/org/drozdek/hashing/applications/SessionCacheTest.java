package org.drozdek.hashing.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionCacheTest {

    @Test
    @DisplayName("Registers and resolves session tokens")
    void register_resolves() {
        SessionCache cache = new SessionCache();
        cache.register("abc", 42L);
        assertTrue(cache.contains("abc"));
        assertEquals(42L, cache.userId("abc"));
        assertFalse(cache.contains("missing"));
        assertNull(cache.userId("missing"));
    }

    @Test
    @DisplayName("Revoking a session removes it")
    void revoke_removes() {
        SessionCache cache = new SessionCache();
        cache.register("abc", 42L);
        assertEquals(1, cache.activeSessions());
        assertEquals(42L, cache.revoke("abc"));
        assertEquals(0, cache.activeSessions());
        assertNull(cache.revoke("abc"));
    }
}
