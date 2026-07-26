package J;

// End of day, ops wants a quick breakdown of the day's activity: how many
// deposits, withdrawals, and transfers went through the branch. You've got
// the full list of transaction types as they happened. Your move.

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class J6 {
    public static Map<String, Integer> countByType(List<String> types) {
        Map<String, Integer> typeCounter = new HashMap<>(Map.of(
                "deposit", 0,
                "withdrawal", 0,
                "transfer", 0
        ));

        types.forEach( t -> typeCounter.merge(t,1, Integer::sum));

        return typeCounter;
    }

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of("deposit", "withdrawal", "deposit", "transfer", "deposit"),
    //             Map.of("deposit", 3, "withdrawal", 1, "transfer", 1),
    //             "mixed types");
    //     runTest(List.of(),
    //             Map.of("deposit", 0, "withdrawal", 0, "transfer", 0),
    //             "empty log");
    //     runTest(List.of("withdrawal", "withdrawal", "withdrawal"),
    //             Map.of("deposit", 0, "withdrawal", 3, "transfer", 0),
    //             "single type repeated");
    //     runTest(List.of("deposit"),
    //             Map.of("deposit", 1, "withdrawal", 0, "transfer", 0),
    //             "single entry");
    //     runTest(List.of("transfer", "deposit", "withdrawal"),
    //             Map.of("transfer", 1, "deposit", 1, "withdrawal", 1),
    //             "one of each");
    // }
    //
    // private static void runTest(List<String> types, Map<String, Integer> expected, String label) {
    //     Map<String, Integer> actual = J.J6.countByType(types);
    //     boolean pass = actual != null && actual.equals(expected);
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
