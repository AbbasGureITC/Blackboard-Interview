import J.J10;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of(100, 600, 250, 900, 50), 500,
                List.of(600, 900), "some withdrawals over limit");
        runTest(List.of(100, 200, 300), 500,
                List.of(), "none over limit");
        runTest(List.of(600, 700, 800), 500,
                List.of(600, 700, 800), "all over limit");
        runTest(List.of(), 500,
                List.of(), "no withdrawals today");
        runTest(List.of(500, 501), 500,
                List.of(501), "exactly at limit is not over");
    }

    private static void runTest(List<Integer> withdrawals, int dailyLimit, List<Integer> expected, String label) {
        List<Integer> actual = J10.overLimit(withdrawals, dailyLimit);
        boolean pass = actual != null && actual.equals(expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
