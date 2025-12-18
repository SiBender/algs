package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FourSum {
    private static long[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());
        long target = Long.parseLong(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        data = new long[dataLength];
        for (int i = 0; i < dataLength; i++) {
            data[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(data);

        Map<String, int[]> result = new TreeMap<>();

        Map<Long, List<int[]>> allTwoSums = getAllTwoSums(data);
        Set<Long> knownSubsums = new HashSet<>();
        for (long subsum : allTwoSums.keySet()) {
            long twoSumTarget = target - subsum;
            if (knownSubsums.contains(subsum) || knownSubsums.contains(twoSumTarget)) {
                continue;
            }
            if (allTwoSums.containsKey(twoSumTarget)) {
                result.putAll(mergeTwoArrays(allTwoSums.get(subsum), allTwoSums.get(twoSumTarget)));
                knownSubsums.add(subsum);
                knownSubsums.add(twoSumTarget);
            }
        }


        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        Set<String> uniqueValues = new HashSet<>();
        List<int[]> resultArrays = new ArrayList<>(result.values().stream().toList());
        resultArrays.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare0 = Integer.compare(o1[0], o2[0]);
                if (compare0 != 0) {
                    return compare0;
                }
                int compare1 = Integer.compare(o1[1], o2[1]);
                if (compare1 != 0) {
                    return compare1;
                }
                int compare2 = Integer.compare(o1[2], o2[2]);
                if (compare2 != 0) {
                    return compare2;
                }
                return Integer.compare(o1[3], o2[3]);

            }
        });
        for (int[] indexes : resultArrays) {
            String valuesFromArrayAsString = "%d %d %d %d".formatted(data[indexes[0]],
                                                                     data[indexes[1]],
                                                                     data[indexes[2]],
                                                                     data[indexes[3]]);
            if (!uniqueValues.contains(valuesFromArrayAsString)) {
                joiner.add(valuesFromArrayAsString);
                uniqueValues.add(valuesFromArrayAsString);
            }

        }

        System.out.println(uniqueValues.size());
        System.out.println(joiner);
    }

    private static Map<String, int[]> mergeTwoArrays(List<int[]> ints, List<int[]> ints1) {
        Map<String, int[]> result = new TreeMap<>();
        for (int[] left : ints) {
            for (int[] right : ints1) {
                Set<Integer> filter = new TreeSet<>();
                filter.add(left[0]);
                filter.add(left[1]);
                filter.add(right[0]);
                filter.add(right[1]);

                if (filter.size() == 4) {
                    String key = filter.stream().map(String::valueOf).collect(Collectors.joining(" "));
                    Iterator<Integer> iterator = filter.iterator();
                    int[] value = new int[]{iterator.next(), iterator.next(), iterator.next(), iterator.next()};
                    result.put(key, value);
                }
            }
        }

        return result;
    }

    private static Map<Long, List<int[]>> getAllTwoSums(long[] data) {
        Map<Long, List<int[]>> allPairsSums = new HashMap<>();

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                merge(allPairsSums, data[i] + data[j], new int[]{i, j});
            }
        }
        return allPairsSums;
    }

    private static void merge(Map<Long, List<int[]>> map, long key, int[] value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<int[]> newList = new ArrayList<>();
            newList.add(value);
            map.put(key, newList);
        }
    }
}
