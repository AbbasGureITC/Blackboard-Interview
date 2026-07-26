import J.J8;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of(100, 250, 80, 400, 300),
                new int[]{80, 400}, "spread of amounts");
        runTest(List.of(500, 400, 300, 200),
                new int[]{200, 500}, "descending amounts");
        runTest(List.of(-50, -20, -100),
                new int[]{-100, -20}, "all negative amounts (refunds/reversals)");
        runTest(List.of(750),
                new int[]{750, 750}, "single transaction");
        runTest(List.of(100, 100, 100),
                new int[]{100, 100}, "identical amounts");
    }

    private static void runTest(List<Integer> amounts, int[] expected, String label) {
        int[] actual = J8.minAndMax(amounts);
        boolean pass = actual != null && Arrays.equals(actual, expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, Arrays.toString(expected),
                actual == null ? "null" : Arrays.toString(actual));
    }
}
