package net.bondarik.sprint04;


public class PolynomialHashCracker {

    public static void main(String[] args) {
        long base = 1000L;
        long mod = 123987123L;

        String asd = "dkbashfelnvzdlkb";

        long target = PolynomialHashCalc.getHash(asd, base, mod);

        String prefix = "";
        for (int i = 0; i < 50; i++) {

            for (int j = 0; j < 'z' - 'a'; j++) {
                char next = (char) ('a' + j);
                //String next =
            }
        }

    }


}
