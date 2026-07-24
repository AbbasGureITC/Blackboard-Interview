package J;

import java.util.List;

// You're closing out the books for a merchant account and need the end-of-day
// reconciliation number: reduce today's list of transaction amounts down to
// a single total spent. Your move.

public class J1 {
    public static double totalSpent(List<Double> amounts) {
        // Maths stream, reduce used to collate all answers into singular value, used stream since we dealing with list
        return amounts.stream()
                .reduce(0d, Double::sum);
    }

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of(10.0, 20.0, 30.0), 60.0, "basic sum");
    //     runTest(List.of(), 0.0, "empty list");
    //     runTest(List.of(5.5), 5.5, "single element");
    //     runTest(List.of(-10.0, 20.0), 10.0, "includes a refund/negative amount");
    // }
    //
    // private static void runTest(List<Double> input, double expected, String label) {
    //     double actual = J.J1.totalSpent(input);
    //     boolean pass = Math.abs(actual - expected) < 1e-9;
    //     System.out.printf("[%s] %s - expected=%.2f actual=%.2f%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
