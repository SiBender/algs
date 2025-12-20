package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnagramGroups {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String length = reader.readLine();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        Map<Map<Character, Integer>, List<Integer>> totalCounts = new LinkedHashMap<>();
        int tokenCounter = 0;
        while (tokenizer.hasMoreTokens()) {
            Map<Character, Integer> charsCounter =  getCounts(tokenizer.nextToken());
            mergeResult(totalCounts, charsCounter, tokenCounter);
            tokenCounter++;
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (List<Integer> list : totalCounts.values()) {
            result.add(
                    list.stream().map(String::valueOf).collect(Collectors.joining(" "))
            );
        }
        System.out.println(result);
    }

    private static Map<Character, Integer> getCounts(String in) {
        Map<Character, Integer> res = new TreeMap<>();
        for (char ch : in.toCharArray()) {
            res.merge(ch, 1, Integer::sum);
        }
        return res;
    }

    private static void mergeResult(Map<Map<Character, Integer>, List<Integer>> result,
                                    Map<Character, Integer> next, int nextPos) {
        if (result.containsKey(next)) {
            result.get(next).add(nextPos);
        } else {
            List<Integer> newArray = new ArrayList<>();
            newArray.add(nextPos);
            result.put(next, newArray);
        }
    }
}
