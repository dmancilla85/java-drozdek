package org.drozdek.graphs.applications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.drozdek.graphs.DirectedAcyclicGraph;

/// Build system dependency resolver utilizing a Directed Acyclic Graph (`DirectedAcyclicGraph`)
/// to determine valid compilation or task execution orders and prevent cyclic dependencies.
///
/// In modern build tools (Maven, Gradle, Make, Webpack), modules depend on other
/// libraries or compile outputs. Dependencies form a directed graph where an edge
/// from A to B indicates that A must be built before B. Resolving this graph via
/// topological sorting produces a linear execution schedule. If a circular
/// dependency is introduced (e.g., A -> B -> A), the DAG detects it immediately.
///
/// **Real-world use case:** Build system orchestration (Maven plugin lifecycle,
/// Gradle task graph), software package managers (apt, npm, Maven Central
/// transitive dependency trees), spreadsheet formula recalculation order,
/// and academic curriculum prerequisite planning.
///
/// Complexity Analysis:
/// Time Complexity: O(V + E) for adding dependencies and topological ordering
/// Auxiliary Space: O(V) for node mapping and graph storage
///
/// @see DirectedAcyclicGraph
public class BuildDependencyResolver {

  private final int maxModules;
  private final Map<String, Integer> moduleToId;
  private final Map<Integer, String> idToModule;
  private final DirectedAcyclicGraph dag;
  private int nextId;

  /// Constructs a dependency resolver for up to `maxModules` distinct modules.
  ///
  /// @param maxModules maximum capacity of distinct module names
  /// @throws IllegalArgumentException if maxModules is less than or equal to 0
  public BuildDependencyResolver(int maxModules) {
    if (maxModules <= 0) {
      throw new IllegalArgumentException("maxModules must be positive");
    }
    this.maxModules = maxModules;
    this.moduleToId = new HashMap<>();
    this.idToModule = new HashMap<>();
    this.dag = new DirectedAcyclicGraph(maxModules);
    this.nextId = 0;
  }

  /// Registers a module name into the resolver if not already present.
  ///
  /// @param moduleName name of the module or task
  /// @return integer vertex identifier assigned to the module
  /// @throws IllegalStateException    if maxModules capacity is exceeded
  /// @throws IllegalArgumentException if moduleName is null or blank
  public int registerModule(String moduleName) {
    if (moduleName == null || moduleName.isBlank()) {
      throw new IllegalArgumentException("moduleName cannot be null or blank");
    }
    String cleanName = moduleName.trim();
    if (moduleToId.containsKey(cleanName)) {
      return moduleToId.get(cleanName);
    }
    if (nextId >= maxModules) {
      throw new IllegalStateException("Maximum module capacity (" + maxModules + ") reached");
    }
    int id = nextId++;
    moduleToId.put(cleanName, id);
    idToModule.put(id, cleanName);
    return id;
  }

  /// Adds a dependency constraint specifying that `dependsOn` must be built
  /// before `module` can proceed.
  ///
  /// @param module    the dependent module
  /// @param dependsOn the prerequisite module that must build first
  /// @throws IllegalStateException    if adding this dependency creates a cycle
  /// @throws IllegalArgumentException if either parameter is null or blank
  public void addDependency(String module, String dependsOn) {
    int targetId = registerModule(module);
    int sourceId = registerModule(dependsOn);

    if (sourceId == targetId) {
      throw new IllegalStateException("Self-dependency cycle detected on module: " + module);
    }

    boolean success = dag.createArc(sourceId, targetId);
    if (!success) {
      throw new IllegalStateException(
          "Circular dependency detected between '" + dependsOn + "' and '" + module + "'");
    }
  }

  /// Computes a valid linear build order where all prerequisite modules appear
  /// before the modules that depend on them.
  ///
  /// @return ordered list of module names to execute sequentially
  /// @throws IllegalStateException if a cycle is detected preventing complete order
  public List<String> resolveBuildOrder() {
    List<Integer> sortedIds = dag.topologicalSort();
    List<String> buildOrder = new ArrayList<>();

    for (int id : sortedIds) {
      if (idToModule.containsKey(id)) {
        buildOrder.add(idToModule.get(id));
      }
    }

    if (buildOrder.size() < nextId) {
      throw new IllegalStateException("Incomplete build order due to unresolved cycle");
    }

    return buildOrder;
  }

  /// Checks if a module is registered in the resolver.
  ///
  /// @param moduleName name of the module
  /// @return true if registered
  public boolean hasModule(String moduleName) {
    if (moduleName == null) {
      return false;
    }
    return moduleToId.containsKey(moduleName.trim());
  }

  /// Returns the number of currently registered modules.
  ///
  /// @return module count
  public int getModuleCount() {
    return nextId;
  }
}
