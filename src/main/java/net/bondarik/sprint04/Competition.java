package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Competition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dataLength = Integer.parseInt(reader.readLine());

        Map<Integer, Integer> subSums = new HashMap<>();
        subSums.put(0, -1); //

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < dataLength; i++) {
            if (tokenizer.nextToken().equals("0")) {
                sum -= 1;
            } else {
                sum += 1;
            }

            if (sum == 0) {
                maxLen = i + 1;
            } else {
                //sum - subsum = targetSum
                //sum = subsum
                if (subSums.containsKey(sum)) {
                    maxLen = Math.max(maxLen, i - subSums.get(sum));
                }
            }

            subSums.merge(sum, i, Math::min);
        }

        System.out.println(maxLen);
    }
}
