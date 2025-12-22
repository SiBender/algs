package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClosestBussStops {
    private static final long MAX_DISTANCE = 20L;
    private static final long MAX_DISTANCE_SQUARE = 20L * 20;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int exitsCount = Integer.parseInt(reader.readLine());

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int[] exits = new int[exitsCount * 2];
        for (int i = 0; i < exitsCount; i++) {
            String[] coord = reader.readLine().split(" ");
            exits[2 * i] = Integer.parseInt(coord[0]);
            exits[2 * i + 1] = Integer.parseInt(coord[1]);

            minX = Math.min(minX, exits[2 * i]);
            maxX = Math.max(maxX, exits[2 * i]);

            minY = Math.min(minY, exits[2 * i + 1]);
            maxY = Math.max(minY, exits[2 * i + 1]);
        }

        minX -= MAX_DISTANCE;
        maxX += MAX_DISTANCE;
        minY -= MAX_DISTANCE;
        maxY += MAX_DISTANCE;
        int stopsCount = Integer.parseInt(reader.readLine());
        Map<Integer, Set<Integer>> stops = new TreeMap<>();
        for (int i = 0; i < stopsCount; i++) {

            String[] coord = reader.readLine().split(" ");
            int x = Integer.parseInt(coord[0]);

            if (x < minX || x > maxX) {
                continue;
            }
            int y = Integer.parseInt(coord[1]);
            if (y < minY || y > maxY) {
                continue;
            }
            merge(stops, x, y);
        }

        Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < exitsCount; i++) {
            long x1 = exits[2 * i];
            long y1 = exits[2 * i + 1];

            for (Map.Entry<Integer, Set<Integer>> entry : stops.entrySet()) {
                long x2 = entry.getKey();
                if (x2 > x1 && Math.abs(x1 - x2) > MAX_DISTANCE) {
                    break;
                }

                if (Math.abs(x1 - x2) > MAX_DISTANCE) {
                    continue;
                }

                for (int y2 : entry.getValue()) {
                    if (y2 > y1 && Math.abs(y2 - y1) > MAX_DISTANCE) {
                        break;
                    }

                    if (Math.abs(y2 - y1) > MAX_DISTANCE) {
                        continue;
                    }

                    long distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                    if (distance <= MAX_DISTANCE_SQUARE) {
                        result.merge(i + 1, 1, Integer::sum);
                    }
                }

            }
        }

        List<Integer> sortedExits = new ArrayList<>(result.keySet());
        sortedExits.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int compare = Integer.compare(result.get(o2), result.get(o1));
                return compare != 0 ? compare : Integer.compare(o1, o2);
            }
        });

        System.out.println(sortedExits.getFirst());
    }

    private static void merge(Map<Integer, Set<Integer>> map, int x, int y) {
        if (map.containsKey(x)) {
            map.get(x).add(y);
        } else {
            Set<Integer> set = new TreeSet<>();
            set.add(y);
            map.put(x, set);
        }
    }
}
