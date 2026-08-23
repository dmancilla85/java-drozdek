package org.drozdek.graphs.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BuildDependencyResolver Tests")
class BuildDependencyResolverTest {

  private BuildDependencyResolver resolver;

  @BeforeEach
  void setUp() {
    resolver = new BuildDependencyResolver(10);
  }

  @Test
  @DisplayName("Constructor parameter validation")
  void testConstructorValidation() {
    assertThrows(IllegalArgumentException.class, () -> new BuildDependencyResolver(0));
    assertThrows(IllegalArgumentException.class, () -> new BuildDependencyResolver(-5));
  }

  @Test
  @DisplayName("Module registration and capacity limits")
  void testModuleRegistration() {
    assertEquals(0, resolver.getModuleCount());
    int id1 = resolver.registerModule("core");
    int id2 = resolver.registerModule("api");
    int id1Dup = resolver.registerModule("core");

    assertEquals(0, id1);
    assertEquals(1, id2);
    assertEquals(id1, id1Dup);
    assertEquals(2, resolver.getModuleCount());
    assertTrue(resolver.hasModule("core"));
    assertTrue(resolver.hasModule("api"));
    assertFalse(resolver.hasModule("ui"));

    assertThrows(IllegalArgumentException.class, () -> resolver.registerModule(null));
    assertThrows(IllegalArgumentException.class, () -> resolver.registerModule("   "));
  }

  @Test
  @DisplayName("Linear dependency chain resolution (A -> B -> C)")
  void testLinearDependencyChain() {
    // app depends on service, service depends on core
    resolver.addDependency("service", "core");
    resolver.addDependency("app", "service");

    List<String> order = resolver.resolveBuildOrder();
    assertEquals(3, order.size());

    int coreIdx = order.indexOf("core");
    int serviceIdx = order.indexOf("service");
    int appIdx = order.indexOf("app");

    assertTrue(coreIdx < serviceIdx, "core must build before service");
    assertTrue(serviceIdx < appIdx, "service must build before app");
  }

  @Test
  @DisplayName("Diamond dependency resolution (A -> B, A -> C, B & C -> D)")
  void testDiamondDependencyResolution() {
    // core is prerequisite for auth and db; web depends on both auth and db
    resolver.addDependency("auth", "core");
    resolver.addDependency("db", "core");
    resolver.addDependency("web", "auth");
    resolver.addDependency("web", "db");

    List<String> order = resolver.resolveBuildOrder();
    assertEquals(4, order.size());

    int coreIdx = order.indexOf("core");
    int authIdx = order.indexOf("auth");
    int dbIdx = order.indexOf("db");
    int webIdx = order.indexOf("web");

    assertTrue(coreIdx < authIdx);
    assertTrue(coreIdx < dbIdx);
    assertTrue(authIdx < webIdx);
    assertTrue(dbIdx < webIdx);
  }

  @Test
  @DisplayName("Self dependency cycle detection")
  void testSelfDependencyCycle() {
    assertThrows(IllegalStateException.class, () -> resolver.addDependency("core", "core"));
  }

  @Test
  @DisplayName("Circular dependency detection (A -> B -> A)")
  void testDirectCircularDependency() {
    resolver.addDependency("api", "core");
    assertThrows(IllegalStateException.class, () -> resolver.addDependency("core", "api"));
  }

  @Test
  @DisplayName("Indirect circular dependency detection (A -> B -> C -> A)")
  void testIndirectCircularDependency() {
    resolver.addDependency("b", "a");
    resolver.addDependency("c", "b");
    assertThrows(IllegalStateException.class, () -> resolver.addDependency("a", "c"));
  }
}
