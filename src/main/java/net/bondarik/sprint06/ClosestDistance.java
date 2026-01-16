package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ClosestDistance {
    private static List<Integer>[] graph;

    private static int[] distance;
    private static int[] visited;

    private static Set<Integer> nextStepCandidates;

    private static int vDistance = 0;
    private static int  uDistance = 0;

    public static void main(String[] args) throws IOException {

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("C:\\_code\\algs\\src\\main\\resources\\6-F-tests_41.txt"));

        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);

        distance = new int[vertexCount + 1];
        visited = new int[vertexCount + 1];

        graph = new List[vertexCount + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());

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
        nextStepCandidates = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int distancesCompare = Integer.compare(distance[o1], distance[o2]);
                return distancesCompare != 0 ? distancesCompare : Integer.compare(o1, o2);
            }
        });
        distance[0] = -1;
        visited[0] = -1;
        for (int i = 1; i < distance.length; i++) {
            distance[i] = -1;
            visited[i] = -1;
        }
    }

    private static void dijxtraAlg(Integer startVertex) {
        resetDataArrays();

        distance[startVertex] = 0;
        nextStepCandidates.add(startVertex);
        while (true) {
            int u = getClosestUnvisited();

            if (u == -1 || distance[u] == -1) {
                break;
            }

            visited[u] = 1;
            nextStepCandidates.remove(u);
            List<Integer> neighbours = graph[u];

            for (Integer neighborVertex : neighbours) {
                relax(u, neighborVertex);
            }
        }
    }


    private static int getClosestUnvisited() {
        return nextStepCandidates.isEmpty() ? -1 : nextStepCandidates.iterator().next();
    }

    private static void relax(int u, int v) {
        vDistance = distance[v];
        uDistance = distance[u] + 1;
        if (vDistance == -1 || vDistance > uDistance) {
            distance[v] = uDistance;
            //previous[v] = u;
        }

        if (visited[v] == -1) {
            nextStepCandidates.add(v);
        }
    }


    private static void put(int from, int to) {
        graph[from].add(to);
    }
}
