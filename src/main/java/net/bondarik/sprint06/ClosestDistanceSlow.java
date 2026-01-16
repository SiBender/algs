package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClosestDistanceSlow {

    private static final Set<Integer> EMPTY_SET = new HashSet<>();

    private static Map<Integer, Set<Integer>> graph;

    private static int[] distance;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("C:\\_code\\algs\\src\\main\\resources\\6-F-tests_41.txt"));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        distance = new int[vertexCount + 1];
        visited = new int[vertexCount + 1];

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);

            put(from, to);
            put(to, from);
        }


        String[] fromTo = reader.readLine().split(" ");
        int from = Integer.parseInt(fromTo[0]);
        int to = Integer.parseInt(fromTo[1]);

        dijxtraAlg(from);

        System.out.println(distance[to]);
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

            Set<Integer> neighbours = graph.getOrDefault(u, EMPTY_SET);

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
        if (distance[v] == -1 || distance[v] > distance[u] + 1) {
            distance[v] = distance[u] + 1;
            //previous[v] = u;
        }
    }


    private static void put(int from, int to) {
        if (graph.containsKey(from)) {
            graph.get(from).add(to);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(to);
            graph.put(from, set);
        }
    }
}

