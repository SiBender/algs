package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NeighborVertexList {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        Map<Integer, Set<Integer>> graph = new TreeMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = 1; i <= vertexCount; i++) {
            result.add(toNeighborsList(graph.get(i)));
        }
        System.out.println(result);

    }

    private static String toNeighborsList(Set<Integer> value) {
        if (value == null || value.isEmpty()) {
            return "0";
        }

        return value.size() + " " + value.stream().map(String::valueOf).collect(Collectors.joining(" "));

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
