import M.M1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of("A", "B", "C", "B", "D"), "B", "duplicate appears mid-stream");
        runTest(List.of("A", "B", "C"), null, "no duplicates");
        runTest(List.of("A", "A"), "A", "immediate duplicate");
        runTest(List.of("A", "B", "A", "B"), "A", "first repeat wins even if others repeat later");
        runTest(List.of(), null, "empty stream");
    }

    private static void runTest(List<String> transactionIds, String expected, String label) {
        String actual = M1.firstDuplicate(transactionIds);
        boolean pass = actual == null ? expected == null : actual.equals(expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}