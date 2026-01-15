// https://contest.yandex.ru/contest/25070/run-report/155200976/

/**
 -- ПРИНЦИП РАБОТЫ --

 Для решения используется Алгоритм Прима, поиск минимального остовного дерева.
 Но т.к. по условию задачи нам нужно найти максимальную стоимость,
 при выборе следующей вершины вместо минимального веса ребра берем максимальный.

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 Алгоритм Прима (в отличии от алгоритма Дийксты) на каждой итерации дает окончательный ответ
 какая вершина и через какое ребро добавляется к остовному дереву.

 Поэтому при добавлении каждой следующей вершины достаточно просто увеличить общую стоимость на вес нового ребра.

 Так же известно, что корректность алгоритма не зависит от того с какой вершины он стартовал


  Оптимизация по времени (были проблемы с Time Limit)
    Поиск ребер с максимальным весом с каждой итерацией становится всё более затратным,
    т.к. нужно перебрать ВСЕ ребра из ВСЕХ уже добавленных в остовное дерево вершин.
    Для сокращения времени поиска ребра хранятся в упорядоченном виде от большего веса к меньшему
    и как только вес меньше или равен уже найденному поиск по вершине прерывается

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 V - количество вершин
 E - количество ребер

 Заполнение списка ребер
 в худшем случае все ребра относятся в одной вершине.
 А так как мы поддерживаем упорядоченность, сложность будет O(E*log(E))

 Удаление из списка не посещенных и добавление в список посещенных O(1)

 Поиск следующего максимального ребра....
 Попробую посчитать на примере графа в котором у каждой вершине одинаковое количество ребер

 допустим V = 8, и из каждой вершины по 4 ребра, E = 32

 Количество проверенных ребер на каждом шагу
 1 = 0
 2 = 4
 3 = 8
 4 = 12
 ...

 X = 0 + 4 + 8 + 12 + 16 + 20 + 24 + 28

 X = (E * (V - 1)) / 2 -> O(V * E)


 O(E*log(E)) + O(1) + O(V * E)
 т.к. E*log(E) значительно меньше V * E (V >> log(E))
 итоговая временная сложность O(V * E)

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
  Затраты памяти
 1) Список ребер O(E)
 2) Список не добавленных ребер
 3) Список добавленных ребер
 Сумма добавленных и не добавленных ребер всегда равна V
 2 + 3 = O(V)

 Итого общая пространственная сложность O(V + E)
 */

package net.bondarik.sprint06.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MaxSpanningTree {
    private static final Set<Edge> EMPTY_SET = new HashSet<>();
    private static final String ERROR_MESSAGE = "Oops! I did it again";

    private static Map<Integer, Set<Edge>> graph;

    private static Set<Integer> unvisitedVertexes = new HashSet<>();
    private static Set<Integer> visitedVertexes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        for (int i = 1; i <= vertexCount; i++) {
            unvisitedVertexes.add(i);
        }

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);

            put(from, to, weight);
            put(to, from, weight);
        }

        int maxWeight = maxSpanningTreeWeight(1);

        System.out.println(unvisitedVertexes.isEmpty() ? maxWeight : ERROR_MESSAGE);

    }

    private static int maxSpanningTreeWeight(int startVertex) {
        int totalWeight = 0;
        unvisitedVertexes.remove(startVertex);
        visitedVertexes.add(startVertex);

        while (true) {
            Edge maxDistanceEdge = getNextMaxEdge();

            if (maxDistanceEdge == null) {
                break;
            }

            totalWeight += maxDistanceEdge.getWeight();
            int maxDistanceVertex = maxDistanceEdge.getTo();

            unvisitedVertexes.remove(maxDistanceVertex);
            visitedVertexes.add(maxDistanceVertex);

        }

        return totalWeight;
    }

    private static Edge getNextMaxEdge() {
        int maxDistance = -1;
        int maxDistanceVertex = -1;

        for (Integer from : visitedVertexes) {
            for (Edge edge : graph.getOrDefault(from, EMPTY_SET)) {
                if (edge.getWeight() < maxDistance) {
                    break;
                }

                if (unvisitedVertexes.contains(edge.getTo())) {

                    if (edge.getWeight() > maxDistance) {
                        maxDistanceVertex = edge.getTo();
                        maxDistance = edge.getWeight();
                    }

                }
            }
        }


        if (maxDistance == -1) {
            return null;
        } else {
            return new Edge(maxDistanceVertex, maxDistance);
        }

    }

    private static void put(int from, int to, int weight) {
        Edge newEdge = new Edge(to, weight);

        if (graph.containsKey(from)) {
            graph.get(from).add(newEdge);
        } else {
            Set<Edge> set = new TreeSet<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    int compare = Integer.compare(o2.getWeight(), o1.getWeight());
                    if (compare != 0) {
                        return compare;
                    }
                    return Integer.compare(o1.getTo(), o2.getTo());
                }
            });
            set.add(newEdge);
            graph.put(from, set);
        }
    }
}

class Edge {
    private final int to;
    private final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}
