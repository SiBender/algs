package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class FlowerBeds {
    private static final Map<Integer, Integer> segments = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        int[][] data = new int[dataLength][2];
        String[] nextSegment;
        for (int i = 0; i < dataLength; i++) {
            nextSegment = reader.readLine().split(" ");
            data[i][0] = Integer.parseInt(nextSegment[0]);
            data[i][1] = Integer.parseInt(nextSegment[1]);
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });


        int lastAddedLeft = data[0][0];
        int lastAddedRight = data[0][1];
        for (int i = 1; i < dataLength; i++) {
            int left = data[i][0];
            int right = data[i][1];
            if (left > lastAddedRight) {
                segments.put(lastAddedLeft, lastAddedRight);
                lastAddedLeft = left;
                lastAddedRight = right;
            } else {
                lastAddedRight = Math.max(lastAddedRight, right);
            }
        }
        segments.put(lastAddedLeft, lastAddedRight);

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (var entry : segments.entrySet()) {
            result.add("%d %d".formatted(entry.getKey(), entry.getValue()));
        }

        System.out.println(result);
    }

}
