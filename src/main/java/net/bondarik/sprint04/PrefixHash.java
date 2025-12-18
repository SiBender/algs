package net.bondarik.sprint04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class PrefixHash {
    private static final Map<Integer, Long> cache = new HashMap<>();
    private static long base;
    private static long mod;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        base = Long.parseLong(reader.readLine());
        mod = Long.parseLong(reader.readLine());

        String input = reader.readLine();

        long prevHash = 0;
        long newHash = 0;
        cache.put(0, newHash);
        for (int i = 0; i < input.length(); i++) {
            newHash = getNextHash(prevHash, input.charAt(i));
            cache.put(i + 1, newHash);

            prevHash = newHash;
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());

        int commandsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < commandsCount; i++) {
            String[] data = reader.readLine().split(" ");
            result.add(String.valueOf(
                                getPrefixHash(Integer.parseInt(data[0]), Integer.parseInt(data[1]))
                    ));
        }

        System.out.println(result);
    }

    private static long getNextHash(long prev, int nexCharCode) {
        return (prev * base + nexCharCode) % mod;
    }

    private static long getPrefixHash(int left, int right) {
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
