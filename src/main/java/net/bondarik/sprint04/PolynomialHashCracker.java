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
                char next = 'a' + j;
                //String next =
            }
        }

    }

    private static String findHash(long target, String prefix, long a, long m) {
        if (prefix.length() > 6) {
            return "error";
        }

        for (int j = 0; j < (int)('z' - 'a'); j++) {
            char nextChar = (char) (j + 'a');
            String next = prefix + nextChar;
            if (PolynomialHashCalc.getHash(next, a, m) == target) {
                return next;
            } else {
                return findHash(target, next, a, m);
            }
        }
    }

}
