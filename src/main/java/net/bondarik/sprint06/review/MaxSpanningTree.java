// https://contest.yandex.ru/contest/25070/run-report/155165082/

/**
 -- ПРИНЦИП РАБОТЫ --

 -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

 -- ВРЕМЕННАЯ СЛОЖНОСТЬ --

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

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

    private static int totalWeight = 0;

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


        maxSpanningTree(1);

        System.out.println(unvisitedVertexes.isEmpty() ? totalWeight : ERROR_MESSAGE);

    }

    private static void maxSpanningTree(int startVertex) {
        unvisitedVertexes.remove(startVertex);
        visitedVertexes.add(startVertex);

        while (true) {
            Edge maxDistanceEdge = getMaxEdge();

            if (maxDistanceEdge == null) {
                break;
            }

            totalWeight += maxDistanceEdge.getWeight();
            int maxDistanceVertex = maxDistanceEdge.getTo();

            unvisitedVertexes.remove(maxDistanceVertex);
            visitedVertexes.add(maxDistanceVertex);

        }

    }

    private static Edge getMaxEdge() {
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
