package org.example.Dijkstra;

import java.util.*;

public class DijkstraWithBuckets {

    public static Map<Integer, Integer> dijkstraWithBuckets(Graph graph, int start) {
        // Инициализация расстояний
        Map<Integer, Integer> distances = new HashMap<>();
        for (int node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Определение максимального веса рёбер
        int maxDistance = 0;
        for (int node : graph.getNodes()) {
            for (Map.Entry<Integer, Integer> entry : graph.getNeighbors(node).entrySet()) {
                maxDistance = Math.max(maxDistance, entry.getValue());
            }
        }

        // Создание черпаков
        Map<Integer, Bucket> buckets = new TreeMap<>();
        for (int i = 0; i <= maxDistance; i++) {
            buckets.put(i, new Bucket(i));
        }

        // Добавление стартовой вершины в соответствующий черпак
        buckets.get(0).addNode(start);

        while (buckets.values().stream().anyMatch(bucket -> !bucket.isEmpty())) {
            for (Map.Entry<Integer, Bucket> entry : buckets.entrySet()) {
                int distance = entry.getKey();
                Bucket bucket = entry.getValue();

                if (!bucket.isEmpty()) {
                    int currentNode = bucket.extractMin();
                    int currentDistance = distance;

                    // Обход соседних вершин
                    for (Map.Entry<Integer, Integer> neighborEntry : graph.getNeighbors(currentNode).entrySet()) {
                        int neighbor = neighborEntry.getKey();
                        int weight = neighborEntry.getValue();
                        int newDistance = currentDistance + weight;

                        if (newDistance < distances.get(neighbor)) {
                            distances.put(neighbor, newDistance);

                            // Добавляем соседнюю вершину в соответствующий черпак
                            if (newDistance <= maxDistance) {
                                buckets.get(newDistance).addNode(neighbor);
                            }
                        }
                    }
                    break;
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        // Добавляем рёбра в граф
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 6);

        // Запуск алгоритма Дейкстры с использованием черпаков
        int startNode = 0;
        Map<Integer, Integer> distances = dijkstraWithBuckets(graph, startNode);

        // Вывод результатов
        System.out.println("Кратчайшие расстояния от стартовой вершины:");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("До вершины " + entry.getKey() + ": " + entry.getValue());
        }
        long endTime = System.currentTimeMillis();

    }

}
