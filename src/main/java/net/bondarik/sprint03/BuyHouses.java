package net.bondarik.sprint03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BuyHouses {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputNums = reader.readLine().split(" ");
        int moneyAmount = Integer.parseInt(inputNums[1]);

        int costsCount = Integer.parseInt(inputNums[0]);
        int[] costs = new int[costsCount];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < costsCount; i++) {
            costs[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(costs);
        int totalHouses = 0;
        for (int cost : costs) {
            if (moneyAmount >= cost) {
                totalHouses++;
                moneyAmount -= cost;
            } else {
                break;
            }
        }

        System.out.println(totalHouses);
    }
}
