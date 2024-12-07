package org.example.Levit;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    public List<Edge> getEdges(int vertex) {
        return adjacencyList.get(vertex);
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }
}