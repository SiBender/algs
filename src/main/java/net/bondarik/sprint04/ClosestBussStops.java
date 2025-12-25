package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClosestBussStops {
    private static final long MAX_DISTANCE = 20L;
    private static final long MAX_DISTANCE_SQUARE = 20L * 20;

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
        int[] stops = new int[stopsCount * 2];
        for (int i = 0; i < stopsCount; i++) {

            String[] coord = reader.readLine().split(" ");
            stops[i * 2] = Integer.parseInt(coord[0]);
            stops[i * 2 + 1] = Integer.parseInt(coord[1]);
        }


        for (int i = 0; i < exitsCount; i++) {
            int x1 = exits[2 * i];
            int y1 = exits[2 * i + 1];

            for (int j = 0; j < stopsCount; j++) {
                int xDistance = Math.abs(x1 - stops[2 * j]);
                if (xDistance > MAX_DISTANCE) {
                    continue;
                }

                int yDistance = Math.abs(y1 - stops[2 * j + 1]);

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
