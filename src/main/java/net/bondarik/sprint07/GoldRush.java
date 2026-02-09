package net.bondarik.sprint07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GoldRush {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine());
        long totalCost = 0;

        int dataLength = Integer.parseInt(reader.readLine());

        List<Gold> givenHeaps = new ArrayList<>();

        for (int i = 0; i < dataLength; i++) {
            givenHeaps.add(Gold.fromString(reader.readLine()));
        }

        givenHeaps.sort(new Comparator<Gold>() {
            @Override
            public int compare(Gold o1, Gold o2) {
                return Integer.compare(o2.getCost(), o1.getCost());
            }
        });

        for (int i = 0; i < givenHeaps.size(); i++) {
            if (capacity == 0) {
                break;
            }

            Gold current =  givenHeaps.get(i);

            int currentAmount = Math.min(capacity, current.getAmount());
            totalCost += (long) currentAmount * current.getCost();
            capacity -= currentAmount;
        }

        System.out.println(totalCost);
    }

    static class Gold {
        private final int cost;
        private final int amount;


        public Gold(int cost, int amount) {
            this.cost = cost;
            this.amount = amount;
        }

        public int getCost() {
            return cost;
        }
        public int getAmount() {
            return amount;
        }


        public static Gold fromString(String string) {
            String[] split = string.split(" ");
            return new Gold(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
    }
}
