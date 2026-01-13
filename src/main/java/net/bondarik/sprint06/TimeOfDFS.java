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

public class TimeOfDFS {
    private static final Set<Integer> EMPTY_SET = new HashSet<>();

    private static Map<Integer, Set<Integer>> graph;
    private static VertexColor[] vertexColors;
    private static Stack<Integer> stack = new Stack<>();
    private static int[] entryTime;
    private static int[] exitTime;
    private static int timer = -1;



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        entryTime = new int[vertexCount + 1];
        exitTime = new int[vertexCount + 1];

        vertexColors = new VertexColor[vertexCount + 1];
        for (int i = 0; i < vertexColors.length; i++) {
            vertexColors[i] = VertexColor.WHITE;
        }

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
        }

        int startVertex = 1;



        DFS(startVertex);

        StringJoiner result = new StringJoiner(System.lineSeparator());

        for (int i = 1; i < entryTime.length; i++) {
            result.add("%d %d".formatted(entryTime[i], exitTime[i]));
        }
        System.out.println(result);

    }

    private static void DFS(Integer startVertex) {
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            if (vertexColors[current].equals(VertexColor.WHITE)) {
                timer++;
                vertexColors[current] = VertexColor.GREY;
                entryTime[current] = timer;
                stack.push(current);

                for (Integer neighbor : graph.getOrDefault(current, EMPTY_SET)) {
                    if (vertexColors[neighbor] == VertexColor.WHITE) {
                        stack.push(neighbor);
                    }
                }

            } else if (vertexColors[current].equals(VertexColor.GREY)) {
                vertexColors[current] = VertexColor.BLACK;
                timer++;
                exitTime[current] = timer;
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

enum VertexColor {
    WHITE,
    GREY,
    BLACK,
}
