package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsFrequencies {
    private static Map<String, Integer> wordFrequencies = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dataLength = Integer.parseInt(reader.readLine());

        for (int i = 0; i < dataLength; i++) {
            wordFrequencies.merge(reader.readLine(), 1, Integer::sum);
        }

        List<String> words = new ArrayList<>(wordFrequencies.keySet());
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int countCompare = Integer.compare(wordFrequencies.get(o2), wordFrequencies.get(o1));
                return countCompare == 0 ? o1.compareTo(o2) : countCompare;
            }
        });

        System.out.println(words.getFirst());
    }
}
