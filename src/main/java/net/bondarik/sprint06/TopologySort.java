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

public class TopologySort {
    private static final Set<Integer> EMPTY_SET = new HashSet<>();

    private static Map<Integer, Set<Integer>> graph;
    private static Set<Integer> notVisitedVertexes = new HashSet<>();
    private static ColorT[] colors;
    private static Stack<Integer> stack = new Stack<>();

    private static Stack<Integer> result = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        colors = new ColorT[vertexCount + 1];
        for (int i = 1; i <= vertexCount; i++) {
            colors[i] = ColorT.WHITE;
            notVisitedVertexes.add(i);
        }


        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
        }

        DFS(1);

        while (!notVisitedVertexes.isEmpty()) {
            DFS(getNextUnvisited());
        }

        StringJoiner output = new StringJoiner(" ");
        while (!result.isEmpty()) {
            output.add(String.valueOf(result.pop()));
        }
        System.out.println(output);
    }

    private static void DFS(Integer startVertex) {
        stack.push(startVertex);
        notVisitedVertexes.remove(startVertex);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            if (colors[current].equals(ColorT.WHITE)) {
                colors[current] = ColorT.GREY;
                stack.push(current);

                for (Integer neighbor : graph.getOrDefault(current, EMPTY_SET)) {
                    if (colors[neighbor] == ColorT.WHITE) {
                        stack.push(neighbor);
                        notVisitedVertexes.remove(neighbor);
                    }
                }

            } else if (colors[current].equals(ColorT.GREY)) {
                colors[current] = ColorT.BLACK;
                result.push(current);
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

    private static int getNextUnvisited() {
        if (notVisitedVertexes.isEmpty()) {
            return -1;
        }

        int nextValue = notVisitedVertexes.iterator().next();
        notVisitedVertexes.remove(nextValue);
        return nextValue;
    }
}

enum ColorT {
    WHITE,
    GREY,
    BLACK,
}

