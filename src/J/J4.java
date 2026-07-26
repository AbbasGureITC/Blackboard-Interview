package J;

// A call center supervisor wants to know which customer IDs are calling in
// the most this week, based on the raw call log. Your move.

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class J4 {
    public static Map<String, Integer> callCounts(List<String> customerIds) {
        return customerIds.stream()
                .collect(Collectors.groupingBy(id -> id, Collectors.summingInt(id -> 1)));
    }

    /*
     *  counting() version, for reference:

     public static Map<String, Long> callCounts(List<String> customerIds) {
         return customerIds.stream()
                 .collect(Collectors.groupingBy(id -> id, Collectors.counting()));
     }

     How it reads: groupingBy does two jobs. First, `id -> id` decides
     which group each customer ID lands in (just "group it under itself").
     Second, once a group is built, `counting()` runs through it and
     returns how many items ended up there - the same as looping through
     the group and adding 1 each time, just done automatically.

     Only difference from the summingInt version above: counting() always
     hands back a Long, because that's simply what it was built to
     return. summingInt(id -> 1) does the exact same "add 1 per item"
     job, but produces an Integer instead - useful when the method's
     return type is already fixed to Map<String, Integer> and you can't
     just switch it to Long.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of("C100", "C200", "C100", "C300", "C100", "C200"),
    //             Map.of("C100", 3, "C200", 2, "C300", 1),
    //             "mixed repeat counts");
    //     runTest(List.of("C100", "C200", "C300"),
    //             Map.of("C100", 1, "C200", 1, "C300", 1),
    //             "no repeats");
    //     runTest(List.of(),
    //             Map.of(),
    //             "empty log");
    //     runTest(List.of("C100"),
    //             Map.of("C100", 1),
    //             "single call");
    //     runTest(List.of("C100", "C100", "C100"),
    //             Map.of("C100", 3),
    //             "same customer calling repeatedly");
    // }
    //
    // private static void runTest(List<String> input, Map<String, Integer> expected, String label) {
    //     Map<String, Integer> actual = J.J4.callCounts(input);
    //     boolean pass = actual != null && actual.equals(expected);
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
