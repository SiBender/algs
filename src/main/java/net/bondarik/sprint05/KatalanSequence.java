package net.bondarik.sprint05;

public class KatalanSequence {

    public static void main(String[] args) {


        for (int i = 0; i <= 6; i ++) {
            System.out.println(i + " | " + getKatalanNum(i));
        }
     }

    private static int getKatalanNum(int num) {
        if (num == 0) {
            return 1;
        }

        if (num == 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += getKatalanNum(i - 1) * getKatalanNum(num - i);
        }

        return sum;
    }
}
