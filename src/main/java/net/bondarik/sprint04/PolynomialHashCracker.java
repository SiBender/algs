package net.bondarik.sprint04;


import java.util.Stack;

public class PolynomialHashCracker {
    public static void main(String[] args) {
        long base = 1000L;
        long mod = 123987123L;

        String asd = "dkbelnvzdlkgsadfasdfgsdb";

        long target = PolynomialHashCalc.getHash(asd, base, mod);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String next = build(i);

            long hash = PolynomialHashCalc.getHash(next, base, mod);

            if (i % 1000000 == 0) {
                //System.out.println(i + " | " + next + " | " + hash );
                System.out.println(i);
            }
            if (hash == target) {
                System.out.println(next);
                break;
            }
        }
    }

    private static String build(int num) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> asd = new Stack<>();
        while (num > 0) {
            asd.push(num % 26);
            num /= 26;
        }

        while (!asd.isEmpty()) {
            result.append((char)('a' + asd.pop()));
        }
        return result.toString();
    }

}
