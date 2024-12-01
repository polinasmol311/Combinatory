package org.example.Dijkstra;

import java.util.*;

public class Graph {

    private final Map<Integer, Map<Integer, Integer>> adjacencyList = new HashMap<>();

    public void addEdge(int u, int v, int weight) {
        adjacencyList.computeIfAbsent(u, k -> new HashMap<>()).put(v, weight);
        adjacencyList.computeIfAbsent(v, k -> new HashMap<>()).put(u, weight); // Для неориентированного графа
    }

    public Map<Integer, Integer> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, Collections.emptyMap());
    }

    public Set<Integer> getNodes() {
        return adjacencyList.keySet();
    }

}
