package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class LongestCommonSequenceByMatrix2 {
    private static int[] line1;
    private static int[] line2;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length1 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        line1 = new int[length1];
        for (int i = 0; i < length1; i++) {
            line1[i] = Integer.parseInt(tokenizer1.nextToken());
        }

        int length2 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
        line2 = new int[length2];
        for (int i = 0; i < length2; i++) {
            line2[i] = Integer.parseInt(tokenizer2.nextToken());
        }

        Map<Integer, List<Integer>> line1values = getValues(line1);
        Map<Integer, List<Integer>> line2values = getValues(line2);

        Set<Integer> removeFrom1 = new HashSet<>();
        for (int value : line1values.keySet()) {
            if (!line2values.containsKey(value)) {
                removeFrom1.add(value);
            }
        }
        removeFrom1.forEach(line1values::remove);

        Set<Integer> removeFrom2 = new HashSet<>();
        for (int value : line2values.keySet()) {
            if (!line1values.containsKey(value)) {
                removeFrom2.add(value);
            }
        }
        removeFrom2.forEach(line2values::remove);

        List<Integer> line1points = line1values.values().stream().flatMap(List::stream).sorted().toList();
        List<int[]> line1SubRanges = getSubRanges(line1points);
        List<Integer> line2points = line2values.values().stream().flatMap(List::stream).sorted().toList();
        List<int[]> line2SubRanges = getSubRanges(line2points);

        int maxSubarrayLen = 0;
        for (int i = 0; i < line1SubRanges.size(); i++) {
            for (int j = 0; j < line2SubRanges.size(); j++) {
                maxSubarrayLen = Math.max(maxSubarrayLen,
                        findMaxSubarray(line1SubRanges.get(i), line2SubRanges.get(j)));
            }
        }

        System.out.println(maxSubarrayLen);
    }

    private static List<int[]> getSubRanges(List<Integer> indexes) {
        if (indexes.size() == 1) {
            return List.of(new int[]{indexes.getFirst(), indexes.getFirst()});
        }

        List<int[]> result = new ArrayList<>();

        int left = 0;
        int right = 1;
        while (right < indexes.size()) {
            if (indexes.get(right) - indexes.get(right - 1) == 1) {
                right++;
            } else {
                result.add(new int[]{indexes.get(left), indexes.get(right - 1)});
                left = right;
                right++;
            }

            if (right == indexes.size()) {
                result.add(new int[]{indexes.get(left), indexes.get(right - 1)});
            }
        }

        return result;
    }

    private static Map<Integer, List<Integer>> getValues(int[] array) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (result.containsKey(array[i])) {
                result.get(array[i]).add(i);
            } else {
                List<Integer> arr = new ArrayList<>();
                arr.add(i);
                result.put(array[i], arr);
            }
        }

        return result;
    }


    private static int findMaxSubarray(int[] line1subarray, int[] line2subarray) {
        int length1 = line1subarray[1] - line1subarray[0] + 1;
        int length2 = line2subarray[1] - line2subarray[0] + 1;
        int maxSubarrayLength = Math.min(length1, length2);
        int totalMaxLength = 0;
        int maxCounter = length1 - 1 + length2 - 1 + 1;

        //Поиск по диагоналям в виртуальной матрице. Диагонали направлены слева направо сверху вниз \
        for (int counter = 1; counter <= maxCounter; counter++) {
            if (totalMaxLength == maxSubarrayLength) {
                break;
            }

            int indexDelta = counter - length2;
            int baseStart = counter - length2 > 0 ? counter - length2 : 0;
            int baseEnd = Math.min(counter - 1, length1 - 1);

            int currentLength = 0;
            for (int i = baseStart; i <= baseEnd; i++) {
                //System.out.println("[%d : %d]".formatted(i + line1subarray[0], i - indexDelta + line2subarray[0]));
                if (line1[i + line1subarray[0]] == line2[i - indexDelta + line2subarray[0]]) {
                    currentLength++;
                } else {
                    totalMaxLength = Math.max(totalMaxLength, currentLength);
                    currentLength = 0;
                }
            }
            totalMaxLength = Math.max(totalMaxLength, currentLength);
        }

        return totalMaxLength;
    }
}
