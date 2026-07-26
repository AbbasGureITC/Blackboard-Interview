package J;

// Compliance wants a quick sanity check on today's transactions
// The smallest and the largest amount that went through, in one look.
// You've got the full list of transaction amounts. Your move.

import java.util.IntSummaryStatistics;
import java.util.List;

public class J8 {
    public static int[] minAndMax(List<Integer> amounts) {

        IntSummaryStatistics stats = amounts.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        return new int[]{ stats.getMin(), stats.getMax() };
    }

    /*
     *  double-reduce version, first attempt:

     public static int[] minAndMax(List<Integer> amounts) {
         int largest = amounts.stream().reduce(Integer.MIN_VALUE, Math::max);
         int smallest = amounts.stream().reduce(Integer.MAX_VALUE, Math::min);
         return new int[]{smallest, largest};
     }

     Both versions are O(n) - Big-O drops constant factors, so "two passes"
     and "one pass" are the same complexity class. summaryStatistics() is
     preferred anyway for two real (non-Big-O) reasons: it does roughly half
     the actual work (one walk through the list instead of two), and it
     exposes stats.getCount(), an explicit way to check "was there any data
     at all?" before trusting getMin()/getMax() - same empty-input footgun
     as J3's reduce-with-seed version otherwise (an empty list silently
     returns {Integer.MAX_VALUE, Integer.MIN_VALUE} instead of signaling
     "no data").
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of(100, 250, 80, 400, 300),
    //             new int[]{80, 400}, "spread of amounts");
    //     runTest(List.of(500, 400, 300, 200),
    //             new int[]{200, 500}, "descending amounts");
    //     runTest(List.of(-50, -20, -100),
    //             new int[]{-100, -20}, "all negative amounts (refunds/reversals)");
    //     runTest(List.of(750),
    //             new int[]{750, 750}, "single transaction");
    //     runTest(List.of(100, 100, 100),
    //             new int[]{100, 100}, "identical amounts");
    // }
    //
    // private static void runTest(List<Integer> amounts, int[] expected, String label) {
    //     int[] actual = J.J8.minAndMax(amounts);
    //     boolean pass = actual != null && java.util.Arrays.equals(actual, expected);
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, java.util.Arrays.toString(expected),
    //             actual == null ? "null" : java.util.Arrays.toString(actual));
    // }
}