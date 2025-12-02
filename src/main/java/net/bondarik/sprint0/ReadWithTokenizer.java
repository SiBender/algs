package net.bondarik.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReadWithTokenizer {

    public static void main(String[] args) throws IOException {
        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num_lines = Integer.parseInt(reader.readLine());
        for (int i = 0; i < num_lines; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int value_1 = Integer.parseInt(tokenizer.nextToken());
            int value_2 = Integer.parseInt(tokenizer.nextToken());
            int result = value_1 + value_2;
            output_buffer.append(result).append("\n");
        }
        System.out.println(output_buffer.toString());
    }
}
