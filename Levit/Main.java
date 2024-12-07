package org.example.Levit;

public class Main {
    public static void main(String[] args) {
        // Создаем граф
        Graph graph = new Graph(5);

        // Добавление рёбер
        graph.addEdge(0, 1, 7); // Ребро 0 -> 1 с весом 7
        graph.addEdge(0, 2, 5); // Ребро 0 -> 2 с весом 5
        graph.addEdge(1, 2, 3); // Ребро 1 -> 2 с весом 3
        graph.addEdge(1, 3, 5); // Ребро 1 -> 3 с весом 5
        graph.addEdge(2, 4, 4); // Ребро 2 -> 4 с весом 4
        graph.addEdge(3, 4, 6); // Ребро 3 -> 4 с весом 6

        // Запуск алгоритма Левита
        int start = 0; // Начальная вершина
        int[] distances = LevitAlgorithm.findShortestPaths(graph, start);

        // Вывод результатов
        System.out.println("Минимальные расстояния от вершины " + start + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("До вершины " + i + ": " + (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
    }
}
