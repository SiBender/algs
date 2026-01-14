package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class BFS {
    private static final Set<Integer> EMPTY_MAP = new HashSet<>();
    private static final String WHITE = "white";
    private static final String GRAY = "gray";
    private static final String BLACK = "black";

    private static Map<Integer, Set<Integer>> graph;
    private static String[] vertexColors;
    private static int[] distance;
    private static int[] previous;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        distance = new int[vertexCount + 1];
        previous = new int[vertexCount + 1];

        vertexColors = new String[vertexCount + 1];
        Arrays.fill(vertexColors, WHITE);

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
            put(graph, to, from);
        }

        StringJoiner path = new StringJoiner(" ");
        int startVertex = Integer.parseInt(reader.readLine());

        BFS(startVertex, path);

        System.out.println(path);

    }

    private static void BFS(Integer startVertex, StringJoiner path) {
        Queue<Integer> vertexToVisit = new LinkedList<>();
        vertexColors[startVertex] = GRAY;
        vertexToVisit.add(startVertex);
        distance[startVertex] = 0;
        previous[startVertex] = -1;

        while (!vertexToVisit.isEmpty()) {
            Integer current = vertexToVisit.poll();
            path.add(String.valueOf(current));
            int nextDistance = distance[current] + 1;

            for (Integer neighbor : graph.getOrDefault(current, EMPTY_MAP)) {
                if (vertexColors[neighbor].equals(WHITE)) {
                    vertexColors[neighbor] = GRAY;
                    vertexToVisit.add(neighbor);
                    distance[neighbor] = nextDistance;
                    previous[startVertex] = current;
                }
            }

            vertexColors[current] = BLACK;
        }
    }

    private static void put(Map<Integer, Set<Integer>> graph, int from, int to) {
        if (graph.containsKey(from)) {
            graph.get(from).add(to);
        } else {
            Set<Integer> set = new TreeSet<>();
            set.add(to);
            graph.put(from, set);
        }
    }
}
