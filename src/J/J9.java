package J;

// An auditor wants to review today's transaction log starting from the most recent entry and working backwards.
// You've got the log entries in the order they happened. Your move.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class J9 {
    public static List<String> mostRecentFirst(List<String> log) {
        List<String> lister = new ArrayList<>(log);
        Collections.reverse(lister);
        return lister;
    }

    /*
     *  stream alternative, for reference (index-based, not value-based):

     public static List<String> mostRecentFirst(List<String> log) {
         return java.util.stream.IntStream.rangeClosed(1, log.size())
                 .mapToObj(i -> log.get(log.size() - i))
                 .toList();
     }

     Why Collections.reverse is preferred here: this problem is about
     position, not content - there's nothing to transform, filter, or
     reduce, just "give me the same elements, walked backwards." Streams
     are built around processing elements one at a time without needing
     to know their index, so forcing this into a stream means manually
     doing index math (log.size() - i) that Collections.reverse doesn't
     need at all. It's also worse Big-O for a List like LinkedList, where
     .get(index) is O(n) per call - looping that n times makes the stream
     version O(n^2), versus Collections.reverse's O(n) in-place swap.
     Recognizing "this isn't a streams problem" is as much a skill as
     knowing stream syntax.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of("login", "deposit $100", "withdrawal $40", "logout"),
    //             List.of("logout", "withdrawal $40", "deposit $100", "login"),
    //             "typical sequence");
    //     runTest(List.of(),
    //             List.of(),
    //             "empty log");
    //     runTest(List.of("login"),
    //             List.of("login"),
    //             "single entry");
    //     runTest(List.of("deposit $50", "deposit $50"),
    //             List.of("deposit $50", "deposit $50"),
    //             "duplicate entries");
    //     runTest(List.of("a", "b", "c", "d", "e"),
    //             List.of("e", "d", "c", "b", "a"),
    //             "five entries");
    // }
    //
    // private static void runTest(List<String> log, List<String> expected, String label) {
    //     List<String> actual = J.J9.mostRecentFirst(log);
    //     boolean pass = actual != null && actual.equals(expected);
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
