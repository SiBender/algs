package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BigNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        String[] data = new String[dataLength];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < dataLength; i++) {
            data[i] = tokenizer.nextToken();
        }

        Arrays.sort(data, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int firsLeadingSum = Integer.parseInt(o1 + o2);
                int secondLeadingSum = Integer.parseInt(o2 + o1);
                return Integer.compare(firsLeadingSum, secondLeadingSum);
            }
        });

        StringBuilder result = new StringBuilder();

        for (int i = dataLength - 1; i >= 0; i--) {
            result.append(data[i]);
        }

        System.out.println(result);
    }

}
