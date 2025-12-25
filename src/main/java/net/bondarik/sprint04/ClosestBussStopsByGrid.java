package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosestBussStopsByGrid {
    private static final Map<Integer, List<int[]>> emptyGridCell = new HashMap<>();
    private static final List<int[]> emptyPointsList = new  ArrayList<>();

    private static final long MAX_DISTANCE = 20L;
    private static final long MAX_DISTANCE_SQUARE = 20L * 20;

    private static final Map<Integer, Map<Integer, List<int[]>>> grid = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\code\\algs\\src\\main\\java\\net\\bondarik\\sprint04\\buss24.txt"));

        int exitsCount = Integer.parseInt(reader.readLine());

        int[] exits = new int[exitsCount * 2];
        int[] hitCount = new int[exitsCount];
        for (int i = 0; i < exitsCount; i++) {
            String[] coord = reader.readLine().split(" ");
            exits[2 * i] = Integer.parseInt(coord[0]);
            exits[2 * i + 1] = Integer.parseInt(coord[1]);
        }

        int stopsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < stopsCount; i++) {

            String[] coord = reader.readLine().split(" ");
            putToGrid(new int[]{Integer.parseInt(coord[0]), Integer.parseInt(coord[1])});
        }


        for (int i = 0; i < exitsCount; i++) {
            int x1 = exits[2 * i];
            int y1 = exits[2 * i + 1];

            List<int[]> pointsFromGrid = getNeighborsFromGrid(x1, y1);

            for (int[] point : pointsFromGrid) {
                int xDistance = Math.abs(x1 - point[0]);
                if (xDistance > MAX_DISTANCE) {
                    continue;
                }

                int yDistance = Math.abs(y1 - point[1]);

                if (yDistance > MAX_DISTANCE) {
                    continue;
                }

                long distance = (long) xDistance * xDistance + (long) yDistance * yDistance;
                if (distance <= MAX_DISTANCE_SQUARE) {
                    hitCount[i]++;
                }
            }
        }

        System.out.println(getMax(hitCount));
    }

    private static void putToGrid(int[] point) {
        int x = (int) (point[0] / MAX_DISTANCE);
        int y = (int) (point[1] / MAX_DISTANCE);

        if (grid.containsKey(x)) {
            Map<Integer, List<int[]>> dots = grid.get(x);
            if (dots.containsKey(y)) {
                dots.get(y).add(point);
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(point);
                dots.put(y, list);
            }
        } else {
            Map<Integer, List<int[]>> map = new HashMap<>();
            List<int[]> list = new ArrayList<>();
            list.add(point);
            map.put(y, list);
            grid.put(x, map);
        }
    }

    private static List<int[]> getNeighborsFromGrid(int x1, int y1) {
        int x = (int) (x1 / MAX_DISTANCE);
        int y = (int) (y1 / MAX_DISTANCE);

        List<int[]> pointsFromGrid = List.of(
                new int[]{x - 1, y - 1},
                new int[]{x - 1, y},
                new int[]{x - 1, y + 1},
                new int[]{x, y - 1},
                new int[]{x, y},
                new int[]{x, y + 1},
                new int[]{x + 1, y - 1},
                new int[]{x + 1, y},
                new int[]{x + 1, y + 1}
        );

        return pointsFromGrid.stream()
                             .map(p -> getPointsGromGridCell(p[0], p[1]))
                             .filter(l -> !l.isEmpty())
                             .flatMap(List::stream)
                             .toList();
    }

    private static List<int[]> getPointsGromGridCell(int x, int y) {
        return grid.getOrDefault(x, emptyGridCell).getOrDefault(y, emptyPointsList);
    }

    private static int getMax(int[] hitCounter) {
        int maxCount = 0;
        int maxCountPoint = 0;

        for (int i = 0; i < hitCounter.length; i++) {
            if (hitCounter[i] > maxCount) {
                maxCount = hitCounter[i];
                maxCountPoint = i;
            }
        }

        return maxCountPoint + 1;
    }
}
