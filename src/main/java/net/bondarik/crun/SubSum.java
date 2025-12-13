package net.bondarik.crun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SubSum {
    private static final int TARGET_SUM = 100;
    private static int bestSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            nums.add(Integer.parseInt(reader.readLine().strip()));
        }

        calculateSubsums(nums);

        writer.write(String.valueOf(bestSum));
        writer.flush();
    }

    private static void calculateSubsums(List<Integer> source) {
        if (source.isEmpty()) {
            return;
        }

        int currentSum = getSum(source);
        if (Math.abs(TARGET_SUM - bestSum) > Math.abs(TARGET_SUM - currentSum)) {
            bestSum = currentSum;
        } else if (Math.abs(TARGET_SUM - bestSum) == Math.abs(TARGET_SUM - currentSum)) {
            bestSum = Math.max(bestSum, currentSum);
        }

        for (int i = 0; i < source.size(); i++) {
            List<Integer> sublist = getSubList(source, i);
            calculateSubsums(sublist);
        }
    }

    private static List<Integer> getSubList(List<Integer> source, int positionToExclude) {
        List<Integer> result = new ArrayList<>(source.size() - 1);
        for (int i = 0; i < source.size(); i++) {
            if (i != positionToExclude) {
                result.add(source.get(i));
            }
        }
        return result;
    }

    private static int getSum(List<Integer> list) {
        int sum = 0;
        for (int val : list) {
            sum += val;
        }
        return sum;
    }
}
