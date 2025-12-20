package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class LongestCommonSequenceByHashes {
    private static final long base = 1000;
    private static final long mod = 123987123;

    private static final Map<Integer, Long> cache_1 = new HashMap<>();
    private static final Map<Integer, Long> cache_2 = new HashMap<>();

    private static final Map<Long, Integer> LINE_1_PREFIX_HASHES = new HashMap<>();
    private static final Map<Long, Integer> LINE_2_HASHES = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length1 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        int[] line1 = new int[length1];

        long prevHash = 0;
        long newHash = 0;
        cache_1.put(0, newHash);

        for (int i = 0; i < length1; i++) {
            line1[i] = Integer.parseInt(tokenizer1.nextToken());
            newHash = getNextHash(prevHash, line1[i]);
            cache_1.put(i + 1, newHash);
            prevHash = newHash;
        }

        int length2 = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(reader.readLine());
        int[] line2 = new int[length2];

        prevHash = 0;
        newHash = 0;
        cache_2.put(0, newHash);
        for (int i = 0; i < length2; i++) {
            line2[i] = Integer.parseInt(tokenizer2.nextToken());
            newHash = getNextHash(prevHash, line2[i]);
            cache_2.put(i + 1, newHash);
            prevHash = newHash;
        }

        for (int i = 1; i <= line1.length; i++) {
            for (int j = i; j <= line1.length; j++) {
                long prefixHash = getPrefixHash(cache_1, i, j);

                LINE_1_PREFIX_HASHES.merge(prefixHash,
                                     j - i + 1,
                                           Math::max);
            }
        }

        long line2PrefixHash = 0;
        int maxLength = 0;
        for (int i = 1; i <= line2.length; i++) {
            for (int j = i; j <= line2.length; j++) {
                line2PrefixHash = getPrefixHash(cache_2, i, j);

                if (LINE_1_PREFIX_HASHES.containsKey(line2PrefixHash) &&
                    LINE_1_PREFIX_HASHES.get(line2PrefixHash) == j - i + 1) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        System.out.println(maxLength);
    }

    private static long getNextHash(long prev, int nexCharCode) {
        return (prev * base + nexCharCode) % mod;
    }


    private static long getPrefixHash(Map<Integer, Long> cache, int left, int right) {
        return (
                mod + cache.get(right)
                        - (cache.get(left - 1) * getPowByMod(right - left + 1)) % mod
        ) % mod;
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
}
