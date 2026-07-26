import J.J9;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of("login", "deposit $100", "withdrawal $40", "logout"),
                List.of("logout", "withdrawal $40", "deposit $100", "login"),
                "typical sequence");
        runTest(List.of(),
                List.of(),
                "empty log");
        runTest(List.of("login"),
                List.of("login"),
                "single entry");
        runTest(List.of("deposit $50", "deposit $50"),
                List.of("deposit $50", "deposit $50"),
                "duplicate entries");
        runTest(List.of("a", "b", "c", "d", "e"),
                List.of("e", "d", "c", "b", "a"),
                "five entries");
    }

    private static void runTest(List<String> log, List<String> expected, String label) {
        List<String> actual = J9.mostRecentFirst(log);
        boolean pass = actual != null && actual.equals(expected);
        System.out.printf("[%s] %s - expected=%s actual=%s%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
