package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ConnectedComponents {

    private static final Set<Integer> EMPTY_SET = new HashSet<>();

    private static Map<Integer, Set<Integer>> graph;
    private static int[] colors;
    private static Stack<Integer> stack = new Stack<>();


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        colors = new int[vertexCount + 1];
        for (int i = 1; i <= vertexCount; i++) {
            colors[i] = -1;
        }


        graph = new HashMap<>();
        for (int i = 0; i < edgeCount; i++) {
            String[] deges = reader.readLine().split(" ");
            int from = Integer.parseInt(deges[0]);
            int to = Integer.parseInt(deges[1]);
            put(graph, from, to);
            put(graph, to, from);
        }

        int componentColor = 1;
        DFS(1, componentColor);

        while (true) {
            int next = getNextUnvisited();
            if (next == -1) {
                break;
            }

            componentColor++;
            DFS(next, componentColor);
        }


        System.out.println(buildResultString());
    }

    private static String buildResultString() {
        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int i = 1; i < colors.length; i++) {
            if (groups.containsKey(colors[i])) {
                groups.get(colors[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                groups.put(colors[i], list);
            }
        }

        Set<List<Integer>> sortedGroups = new TreeSet<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return Integer.compare(o1.getFirst(), o2.getFirst());
            }
        });

        groups.values().forEach(sortedGroups::add);

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add(String.valueOf(groups.size()));

        for (List<Integer> group : sortedGroups) {
            joiner.add(group.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        return joiner.toString();
    }

    private static void DFS(Integer startVertex, int componentColor) {
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            if (colors[current] == -1) {
                colors[current] = 0;
                stack.push(current);

                for (Integer neighbor : graph.getOrDefault(current, EMPTY_SET)) {
                    if (colors[neighbor] == -1) {
                        stack.push(neighbor);
                    }
                }

            } else if (colors[current] == 0) {
                colors[current] = componentColor;
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
        for (int i = 1; i < colors.length; i++) {
            if (colors[i] == -1) {
                return i;
            }
        }

        return -1;
    }
}
