package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class LongestCommonSequenceByHashes2 {
    private static final long base = 7L * 109 * 173;
    private static final long mod = 101L * 191 * 467 * 1187 * 883;

    private static int[] line1;
    private static int[] line2;

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


        int maxLength = findMaxSubLen(0, Math.min(line1.length, line2.length) + 1);


        System.out.println(maxLength);
    }

    private static int findMaxSubLen(int min, int max) {
        if (max - min <= 1) {
            return min;
        }

        int middle = (max + min) / 2;

        if (isSubLenExists(middle)) {
            return findMaxSubLen(middle, max);
        } else {
            return findMaxSubLen(min, middle);
        }
    }

    private static boolean isSubLenExists(int length) {
        Map<Long, Integer> line1Hashes = getAllHashes(line1, length);

        int left = 0;
        int right = length - 1;
        long hash = -1;

        long poweredBase = getPowByMod(length - 1);
        while (right < line2.length) {
            if (hash == -1) {
                hash = getHash(line2, left, right);
            } else {
                hash = getPrefixHash(line2, hash, left, right, poweredBase);
            }

            if (line1Hashes.containsKey(hash)) {
                if (isEqualsSubsequences(line1Hashes.get(hash), left, length)) {
                    return true;
                }
            }

            left++;
            right++;
        }

        return false;
    }

    private static Map<Long, Integer> getAllHashes(int[] line, int length) {
        Map<Long, Integer> hashes = new HashMap<>();
        long poweredBase = getPowByMod(length - 1);

        int left = 0;
        int right = length - 1;
        long hash = -1;

        while (right < line.length) {
            if (hash == -1) {
                hash = getHash(line1, left, right);
            } else {
                hash = getPrefixHash(line1, hash, left, right, poweredBase);
            }
            hashes.put(hash, left);
            left++;
            right++;
        }

        return hashes;
    }


    private static long getHash(int[] line, int left, int right) {
        long hash = 0;
        while (left <= right) {
            hash = getNextHash(hash, line[left]);
            left++;
        }
        return hash;
    }


    private static long getNextHash(long prev, int nexCharCode) {
        return (prev * base + nexCharCode) % mod;
    }


    private static long getPrefixHash(int[] line, long prevHash, int left, int right, long poweredBase) {
        long prefixHash = ((prevHash - line[left - 1] * poweredBase) % mod + mod) % mod;
        return getNextHash(prefixHash, line[right]);
    }

    private static long getPowByMod(int power) {
        if (power == 0) {
            return 1;
        } else {
            int halhPower = power / 2;
            long halfPowerValue = (getPowByMod(halhPower)) % mod;
            if (power % 2 == 1) {
                return (base * (halfPowerValue * halfPowerValue) % mod) % mod;
            } else {
                return (halfPowerValue * halfPowerValue) % mod;
            }

        }
    }

    private static boolean isEqualsSubsequences(int line1Index, int line2Index, int length) {
        for (int i = 0; i < length; i++) {
            if (line1[line1Index + i] != line2[line2Index + i]) {
                return false;
            }
        }

        return true;
    }
}
