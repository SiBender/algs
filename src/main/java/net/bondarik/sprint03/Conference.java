package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Conference {
    private static final Map<Integer, Integer> counter = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < dataLength; i++) {
            counter.merge(Integer.parseInt(tokenizer.nextToken()), 1, Integer::sum);
        }

        int resultLength = Integer.parseInt(reader.readLine());

        Map<Integer, Integer> sortedMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result = Integer.compare(counter.get(o2), counter.get(o1));
                return result == 0 ? Integer.compare(o1, o2) : result;
            }
        });

        sortedMap.putAll(counter);

        StringJoiner result = new StringJoiner(" ");
        for (var entry : sortedMap.entrySet()) {
            if (resultLength == 0) {
                break;
            }
            result.add(entry.getKey().toString());
            resultLength--;
        }

        System.out.println(result);
    }

}
