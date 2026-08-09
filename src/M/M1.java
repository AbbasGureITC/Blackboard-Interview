package M;

// The payment gateway streams transaction IDs as charges come in, and a bug
// upstream is occasionally resending the same charge twice. You need to catch
// the moment it happens, not just know it happened somewhere in the batch.
// Given the IDs in the order they arrived, return the first one that shows up
// a second time. Your move.

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M1 {
    public static String firstDuplicate(List<String> transactionIds) {
        Set<String> trans = new HashSet<>();
        return transactionIds.stream().filter(e -> !trans.add(e)).findFirst().orElse(null);
    }

    /*
     Why Set.add as the filter condition: Set.add(e) does both jobs at once
     - it returns true and records e if e wasn't seen before, or returns
     false (leaving the set unchanged) if e was already there. Negating that
     turns the filter into "keep only IDs we've already recorded," i.e. the
     repeats, in original order. findFirst() then short-circuits on the
     earliest one instead of scanning the rest of the list.

     This is O(n) time / O(n) space, which is the floor for this problem -
     every ID needs to be looked at once, and something has to remember
     what's already been seen. Matches the plain-loop version, just
     expressed as a stream.

     Caveat: this only works because .stream() processes sequentially. The
     filter lambda mutates trans as a side effect, so switching to
     .parallelStream() would race on the shared set and give wrong or
     nondeterministic results.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of("A", "B", "C", "B", "D"), "B", "duplicate appears mid-stream");
    //     runTest(List.of("A", "B", "C"), null, "no duplicates");
    //     runTest(List.of("A", "A"), "A", "immediate duplicate");
    //     runTest(List.of("A", "B", "A", "B"), "A", "first repeat wins even if others repeat later");
    //     runTest(List.of(), null, "empty stream");
    // }
    //
    // private static void runTest(List<String> transactionIds, String expected, String label) {
    //     String actual = M.M1.firstDuplicate(transactionIds);
    //     boolean pass = actual == null ? expected == null : actual.equals(expected);
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}