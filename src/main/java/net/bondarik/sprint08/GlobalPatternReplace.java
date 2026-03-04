package net.bondarik.sprint08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GlobalPatternReplace {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String origin = reader.readLine();
        String pattern = reader.readLine();
        String target = reader.readLine();

        String concatenated = pattern + "#" + origin;
        int[] prefixes = new int[concatenated.length()]; // нумерация с 1

        List<Integer> replacePositions = new ArrayList<>(); //


        int prevLen = 0;
        for (int i = 1; i < concatenated.length(); i++) {
            int k = prevLen;
            while (k > 0 && concatenated.charAt(k) != concatenated.charAt(i)) {
                k = prefixes[k - 1];
            }
            if (concatenated.charAt(k) == concatenated.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < pattern.length()) {
                prefixes[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            prevLen = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == pattern.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                replacePositions.add(i - pattern.length());
            }
        }

        System.out.println(replace(origin, pattern, target, replacePositions));
    }

    private static String replace(String origin, String pattern, String replace, List<Integer> positions) {
        if (positions.isEmpty()) {
            return origin;
        }

        StringBuilder builder = new StringBuilder();

        int lastKnownIndex = 0;
        for (int i = 0; i < positions.size(); i++) {
            String prefix = origin.substring(lastKnownIndex, positions.get(i) - pattern.length());
            if (!prefix.isEmpty()) {
                builder.append(prefix);
            }
            builder.append(replace);
            lastKnownIndex = positions.get(i);
        }

        String reminder = origin.substring(lastKnownIndex);
        if (!reminder.isEmpty()) {
            builder.append(reminder);
        }
        return builder.toString();
    }
}
