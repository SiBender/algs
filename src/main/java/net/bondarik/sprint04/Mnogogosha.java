package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mnogogosha {
    private static final long base = 1000;
    private static final long mod = 123987123;

    private static char[] data;

    private static final Map<Long, List<Integer>> substrings = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] params = reader.readLine().split(" ");
        int subLen = Integer.parseInt(params[0]);
        int minRepeat = Integer.parseInt(params[1]);
        data = reader.readLine().toCharArray();

        int left = 0;
        int right = left + subLen;

        long prevHash = -1;
        long hash = 0;
        while (right <= data.length) {
            if (prevHash < 0) {
                hash = getHash(left, right);
            } else {
                hash = getPrefixHash(hash, left, right);
            }
            put(substrings, left, right);
            left++;
            right++;
        }

        List<Integer> result = new ArrayList<>();
        for (List<Integer> list : substrings.values()) {
            if (list.size() >= minRepeat) {
                result.add(list.getFirst());
            }
        }

        System.out.println(result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void put( Map<Long, List<Integer>> map, long key, int left) {
        if (map.containsKey(key)) {
            map.get(key).add(left);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(left);
            map.put(key, list);
        }
    }

    private static long getHash(int left, int right) {
        long hash = 0;
        while (left < right) {
            hash = (hash * base + (int)data[left]) % mod;
            left++;
        }

        return hash;
    }

    private static long getPrefixHash(long prevHash, int left, int right) {
        long prefixHash = (mod + prevHash - data[left - 1] * getPowByMod(right - left)) % mod;
        return (prefixHash * base + (int)data[right - 1]) % mod;
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
