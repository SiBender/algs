package net.bondarik.crun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ecipa {
    private static final long MOD = 1_000_000_000 + 7;

    private static long[] groups;
    private static long[] deltas;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = reader.readLine().split(" ");
        int availableEquipment = Integer.parseInt(line1[0]);
        int groupsCount = Integer.parseInt(line1[1]);
        groups = new long[groupsCount];
        deltas = new long[groupsCount];


        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        long totalNeededEquipment = 0;
        long minValue = Integer.MAX_VALUE;
        for (int i = 0; i < groupsCount; i++) {
            groups[i] = Long.parseLong(stringTokenizer.nextToken());
            totalNeededEquipment += groups[i];
            minValue = Math.min(minValue, groups[i]);
        }

        for (int i = 0; i < groupsCount; i++) {
            deltas[i] = minValue;
        }

        long delta = totalNeededEquipment - availableEquipment - minValue * groupsCount;

        int globalPointer = 0;
        while (delta > 0) {
            int position = globalPointer % groupsCount;
            if (groups[position] - minValue > 0) {
                groups[position]--;
                deltas[position]++;
                delta--;
            }
            globalPointer++;
        }

        long sumByModule = 0;
        for (int i = 0; i < deltas.length; i++) {
            sumByModule += deltas[i] * deltas[i];
            sumByModule = sumByModule % MOD;
        }

        System.out.println(sumByModule);
    }
}