package net.bondarik.sprint04;

public class PolynomialHashCalc {

    public static long getHash(String input, long a, long m) {

        long result = 0;
        long multiplier = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            result += input.charAt(i) * multiplier;
            result %= m;
            multiplier = (multiplier * a) % m;
        }

        return result;
    }
}
