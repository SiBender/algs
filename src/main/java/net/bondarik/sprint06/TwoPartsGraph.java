package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoPartsGraph {
    private static final Set<Integer> EMPTY_SET = new HashSet<>();

    private static Map<Integer, Set<Integer>> graph;
    private static List<Integer> vertexesWithEdges = new ArrayList<>();

    private static int[] colors;

    private static int lastPainted = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        colors = new int[vertexCount + 1];

        graph = new HashMap<>();

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);


            if (from != to) {
                vertexesWithEdges.add(from);
                vertexesWithEdges.add(to);
                put(graph, from, to);
                put(graph, to, from);
            }

        }

        int nextUnpainted = getNextUnpainted();
        boolean isPaintProcessCorrect = true;

        while (nextUnpainted > 0) {
            isPaintProcessCorrect = paintInTwoColors(nextUnpainted, 2);

            if (!isPaintProcessCorrect) {
                break;
            }
            nextUnpainted = getNextUnpainted();
        }

        System.out.println(isPaintProcessCorrect && isTwoColored() ? "YES" : "NO");
    }

    private static boolean paintInTwoColors(int currentUnpainted, int prevColor) {
        int currentColor = prevColor == 1 ? 2 : 1;

        if (colors[currentUnpainted] == 0) {


            colors[currentUnpainted] = currentColor;
            Set<Integer> unpaintedNeighbors = new HashSet<>();

            for (Integer neighbor : graph.getOrDefault(currentUnpainted, EMPTY_SET)) {
                if (colors[neighbor] == 0) {
                    unpaintedNeighbors.add(neighbor);
                } else if (colors[neighbor] == currentColor) {
                    return false;
                }
            }

            if (unpaintedNeighbors.isEmpty()) {
                return true;
            }

            boolean paintResult = true;
            for (Integer next : unpaintedNeighbors) {
                paintResult = paintInTwoColors(next, currentColor);
                if (!paintResult) {
                    return false;
                }
            }

            return true;

        } else if (colors[currentUnpainted] == currentColor) {
            return true;
        }

        return false;
    }

    private static boolean isTwoColored() {
        for (Integer vertex : vertexesWithEdges) {
            if (colors[vertex] == 0) {
                return false;
            }
        }

        return true;
    }



    private static int getNextUnpainted() {
        for (int i = lastPainted; i < vertexesWithEdges.size(); i++) {
            int vertex = vertexesWithEdges.get(i);
            if (colors[vertex] == 0) {
                return vertex;
            }
            lastPainted++;
        }

        return -1;
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
