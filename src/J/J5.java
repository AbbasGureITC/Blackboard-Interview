package J;

// Two branches just merged their systems, and finance needs a list of
// account numbers that exist on both sides before they can reconcile
// balances. You've got each branch's list of account numbers. Your move.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class J5 {
    public static List<Integer> sharedAccounts(List<Integer> branchA, List<Integer> branchB) {
        Set<Integer> bSet = new HashSet<>(branchB);
        return branchA.stream()
                .filter(bSet::contains)
                .sorted()
                .distinct()
                .toList();
    }

    /*
     *  How the stream version above reads: `bSet` is a HashSet built from
     *  branchB, so asking "is this account in branchB?" is O(1) instead
     *  of scanning a list. `.filter(bSet::contains)` walks branchA and
     *  keeps only the accounts that also exist in bSet - everything else
     *  is dropped. `.sorted()` puts what's left in numeric order (a
     *  HashSet-based lookup doesn't preserve branchA's original order,
     *  so this makes the output deterministic instead of arbitrary).
     *  `.distinct()` removes repeats - relevant if branchA itself had the
     *  same account number listed twice. `.toList()` is the terminal
     *  step: it collects everything still in the stream into an actual
     *  List<Integer> to return.
     */

    /*
     *  two-pointer version, for reference:

     public static List<Integer> sharedAccountsTwoPointer(List<Integer> branchA, List<Integer> branchB) {
         List<Integer> a = new ArrayList<>(new TreeSet<>(branchA));
         List<Integer> b = new ArrayList<>(new TreeSet<>(branchB));
         List<Integer> result = new ArrayList<>();
         int i = 0, j = 0;
         while (i < a.size() && j < b.size()) {
             int av = a.get(i), bv = b.get(j);
             if (av == bv) {
                 result.add(av);
                 i++;
                 j++;
             } else if (av < bv) {
                 i++;
             } else {
                 j++;
             }
         }
         return result;
     }

     How it reads: TreeSet sorts and dedupes both lists in one step. Then
     two pointers, i into a and j into b, walk forward together. At each
     step there are three cases: the values match (record it, advance
     both), a's value is smaller (only i advances, since nothing smaller
     than av can still be in b), or b's value is smaller (only j
     advances). Because both lists are sorted, once a pointer passes a
     value it never needs to look back - each pointer only moves forward,
     so the whole walk is O(n+m).

     Why the HashSet version above is preferred here: branchA/branchB
     arrive unsorted, so this version pays a sort cost (via TreeSet,
     O((n+m) log(n+m))) before it can even start walking - no cheaper
     than the HashSet approach's O(n+m), and more code. Two-pointer only
     pulls ahead when the inputs are *already* sorted, since then there's
     no sort cost at all: O(n+m) time, O(1) extra space (no hash tables).
     That's the exact shape of M6 (statement reconciliation, two sorted
     int[] arrays) later in the drill - worth returning to this version
     then, when the "already sorted" assumption actually holds.
     */

    // Test cases used in Main.java to verify this solution when the drill was active:
    //
    // public static void main(String[] args) {
    //     runTest(List.of(101, 102, 103, 104), List.of(103, 104, 105, 106),
    //             List.of(103, 104), "some overlap");
    //     runTest(List.of(101, 102), List.of(201, 202),
    //             List.of(), "no overlap");
    //     runTest(List.of(101, 102, 103), List.of(101, 102, 103),
    //             List.of(101, 102, 103), "identical lists");
    //     runTest(List.of(), List.of(101, 102),
    //             List.of(), "one branch has no accounts");
    //     runTest(List.of(101, 101, 102), List.of(101, 103),
    //             List.of(101), "duplicate account number within a branch");
    // }
    //
    // private static void runTest(List<Integer> branchA, List<Integer> branchB,
    //                              List<Integer> expected, String label) {
    //     List<Integer> actual = J.J5.sharedAccounts(branchA, branchB);
    //     boolean pass = actual != null
    //             && new java.util.HashSet<>(actual).equals(new java.util.HashSet<>(expected))
    //             && actual.size() == new java.util.HashSet<>(actual).size();
    //     System.out.printf("[%s] %s - expected=%s actual=%s%n",
    //             pass ? "PASS" : "FAIL", label, expected, actual);
    // }
}
