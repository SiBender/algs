package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeSet;

public class DFS {
    private static Map<Integer, Set<Integer>> graph;
    private static Color[] vertexColors;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        vertexColors = new Color[vertexCount + 1];
        for (int i = 0; i < vertexColors.length; i++) {
            vertexColors[i] = Color.WHITE;
        }

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
            put(graph, to, from);
        }

        int startVertex = Integer.parseInt(reader.readLine());

        StringJoiner result = new StringJoiner(" ");

        DFS(startVertex, result);

        System.out.println(result);

    }

    private static void DFS(Integer startVertex, StringJoiner result) {
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (vertexColors[current].equals(Color.WHITE)) {
                result.add(String.valueOf(current));
                vertexColors[current] = Color.GREY;
                stack.push(current);
                for (Integer neighbor : graph.getOrDefault(current, new HashSet<>())) {
                    stack.push(neighbor);
                }
            } else if (vertexColors[current].equals(Color.GREY)) {
                vertexColors[current] = Color.BLACK;
            }

        }
    }

    private static void put(Map<Integer, Set<Integer>> graph, int from, int to) {
        if (graph.containsKey(from)) {
            graph.get(from).add(to);
        } else {
            Set<Integer> set = new TreeSet<>(Collections.reverseOrder());
            set.add(to);
            graph.put(from, set);
        }
    }
}


enum Color {
    WHITE,
    GREY,
    BLACK,
}