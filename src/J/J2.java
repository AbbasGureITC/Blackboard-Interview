package J;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// A bank teller hands you the list of names on today's new-account signups,
// and compliance wants to know if any single person opened more than one
// account today. Your move.

public class J2 {
    public static boolean hasDuplicateSignup(List<String> names) {
        Set<String> exists = new HashSet<>();
        boolean chance = false;
        for(String name : names){
            if(exists.contains(name)){
                chance =true;
            } else exists.add(name);
        }

        return chance;
    }

    /*
     *  Stream version, for reference:

     public static boolean hasDuplicateSignup(List<String> names) {
         return names.stream().distinct().count() != names.size();
     }

     How it reads: `.distinct()` returns a new stream with duplicate
     elements removed (it uses equals()/hashCode() under the hood, backed
     by its own Set-like tracking, similar in spirit to your HashSet).
     `.count()` terminates the stream and gives you how many elements are
     left after dedup. If that number is smaller than the original list
     size, at least one name was removed as a duplicate, so a mismatch
     means "yes, someone appears more than once".

     Same Big-O as your loop version (O(n) time, O(n) space for the
     internal dedup tracking) — it's not more efficient, just more
     declarative/readable. It also can't short-circuit early like your
     HashSet version can, since .count() has to consume the whole stream
     regardless of when the first duplicate shows up.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of("Alice", "Bob", "Carol"), false, "no duplicates");
    //     runTest(List.of("Alice", "Bob", "Alice"), true, "one duplicate");
    //     runTest(List.of(), false, "empty list");
    //     runTest(List.of("Alice"), false, "single name");
    //     runTest(List.of("Alice", "alice"), false, "different case treated as different person");
    // }
    //
    // private static void runTest(List<String> input, boolean expected, String label) {
    //     boolean actual = J.J2.hasDuplicateSignup(input);
    //     boolean pass = actual == expected;
    //     System.out.printf("[%s] %s - expected=%b actual=%b%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
