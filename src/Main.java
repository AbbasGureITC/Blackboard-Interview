import J.J7;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest("John Smith", List.of("Jane Doe", "John Smith", "Bob Lee"),
                true, "customer is blacklisted");
        runTest("Alice Wong", List.of("Jane Doe", "Bob Lee"),
                false, "customer not blacklisted");
        runTest("Anyone", List.of(),
                false, "empty blacklist");
        runTest("Bob Lee", List.of("Bob Lee"),
                true, "single-entry blacklist match");
        runTest("Jane Doe", List.of("Jane Doe", "Jane Doe"),
                true, "duplicate entries in blacklist");
    }

    private static void runTest(String customerName, List<String> blacklist, boolean expected, String label) {
        boolean actual = J7.isBlacklisted(customerName, blacklist);
        boolean pass = actual == expected;
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
