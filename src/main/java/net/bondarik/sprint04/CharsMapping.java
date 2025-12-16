package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CharsMapping {

    private static final Map<Character, Character> MAPPING = new HashMap<>();
    private static final Map<Character, Character> REVERSE_MAPPING = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine();
        String t = reader.readLine();

        if (s.length() != t.length()) {
            System.out.println("NO");
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (MAPPING.containsKey(s.charAt(i))) {
                if (MAPPING.get(s.charAt(i)) != t.charAt(i)) {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (REVERSE_MAPPING.containsKey(t.charAt(i))) {
                    System.out.println("NO");
                    return;
                }
                MAPPING.put(s.charAt(i), t.charAt(i));
                REVERSE_MAPPING.put(t.charAt(i), s.charAt(i));
            }
        }

        System.out.println("YES");
    }

}
