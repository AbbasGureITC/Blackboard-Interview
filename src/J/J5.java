package J;

// Two branches just merged their systems, and finance needs a list of
// account numbers that exist on both sides before they can reconcile
// balances. You've got each branch's list of account numbers. Your move.

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class J5 {
    public static List<Integer> sharedAccounts(List<Integer> branchA, List<Integer> branchB) {
        Set<Integer> bSet = new HashSet<>(branchB);
        return branchA.stream()
                .filter(bSet::contains)
                .sorted()
                .distinct()
                .toList();
    }
}
