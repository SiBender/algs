package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class DijxtraDistances {

    private static final Map<Integer, Integer> EMPTY_MAP = new HashMap<>();

    private static Map<Integer, Map<Integer, Integer>> graph;

    private static int[] distance;
    private static int[] visited;
    private static int[] previous;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        distance = new int[vertexCount + 1];
        visited = new int[vertexCount + 1];

        previous = new int[vertexCount + 1];


        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);

            int existingWeight = graph.getOrDefault(from, EMPTY_MAP).getOrDefault(to, -1);

            if (existingWeight == -1 || weight < existingWeight) {
                put(graph, from, to, weight);
                put(graph, to, from, weight);
            }

        }


        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = 1; i <= vertexCount; i++) {
            dijxtraAlg(i);
            result.add(distacesToString());
        }


        System.out.println(result);

    }

    private static String distacesToString() {
        StringJoiner line = new StringJoiner(" ");
        for (int i = 1; i < distance.length; i++) {
            line.add(String.valueOf(distance[i]));
        }
        return line.toString();
    }

    private static void resetDataArrays() {
        Arrays.fill(distance, -1);
        Arrays.fill(visited, -1);
    }

    private static void dijxtraAlg(Integer startVertex) {
        resetDataArrays();

        distance[startVertex] = 0;

        while (true) {
            int u = getClosestUnvisited();

            if (u == -1 || distance[u] == -1) {
                break;
            }

            visited[u] = 1;

            Set<Integer> neighbours = graph.getOrDefault(u, EMPTY_MAP).keySet();

            for (Integer neighborVertex : neighbours) {
                relax(u, neighborVertex);
            }
        }
    }

    private static int getClosestUnvisited() {
        int minDistance = -1;
        int minDistanceVertex = -1;

        for (int i = 1; i < distance.length; i++) {
            if (visited[i] == -1 && distance[i] >= 0) {

                if (minDistance == -1 || distance[i] < minDistance) {
                    minDistance = distance[i];
                    minDistanceVertex = i;
                }

            }
        }

        return minDistanceVertex;
    }

    private static void relax(int u, int v) {
        int weightUV = graph.get(u).get(v);
        if (distance[v] == -1 || distance[v] > distance[u] + weightUV) {
            distance[v] = distance[u] + weightUV;
            previous[v] = u;
        }
    }


    private static void put(Map<Integer, Map<Integer, Integer>> graph, int from, int to, int weight) {
        if (graph.containsKey(from)) {
            graph.get(from).put(to, weight);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(to, weight);
            graph.put(from, map);
        }
    }
}
