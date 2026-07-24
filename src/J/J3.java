package J;

// The retention team wants to pitch a premium tier to customers who've hit a
// high balance recently. You've got one customer's daily balance snapshots
// for the month — find the peak. Your move.

import java.util.Arrays;

public class J3 {
    public static int highestBalance(int[] dailyBalances) {
        return Arrays.stream(dailyBalances)
                .reduce(Integer.MIN_VALUE, Math::max);
    }

    /*
     *  .max() version, for reference (the purpose-built way to do this):

     public static int highestBalance(int[] dailyBalances) {
         return Arrays.stream(dailyBalances).max().getAsInt();
     }

     How it reads: IntStream has a terminal op built for exactly this —
     `.max()` walks the stream and returns an OptionalInt holding the
     largest element, or empty if the stream had none. `.getAsInt()`
     unwraps it.

     Why this is arguably preferred over the reduce() version above: two
     reasons, not just style.
     1) Empty input: reduce() with a seed silently returns that seed
     (Integer.MIN_VALUE) if the array is empty, hiding the "no data" case
     behind a number that looks like real data. .max() returns an empty
     OptionalInt instead, forcing the caller to notice and decide what to
     do.
     2) The seed is a footgun: pick 0 instead of Integer.MIN_VALUE (an
     easy mistake, since balances can be negative) and reduce() silently
     breaks on an all-negative-balances input. .max() needs no seed, so
     there's nothing to get wrong.

     reduce() is still worth knowing as the general-purpose fallback for
     when no purpose-built terminal op exists (see J1's sum) — but when
     one does exist and fits, like .max() here, it's the better instinct.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(new int[]{100, 250, 80, 400, 300}, 400, "peak in the middle");
    //     runTest(new int[]{500, 400, 300, 200}, 500, "peak on day one");
    //     runTest(new int[]{100, 200, 300, 450}, 450, "peak on last day");
    //     runTest(new int[]{-50, -20, -100}, -20, "all negative balances (overdrawn all month)");
    //     runTest(new int[]{750}, 750, "single day");
    // }
    //
    // private static void runTest(int[] input, int expected, String label) {
    //     int actual = J.J3.highestBalance(input);
    //     boolean pass = actual == expected;
    //     System.out.printf("[%s] %s - expected=%d actual=%d%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}