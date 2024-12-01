package org.example.Dijkstra;

import java.util.*;

public class Bucket {

    private final int distance;
    private final List<Integer> nodes;

    public Bucket(int distance) {
        this.distance = distance;
        this.nodes = new ArrayList<>();
    }

    public void addNode(int node) {
        nodes.add(node);
    }

    public Integer extractMin() {
        if (!nodes.isEmpty()) {
            return nodes.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public int getDistance() {
        return distance;
    }

}
