import java.util.List;

public class Main {
    public static void main(String[] args) {
        runTest(List.of("Alice", "Bob", "Carol"), false, "no duplicates");
        runTest(List.of("Alice", "Bob", "Alice"), true, "one duplicate");
        runTest(List.of(), false, "empty list");
        runTest(List.of("Alice"), false, "single name");
        runTest(List.of("Alice", "alice"), false, "different case treated as different person");
    }

    private static void runTest(List<String> input, boolean expected, String label) {
        boolean actual = J2.hasDuplicateSignup(input);
        boolean pass = actual == expected;
        System.out.printf("[%s] %s - expected=%b actual=%b%n",
                pass ? "PASS" : "FAIL", label, expected, actual);
    }
}
