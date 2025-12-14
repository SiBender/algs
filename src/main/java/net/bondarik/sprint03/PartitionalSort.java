package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class PartitionalSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());
        int[] data = new int[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }

        List<int[]> arrays = new ArrayList<>();// [left, right) , max

        int currentMaxIndex = 0;
        int localMin = data[currentMaxIndex];

        for (int i = 1; i < data.length; i++) {
            int nextValue = data[i];
            if (nextValue < data[currentMaxIndex]) {
                localMin = Math.min(localMin, nextValue);
            } else {
                arrays.add(new int[]{localMin, data[currentMaxIndex]});
                currentMaxIndex = i;
                localMin = data[currentMaxIndex];
            }
        }

        if (currentMaxIndex < data.length ) {
            arrays.add(new int[]{localMin, data[currentMaxIndex]});
        }

        arrays.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int uniqueSegmentsCounter = 0;

        int lastMaxRight = arrays.get(0)[1];
        for (int i = 1; i < arrays.size(); i++) {
            int[] next = arrays.get(i);

            if (next[0] <= lastMaxRight) {
                lastMaxRight = Math.max(next[1], lastMaxRight);
            } else {
                uniqueSegmentsCounter++;
                lastMaxRight = next[1];
            }
        }
        uniqueSegmentsCounter++;

        System.out.println(uniqueSegmentsCounter);
    }
}
