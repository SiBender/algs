package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FullGraph {

    private static Map<Integer, Set<Integer>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);

            if (from != to) {
                put(graph, from, to);
                put(graph, to, from);
            }

        }


        boolean isGraphFull = isGraphFull(vertexCount);

        System.out.println(isGraphFull ? "YES" : "NO");
    }

    private static boolean isGraphFull(int vertexCount) {
        if (vertexCount <= 1) {
            return true;
        }


        if (graph.size() < vertexCount) {
            return false;
        }

        int neededEdgesCount = vertexCount - 1;
        for (Set<Integer> edges : graph.values()) {
            if (edges.size() < neededEdgesCount) {
                return false;
            }
        }

        return true;
    }


    private static void put(Map<Integer, Set<Integer>> graph, int from, int to) {
        if (graph.containsKey(from)) {
            graph.get(from).add(to);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(to);
            graph.put(from, set);
        }
    }
}
