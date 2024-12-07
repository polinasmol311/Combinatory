package org.example.Levit;

import java.util.*;

public class LevitAlgorithm {
    public static int[] findShortestPaths(Graph graph, int start) {
        int n = graph.getVertexCount();
        int[] dist = new int[n];        // Массив расстояний
        int[] prev = new int[n];        // Массив предыдущих вершин
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        // Множества вершин
        Queue<Integer> q = new LinkedList<>();        // Очередь для множества B
        Set<Integer> cSet = new HashSet<>();          // Множество C
        Set<Integer> aSet = new HashSet<>();          // Множество A

        for (int i = 0; i < n; i++) {
            if (i != start) {
                cSet.add(i);
            }
        }

        q.add(start); // Начальная вершина в очередь

        while (!q.isEmpty()) {
            int u = q.poll(); // Извлечение из очереди B
            if (aSet.contains(u)) {
                continue; // Пропустить, если вершина уже обработана
            }

            for (Edge edge : graph.getEdges(u)) {
                int v = edge.getTo();
                int weight = edge.getWeight();

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;

                    if (cSet.contains(v)) {
                        cSet.remove(v);
                        q.add(v); // Добавляем вершину в B
                    } else if (!aSet.contains(v)) {
                        q.add(v); // Обновляем приоритет
                    }
                }
            }
            aSet.add(u); // Переносим вершину из B в A
        }

        return dist;
    }
}

