import J.J5;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of(101, 102, 103, 104), List.of(103, 104, 105, 106),
                List.of(103, 104), "some overlap");
        runTest(List.of(101, 102), List.of(201, 202),
                List.of(), "no overlap");
        runTest(List.of(101, 102, 103), List.of(101, 102, 103),
                List.of(101, 102, 103), "identical lists");
        runTest(List.of(), List.of(101, 102),
                List.of(), "one branch has no accounts");
        runTest(List.of(101, 101, 102), List.of(101, 103),
                List.of(101), "duplicate account number within a branch");
    }

    private static void runTest(List<Integer> branchA, List<Integer> branchB,
                                 List<Integer> expected, String label) {
        List<Integer> actual = J5.sharedAccounts(branchA, branchB);
        boolean pass = actual != null
                && new java.util.HashSet<>(actual).equals(new java.util.HashSet<>(expected))
                && actual.size() == new java.util.HashSet<>(actual).size();
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
