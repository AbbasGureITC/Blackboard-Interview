import J.J6;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        runTest(List.of("deposit", "withdrawal", "deposit", "transfer", "deposit"),
                Map.of("deposit", 3, "withdrawal", 1, "transfer", 1),
                "mixed types");
        runTest(List.of(),
                Map.of(),
                "empty log");
        runTest(List.of("withdrawal", "withdrawal", "withdrawal"),
                Map.of("withdrawal", 3),
                "single type repeated");
        runTest(List.of("deposit"),
                Map.of("deposit", 1),
                "single entry");
        runTest(List.of("transfer", "deposit", "withdrawal"),
                Map.of("transfer", 1, "deposit", 1, "withdrawal", 1),
                "one of each");
    }

    private static void runTest(List<String> types, Map<String, Integer> expected, String label) {
        Map<String, Integer> actual = J6.countByType(types);
        boolean pass = actual != null && actual.equals(expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
