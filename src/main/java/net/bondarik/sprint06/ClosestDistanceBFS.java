package net.bondarik.sprint06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ClosestDistanceBFS {
    private static List<Integer>[] graph;

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

        resetDataArrays();

        calculateDistances(from, to);

        System.out.println(distance[to]);
    }

    private static void resetDataArrays() {
        distance[0] = -1;
        for (int i = 1; i < distance.length; i++) {
            distance[i] = -1;
        }
    }

    private static void calculateDistances(int startVertex, int finish) {
        Deque<Path> neighbors = new LinkedList<>();

        neighbors.addLast(new Path(startVertex, 0));


        while (!neighbors.isEmpty()) {
            Path currentVertex = neighbors.pollFirst();

            List<Integer> currentNeighbors = graph[currentVertex.getVertexNum()];
            for (Integer neighbor : currentNeighbors) {
                if (visited[neighbor] == 0) {
                    neighbors.addLast(new Path(neighbor, currentVertex.getPrevDistance() + 1));
                    visited[neighbor] = 1;
                }
            }

            distance[currentVertex.getVertexNum()] = currentVertex.getPrevDistance();

            if (currentVertex.getVertexNum() == finish) {
                break;
            }
        }
    }


    private static void put(int from, int to) {
        graph[from].add(to);
    }
}

class Path {
    private final int vertexNum;
    private final int prevDistance;

    public Path(int vertexNum, int prevDistance) {
        this.vertexNum = vertexNum;
        this.prevDistance = prevDistance;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getPrevDistance() {
        return prevDistance;
    }
}
