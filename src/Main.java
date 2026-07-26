import J.J4;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        runTest(List.of("C100", "C200", "C100", "C300", "C100", "C200"),
                Map.of("C100", 3, "C200", 2, "C300", 1),
                "mixed repeat counts");
        runTest(List.of("C100", "C200", "C300"),
                Map.of("C100", 1, "C200", 1, "C300", 1),
                "no repeats");
        runTest(List.of(),
                Map.of(),
                "empty log");
        runTest(List.of("C100"),
                Map.of("C100", 1),
                "single call");
        runTest(List.of("C100", "C100", "C100"),
                Map.of("C100", 3),
                "same customer calling repeatedly");
    }

    private static void runTest(List<String> input, Map<String, Integer> expected, String label) {
        Map<String, Integer> actual = J4.callCounts(input);
        boolean pass = actual != null && actual.equals(expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
