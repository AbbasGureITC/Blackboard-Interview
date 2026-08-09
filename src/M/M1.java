package M;

// The payment gateway streams transaction IDs as charges come in, and a bug
// upstream is occasionally resending the same charge twice. You need to catch
// the moment it happens, not just know it happened somewhere in the batch.
// Given the IDs in the order they arrived, return the first one that shows up
// a second time. Your move.

import java.util.List;

public class M1 {
    public static String firstDuplicate(List<String> transactionIds) {
        // TODO
        return null;
    }
}