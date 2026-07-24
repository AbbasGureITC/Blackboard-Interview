import J.J3;

public class Main {
    public static void main(String[] args) {
        runTest(new int[]{100, 250, 80, 400, 300}, 400, "peak in the middle");
        runTest(new int[]{500, 400, 300, 200}, 500, "peak on day one");
        runTest(new int[]{100, 200, 300, 450}, 450, "peak on last day");
        runTest(new int[]{-50, -20, -100}, -20, "all negative balances (overdrawn all month)");
        runTest(new int[]{750}, 750, "single day");
    }

    private static void runTest(int[] input, int expected, String label) {
        int actual = J3.highestBalance(input);
        boolean pass = actual == expected;
        System.out.printf("[%s] %s - expected=%d actual=%d%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}