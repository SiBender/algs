package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestCommonSequenceByMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length1 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        int[] line1 = new int[length1];
        for (int i = 0; i < length1; i++) {
            line1[i] = Integer.parseInt(tokenizer1.nextToken());
        }

        int length2 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
        int[] line2 = new int[length2];
        for (int i = 0; i < length2; i++) {
            line2[i] = Integer.parseInt(tokenizer2.nextToken());
        }

        int maxSubarrayLength = Math.min(length1, length2);
        int totalMaxLength = 0;
        int maxCounter = length1 - 1 + length2 - 1 + 1;

        //Поиск по диагоналям в виртуальной матрице. Диагонали направлены слева направо сверху вниз \
        for (int counter = 1; counter <= maxCounter; counter++) {
            if (totalMaxLength == maxSubarrayLength) {
                break;
            }

            int indexDelta = counter - length2;
            int baseStart = counter - length2 > 0 ? counter - length2 : 0;
            int baseEnd = Math.min(counter - 1, length1 - 1);

            int currentLength = 0;
            for (int i = baseStart; i <= baseEnd; i++) {
                if (line1[i] == line2[i - indexDelta]) {
                    currentLength++;
                } else {
                    totalMaxLength = Math.max(totalMaxLength, currentLength);
                    currentLength = 0;
                }
            }
            totalMaxLength = Math.max(totalMaxLength, currentLength);
        }

        System.out.println(totalMaxLength);
    }

}
