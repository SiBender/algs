package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Hobbies {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        Set<String> result = new LinkedHashSet<>();

        for (int i = 0; i < dataLength; i++) {
            result.add(reader.readLine());
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        for (String item : result) {
            joiner.add(item);
        }

        System.out.println(joiner);
    }
}
