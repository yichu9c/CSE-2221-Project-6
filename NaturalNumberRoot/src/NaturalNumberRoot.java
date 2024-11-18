import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Yihone Chu
 *
 */
public final class NaturalNumberRoot {
    /**
     * Class that finds root of a number.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-the root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {

        final NaturalNumber zero = new NaturalNumber2(0);
        final NaturalNumber one = new NaturalNumber2(1);
        final NaturalNumber two = new NaturalNumber2(2);

        NaturalNumber lowEnough = new NaturalNumber2(0);
        NaturalNumber highEnough = new NaturalNumber2(n);
        highEnough.increment();

        boolean play = true;
        // exit if number is less than 2
        if (n.equals(one) || n.equals(zero)) {
            play = false;
        }

        while (play) {

            NaturalNumber guess = new NaturalNumber2();
            guess.add(highEnough);
            guess.add(lowEnough);
            guess.divide(two);

            NaturalNumber guessPowered = new NaturalNumber2(guess);
            guessPowered.power(r);

            int comparison = guessPowered.compareTo(n);

            //if guess is on the money, stop loop change n
            if (comparison == 0) {
                play = false;
                n.copyFrom(guess);
            } else if (comparison > 0) {
                // if guess powered is larger than n
                highEnough = new NaturalNumber2(guess);
            } else if (comparison < 0) {
                // if guess powered is less than n
                lowEnough = new NaturalNumber2(guess);
            }

            //If lowEnough and highEnough are back to back ex 4 and 5 it stops
            // while loop and changes n to lowEnough.
            NaturalNumber tempHigh = new NaturalNumber2(highEnough);
            tempHigh.subtract(lowEnough);
            if (tempHigh.equals(one)) {
                n.copyFrom(lowEnough);
                play = false;
            }

        }
    }

    /**
     * Main method.
     *
     * @param args
     *            Tests the root method with values
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);

            root(n, roots[i]);

            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }
}
