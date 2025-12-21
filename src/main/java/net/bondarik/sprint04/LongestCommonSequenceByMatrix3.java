package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * самую длоинную последовательность в виртуальных диагоналях.
 * Полный перебор хоть и линейный, всё равно не укладывается по времени
 *
 * Попробую перебирать по одной диагонали.
 * Если полученная длина больше или равна оставшихся диагоналей, то return
 *
 *
 * Надо научиться их генерировать диагонали заданной длины
 * А потом написать метод получения длинны совпадений по заданной диагонали
 */

public class LongestCommonSequenceByMatrix3 {
    private static int[] line1;
    private static int[] line2;

    private static int maxDiagLen;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length1 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        line1 = new int[length1];
        for (int i = 0; i < length1; i++) {
            line1[i] = Integer.parseInt(tokenizer1.nextToken());
        }

        int length2 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
        line2 = new int[length2];
        for (int i = 0; i < length2; i++) {
            line2[i] = Integer.parseInt(tokenizer2.nextToken());
        }


        maxDiagLen = Math.min(length1, length2);

        int maxSubarrayLen = 0;

        int currentDiagonalLen = maxDiagLen;
        while (currentDiagonalLen > 0) {
            Map<int[], int[]> diagonals = getAllDiagonalsForLen(currentDiagonalLen);
            for (Map.Entry<int[], int[]> diag : diagonals.entrySet()) {
                int currentCommonLen = findMatchLenByDiagonal(diag.getKey(), diag.getValue());

                maxSubarrayLen = Math.max(maxSubarrayLen, currentCommonLen);

                if (currentCommonLen == currentDiagonalLen) {
                    break;
                }
            }

            if (maxSubarrayLen >= currentDiagonalLen) {
                break;
            }

            currentDiagonalLen--;
        }

        System.out.println(maxSubarrayLen);
    }

    private static Map<int[],int[]> getAllDiagonalsForLen(int currentDiagonalLen) {
        Map<int[], int[]> result = new HashMap<>();

        if (currentDiagonalLen == maxDiagLen) {
            int maxLenDiagsCount = Math.abs(line1.length - line2.length) + 1;
            for (int i = 0; i < maxLenDiagsCount; i++) {
                if (line1.length < line2.length) {
                    result.put(new int[]{0, line1.length - 1},
                               new int[]{0 + i,  line1.length - 1 + i});
                } else {
                    result.put(new int[]{0 + i, line2.length - 1 + i},
                               new int[]{0,  line2.length - 1});
                }
            }
        } else {
                result.put(new int[]{line1.length - currentDiagonalLen, line1.length - 1},
                           new int[]{0, currentDiagonalLen - 1}); //верх право
                result.put(new int[]{0, currentDiagonalLen - 1},
                           new int[]{line2.length - currentDiagonalLen, line2.length - 1}); // низ лево
        }

        return result;
    }

    private static int findMatchLenByDiagonal(int[] line1subarray, int[] line2subarray) {
        int maxLen = 0;
        int diagLength = line1subarray[1] - line1subarray[0] + 1;
        //Поиск по диагоналям в виртуальной матрице. Диагонали направлены слева направо сверху вниз \

        int currentLen = 0;
        for (int i = 0; i < diagLength; i++) {
            if (line1[i + line1subarray[0]] == line2[i + line2subarray[0]]) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 0;
            }
        }
        maxLen = Math.max(maxLen, currentLen);

        return maxLen;
    }
}
