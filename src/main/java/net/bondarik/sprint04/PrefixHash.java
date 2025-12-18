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


        long prevHash = 0;
        long newHash = 0;
        cache.put(0, newHash);


        String input = reader.readLine();


        int commandsCount = Integer.parseInt(reader.readLine());
        int[] commands = new int[commandsCount * 2];
        String[] data;

        int minLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < commandsCount; i++) {
            data = reader.readLine().split(" ");

            commands[i * 2] = Integer.parseInt(data[0]);
            commands[i * 2 + 1] = Integer.parseInt(data[1]);

            minLeft = Math.min(minLeft, commands[i * 2]);
            maxRight = Math.max(maxRight, commands[i * 2 + 1]);
        }
        minLeft--; //т.к. для префикса нужен элемент left - 1


        for (int i = 0; i < maxRight; i++) {
            newHash = getNextHash(prevHash, input.charAt(i));

            if (i >= minLeft) {
                cache.put(i + 1, newHash);
            }

            prevHash = newHash;
        }


        StringJoiner result = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < commandsCount; i++) {

            result.add(String.valueOf(
                    getPrefixHash(commands[i * 2], commands[i * 2 + 1])
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
        //базовый случай с расширением, чтобы уменьшить глубину стека
        if (power == 0) {
            return 1;
        } else if (power == 1) {
            return base;
        } else if (power == 2) {
            return (base * base) % mod;
        } else if (power == 3) {
            return (base * (base * base) % mod ) % mod;
        } else {
            long halfPowerValue = (getPowByMod(power / 2)) % mod;
            if (power % 2 == 1) {
                return (base * (halfPowerValue * halfPowerValue) % mod) % mod;
            } else {
                return (halfPowerValue * halfPowerValue) % mod;
            }

        }
    }
}
