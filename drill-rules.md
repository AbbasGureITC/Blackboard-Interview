# Java Coding Drill — Spaced Repetition Rules

State lives in `progress.md` as a JSON block: `tier`, `sentInTier` (IDs already
sent in the current tier), and `history` (list of `{date, id, topic}`).

Cadence is weekly: assume the previous drill was completed, and each time the
user asks for "this week's drill" (or "today's drill"), give exactly one new
problem — never re-send one already logged in `sentInTier` for the current
tier.

## When asked for "this week's drill"

1. Read `progress.md`.
2. Pick one problem from the current tier's pool below that is NOT in
   `sentInTier`. If the whole tier has been sent, advance
   junior -> mid -> senior (senior loops back with `sentInTier` reset), and
   pick from the new tier.
3. Reply with ONLY the scenario, 1-2 sentences, real-world flavored, ending
   with a short sign-off like "Your move." Do NOT reveal which data
   structure or algorithm it maps to, and do NOT give a hint, approach, or
   solution.
4. LeetCode-style harness: write `src/<ID>.java` containing the scenario as a
   comment plus a stub method (right signature, `TODO` body, no solution
   logic) for the user to implement. Overwrite `src/Main.java` with a
   `runTest(input, expected, label)` helper and a handful of hardcoded test
   scenarios (including edge cases) that call the stub and print PASS/FAIL.
   The user should be able to just run `Main` to see if their implementation
   passes — don't make them wire up their own test cases.
5. Append the chosen ID to `sentInTier` and to `history` (with today's date
   and a short topic label, not the pattern name) in `progress.md`, then
   commit with a message like "drill: sent J3".
6. If the user replies with code afterward, react with feedback only —
   correctness, missed edge cases, time/space complexity, whether the data
   structure choice is efficient enough. Never give the full solution
   unless explicitly asked (e.g. "show me the solution", "just tell me").

## JUNIOR POOL (J1-J10)

- J1. Given a List<Double> of today's transaction amounts, return the total spent.
- J2. Given a List<String> of account holder names, return true if any name appears more than once.
- J3. Given an int[] of a customer's daily balances, find the highest balance reached this month.
- J4. Given a List<String> of customer IDs from a call center log, return how many times each ID appears (Map<String,Integer>).
- J5. Given two List<Integer> of account numbers from two different branches, return the account numbers that exist in both.
- J6. Given a List<String> of transaction types ("deposit", "withdrawal", "transfer"), count how many of each type occurred.
- J7. Given a String customer name and a List<String> of blacklisted names, return whether the customer is blacklisted.
- J8. Given a List<Integer> of transaction amounts, return the largest and the smallest transaction.
- J9. Given a List<String> of transaction log entries in the order they happened, return them in reverse (most recent first).
- J10. Given a List<Integer> of ATM withdrawal amounts and an int dailyLimit, return the list of amounts that individually exceed the limit.

## MID POOL (M1-M12)

- M1. Duplicate charge detector. Streaming transaction IDs. Return the first ID that shows up a second time.
- M2. Branch locator. A customer's current address (String) and a List<String> of branch addresses. Return the 3 nearest branches.
- M3. Spending spike flag. A chronological List<Double> of amounts and int windowSize. Find the max total spent across any windowSize consecutive transactions.
- M4. Session cache with a limit. At most N active sessions; evict least-recently-used when full.
- M5. Duplicate customer merge. Combined List<String> of emails, same person may differ in casing. Return the deduplicated, case-insensitive set.
- M6. Statement reconciliation. Two sorted int[] arrays of amounts (bank vs internal). Return amounts present in both.
- M7. Failed payment retry queue. Retries happen in original-fail order; a payment failing again goes to the back of the line.
- M8. Undo last trade. A structure that undoes the trader's most recent action, repeatedly if called again.
- M9. Reporting chain lookup. Map<String, List<String>> of manager to direct reports. Return everyone under a given manager, direct and indirect.
- M10. Portfolio total value. A fund can hold cash and/or nested funds (a tree). Compute total value from the root.
- M11. Top-5 traders leaderboard. Continuous stream of (traderId, profit). Report current top 5 by profit at any point.
- M12. Audit log ordering. Letter-logs (readable) sort alphabetically by content first; digit-logs (system codes) keep original relative order after.

## SENIOR POOL (S1-S8)

- S1. Merge k statement feeds. k already-sorted List<Integer> transaction streams. Merge into one sorted stream efficiently (not concatenate-then-sort).
- S2. Merchant autocomplete. A large List<String> of merchant names. Support fast prefix search. What structure beats a linear scan?
- S3. Approval cycle detection. A payment approval workflow as a directed graph (Map<String, List<String>>, approver to who they can escalate to). Detect if a cycle exists.
- S4. Sliding window max balance. A stream of balance snapshots and window size k. Report the max balance in the current window after every new snapshot, better than O(n·k).
- S5. Thread-safe rate limiter. At most N requests per user per rolling 60-second window, safe under concurrent access. What's the data structure and what needs synchronization?
- S6. LFU eviction cache. Like the session cache, but evict Least Frequently Used, ties broken by recency.
- S7. Interval merge for account holds. A List<int[]> of [start, end] timestamp ranges where an account was on hold (possibly overlapping). Merge into minimal non-overlapping periods.
- S8. Consistent top-K under updates. A live top-K leaderboard where values can also decrease (a trade reversed) — needs efficient updates, not just inserts.
