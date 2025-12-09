package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class TelephoneCombinations {
    public static final StringJoiner result = new StringJoiner(" ");

    private static final Map<Character, List<Character>> keyboard = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z')
    );

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] inputNumbers = reader.readLine().toCharArray();

        generate("", inputNumbers, -1);
        System.out.println(result);
    }

    private static void generate(String prefix, char[] inputNumbers, int previousIndex) {
        int currentIndex = previousIndex + 1;
        if (currentIndex == inputNumbers.length) {
            result.add(prefix);
        } else {
            for (char charByNumber : keyboard.get(inputNumbers[currentIndex])) {
                generate(prefix + charByNumber, inputNumbers, currentIndex);
            }
        }
    }
}
