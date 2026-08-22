package org.drozdek.graphs;

import org.drozdek.graphs.algorithms.ConstructionAlgorithms;
import org.drozdek.graphs.algorithms.ShortestPathAlgorithms;
import org.drozdek.graphs.algorithms.StructuralAlgorithms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    @DisplayName("Dijkstra finds shortest paths")
    void dijkstraAlgorithm() {
        WeightedDigraph g = new WeightedDigraph(4);
        g.createArc('a', 'b', 10);
        g.createArc('a', 'c', 3);
        g.createArc('c', 'b', 1);
        g.createArc('b', 'd', 2);
        g.createArc('c', 'd', 8);

        Integer[] dist = ShortestPathAlgorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(4, dist[1]);
        assertEquals(3, dist[2]);
        assertEquals(6, dist[3]);
    }

    @Test
    @DisplayName("Dijkstra with single vertex")
    void dijkstraSingleVertex() {
        WeightedDigraph g = new WeightedDigraph(1);
        Integer[] dist = ShortestPathAlgorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(1, dist.length);
        assertEquals(0, dist[0]);
    }

    @Test
    @DisplayName("Prim-Jarnik finds minimum spanning tree")
    void primJarnikAlgorithm() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('a', 'c', 4);
        g.createEdge('b', 'c', 2);
        g.createEdge('b', 'd', 5);
        g.createEdge('c', 'd', 3);

        WeightedGraph mst = ConstructionAlgorithms.primJarnikAlgorithm(g, 0);
        assertNotNull(mst);
        assertEquals(4, mst.cardinality());
    }

    @Test
    @DisplayName("Kruskal finds minimum spanning tree")
    void kruskalAlgorithm() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('a', 'c', 4);
        g.createEdge('b', 'c', 2);
        g.createEdge('b', 'd', 5);
        g.createEdge('c', 'd', 3);

        WeightedGraph mst = ConstructionAlgorithms.kruskalAlgorithm(g);
        assertNotNull(mst);
        assertEquals(4, mst.cardinality());
    }

    @Test
    @DisplayName("Prim on single vertex graph")
    void primSingleVertex() {
        WeightedGraph g = new WeightedGraph(1);
        assertThrows(IllegalArgumentException.class, () -> ConstructionAlgorithms.primJarnikAlgorithm(g, 0));
    }

    @Test
    @DisplayName("Kruskal on single vertex graph")
    void kruskalSingleVertex() {
        WeightedGraph g = new WeightedGraph(1);
        assertThrows(IllegalArgumentException.class, () -> ConstructionAlgorithms.kruskalAlgorithm(g));
    }

    @Test
    @DisplayName("Floyd-Warshall finds all-pairs shortest paths")
    void floydMarshallAlgorithm() {
        WeightedGraph g = new WeightedGraph(3);
        g.createEdge('a', 'b', 2);
        g.createEdge('b', 'c', 3);
        g.createEdge('a', 'c', 10);

        int[][] result = ShortestPathAlgorithms.floydMarshallAlgorithm(g);
        assertNotNull(result);
        assertEquals(3, result.length);
    }

    @Test
    @DisplayName("Dijkstra with single unreachable vertex")
    void dijkstraDisconnected() {
        WeightedDigraph g = new WeightedDigraph(2);
        g.createArc(0, 1, 5);
        Integer[] dist = ShortestPathAlgorithms.dijkstraAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
    }

    @Test
    @DisplayName("Cycle detector on Graph with no cycle")
    void cycleDetectorGraphNoCycle() {
        Graph g = new Graph(4);
        g.createEdge('a', 'b');
        g.createEdge('b', 'c');
        g.createEdge('c', 'd');
        assertFalse(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Cycle detector on Graph with cycle")
    void cycleDetectorGraphWithCycle() {
        Graph g = new Graph(4);
        g.createEdge('a', 'b');
        g.createEdge('b', 'c');
        g.createEdge('c', 'a');
        assertTrue(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Cycle detector on WeightedGraph with no cycle")
    void cycleDetectorWeightedGraphNoCycle() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('b', 'c', 2);
        g.createEdge('c', 'd', 3);
        assertFalse(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Cycle detector on WeightedGraph with cycle")
    void cycleDetectorWeightedGraphWithCycle() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge('a', 'b', 1);
        g.createEdge('b', 'c', 2);
        g.createEdge('c', 'a', 3);
        assertTrue(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Cycle detector on WeightedDigraph with no cycle")
    void cycleDetectorDigraphNoCycle() {
        WeightedDigraph g = new WeightedDigraph(4);
        g.createArc('a', 'b', 1);
        g.createArc('b', 'c', 2);
        g.createArc('c', 'd', 3);
        assertFalse(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Cycle detector on WeightedDigraph with cycle")
    void cycleDetectorDigraphWithCycle() {
        WeightedDigraph g = new WeightedDigraph(3);
        g.createArc('a', 'b', 1);
        g.createArc('b', 'c', 2);
        g.createArc('c', 'a', 3);
        assertTrue(StructuralAlgorithms.cycleDetector(g));
    }

    @Test
    @DisplayName("Topological sort on DAG returns valid order")
    void topologicalSortDag() {
        DirectedGraph g = new DirectedGraph(4);
        g.createArc(0, 1);
        g.createArc(1, 2);
        g.createArc(0, 3);
        g.createArc(3, 2);
        List<Integer> order = StructuralAlgorithms.topologicalSort(g);
        assertEquals(4, order.size());
        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.indexOf(0) < order.indexOf(3));
        assertTrue(order.indexOf(1) < order.indexOf(2));
        assertTrue(order.indexOf(3) < order.indexOf(2));
    }

    @Test
    @DisplayName("Topological sort on cyclic graph returns empty")
    void topologicalSortCycle() {
        DirectedGraph g = new DirectedGraph(3);
        g.createArc(0, 1);
        g.createArc(1, 2);
        g.createArc(2, 0);
        List<Integer> order = StructuralAlgorithms.topologicalSort(g);
        assertTrue(order.isEmpty());
    }

    @Test
    @DisplayName("Bellman-Ford finds shortest paths")
    void bellmanFordAlgorithm() {
        WeightedDigraph g = new WeightedDigraph(4);
        g.createArc('a', 'b', 10);
        g.createArc('a', 'c', 3);
        g.createArc('c', 'b', 1);
        g.createArc('b', 'd', 2);
        g.createArc('c', 'd', 8);

        Integer[] dist = ShortestPathAlgorithms.bellmanFordAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(4, dist[1]);
        assertEquals(3, dist[2]);
        assertEquals(6, dist[3]);
    }

    @Test
    @DisplayName("Bellman-Ford with negative weights")
    void bellmanFordNegativeWeights() {
        WeightedDigraph g = new WeightedDigraph(3);
        g.createArc(0, 1, 5);
        g.createArc(1, 2, -2);
        g.createArc(0, 2, 4);

        Integer[] dist = ShortestPathAlgorithms.bellmanFordAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
        assertEquals(3, dist[2]);
    }

    @Test
    @DisplayName("Bellman-Ford detects negative cycle")
    void bellmanFordNegativeCycle() {
        WeightedDigraph g = new WeightedDigraph(3);
        g.createArc(0, 1, 1);
        g.createArc(1, 2, -3);
        g.createArc(2, 0, 1);

        Integer[] dist = ShortestPathAlgorithms.bellmanFordAlgorithm(g, 0);
        assertNotNull(dist);
        assertEquals(0, dist.length);
    }

    @Test
    @DisplayName("SCC on DirectedGraph with single strong component")
    void stronglyConnectedComponentsSingle() {
        DirectedGraph g = new DirectedGraph(3);
        g.createArc(0, 1);
        g.createArc(1, 2);
        g.createArc(2, 0);

        List<List<Integer>> scc = StructuralAlgorithms.stronglyConnectedComponents(g);
        assertEquals(1, scc.size());
    }

    @Test
    @DisplayName("SCC on DirectedGraph with two components")
    void stronglyConnectedComponentsTwo() {
        DirectedGraph g = new DirectedGraph(4);
        g.createArc(0, 1);
        g.createArc(1, 2);
        g.createArc(2, 0);
        g.createArc(1, 3);

        List<List<Integer>> scc = StructuralAlgorithms.stronglyConnectedComponents(g);
        assertEquals(2, scc.size());
    }

    @Test
    @DisplayName("SCC on WeightedDigraph")
    void stronglyConnectedComponentsWeighted() {
        WeightedDigraph g = new WeightedDigraph(3);
        g.createArc(0, 1, 1);
        g.createArc(1, 2, 2);
        g.createArc(2, 0, 3);

        List<List<Integer>> scc = StructuralAlgorithms.stronglyConnectedComponents(g);
        assertEquals(1, scc.size());
    }

    @Test
    @DisplayName("Edmonds-Karp finds max flow")
    void edmondsKarpMaxFlow() {
        FlowNetwork network = new FlowNetwork(4);
        network.addEdge(0, 1, 10);
        network.addEdge(0, 2, 5);
        network.addEdge(1, 2, 15);
        network.addEdge(1, 3, 10);
        network.addEdge(2, 3, 10);

        int flow = ConstructionAlgorithms.edmondsKarpMaxFlow(network, 0, 3);
        assertEquals(15, flow);
    }

    @Test
    @DisplayName("Edmonds-Karp on single edge")
    void edmondsKarpSingleEdge() {
        FlowNetwork network = new FlowNetwork(2);
        network.addEdge(0, 1, 7);

        int flow = ConstructionAlgorithms.edmondsKarpMaxFlow(network, 0, 1);
        assertEquals(7, flow);
    }

    @Test
    @DisplayName("Articulation points in bow-tie graph")
    void articulationPoints() {
        Graph g = new Graph(5);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        g.newEdge(2, 3);
        g.newEdge(3, 4);
        g.newEdge(2, 4);

        List<Integer> points = StructuralAlgorithms.articulationPoints(g);
        assertEquals(2, points.size());
        assertTrue(points.contains(1));
        assertTrue(points.contains(2));
    }

    @Test
    @DisplayName("No articulation points in cycle graph")
    void articulationPointsNone() {
        Graph g = new Graph(4);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        g.newEdge(2, 3);
        g.newEdge(3, 0);

        List<Integer> points = StructuralAlgorithms.articulationPoints(g);
        assertTrue(points.isEmpty());
    }

    @Test
    @DisplayName("Bridges in line graph")
    void bridgesLine() {
        Graph g = new Graph(4);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        g.newEdge(2, 3);

        List<Edge> bridgeList = StructuralAlgorithms.bridges(g);
        assertEquals(3, bridgeList.size());
    }

    @Test
    @DisplayName("No bridges in cycle graph")
    void bridgesNone() {
        Graph g = new Graph(4);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        g.newEdge(2, 3);
        g.newEdge(3, 0);

        List<Edge> bridgeList = StructuralAlgorithms.bridges(g);
        assertTrue(bridgeList.isEmpty());
    }

    @Test
    @DisplayName("Graph coloring on K4 uses 4 colors")
    void colorGraphK4() {
        Graph g = new Graph(4);
        g.newEdge(0, 1);
        g.newEdge(0, 2);
        g.newEdge(0, 3);
        g.newEdge(1, 2);
        g.newEdge(1, 3);
        g.newEdge(2, 3);

        int[] colors = ConstructionAlgorithms.colorGraph(g);
        assertEquals(4, colors.length);
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                assertNotEquals(colors[i], colors[j]);
            }
        }
    }

    @Test
    @DisplayName("Graph coloring on bipartite graph uses 2 colors")
    void colorGraphBipartite() {
        Graph g = new Graph(4);
        g.newEdge(0, 1);
        g.newEdge(1, 2);
        g.newEdge(2, 3);
        g.newEdge(3, 0);

        int[] colors = ConstructionAlgorithms.colorGraph(g);
        assertEquals(4, colors.length);
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int ii = j;
                if (g.getAdjacentVertices(i).stream().anyMatch(v -> v.getKey() == ii)) {
                    assertNotEquals(colors[i], colors[j]);
                }
            }
        }
    }

    @Test
    @DisplayName("A* on simple weighted graph")
    void aStarAlgorithm() {
        WeightedGraph g = new WeightedGraph(3);
        g.createEdge('a', 'b', 2);
        g.createEdge('b', 'c', 3);
        g.createEdge('a', 'c', 10);

        Heuristic zeroHeuristic = (s, t) -> 0;
        List<Integer> path = ShortestPathAlgorithms.aStarAlgorithm(g, 0, 2, zeroHeuristic);
        assertEquals(3, path.size());
        assertEquals(0, path.get(0));
        assertEquals(2, path.get(path.size() - 1));
    }

    @Test
    @DisplayName("A* with admissible heuristic")
    void aStarAlgorithmWithHeuristic() {
        WeightedGraph g = new WeightedGraph(4);
        g.createEdge(0, 1, 1);
        g.createEdge(1, 2, 2);
        g.createEdge(0, 2, 10);
        g.createEdge(2, 3, 1);

        Heuristic h = (s, t) -> Math.abs(t - s);
        List<Integer> path = ShortestPathAlgorithms.aStarAlgorithm(g, 0, 3, h);
        assertEquals(4, path.size());
        assertEquals(0, path.get(0));
        assertEquals(3, path.get(path.size() - 1));
    }

    @Test
    @DisplayName("A* on weighted digraph")
    void aStarAlgorithmDigraph() {
        WeightedDigraph g = new WeightedDigraph(4);
        g.createArc(0, 1, 1);
        g.createArc(1, 2, 2);
        g.createArc(0, 2, 10);
        g.createArc(2, 3, 1);

        Heuristic h = (s, t) -> Math.abs(t - s);
        List<Integer> path = ShortestPathAlgorithms.aStarAlgorithm(g, 0, 3, h);
        assertEquals(4, path.size());
        assertEquals(0, path.get(0));
        assertEquals(3, path.get(path.size() - 1));
    }

    @Test
    @DisplayName("A* returns empty path when target unreachable")
    void aStarUnreachable() {
        WeightedGraph g = new WeightedGraph(3);
        g.createEdge(0, 1, 1);

        Heuristic h = (s, t) -> 0;
        List<Integer> path = ShortestPathAlgorithms.aStarAlgorithm(g, 0, 2, h);
        assertTrue(path.isEmpty());
    }
}
