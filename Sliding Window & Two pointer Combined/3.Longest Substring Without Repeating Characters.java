/* Problem: Longest Substring Without Repeating Characters

We are given a string s, and we need to find the length of the longest substring with all unique characters.

Key Observations:
Substring = contiguous
Need maximum length
Constraint = no repeating characters
🚀 Approach 1: Brute Force
💡 Idea:
Try all possible substrings
For each starting index i, expand till you find a duplicate
Use a hash array to track characters
🔍 Example:
s = "cadbzabcd"

Start at i = 0:
c → a → d → b → z (valid)
Next 'a' repeats → stop

Longest = "cadbz" → length = 5
Code (Brute Force) */
import java.util.*;

class Solution {

    // Brute Force Approach
    public int longestNonRepeatingSubstring_BF(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] hash = new int[256]; // ASCII characters
            Arrays.fill(hash, 0);

            for (int j = i; j < n; j++) {
                if (hash[s.charAt(j)] == 1) break;

                hash[s.charAt(j)] = 1;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }
}
/*
TC: O(n^2)
SC: O(1) (since 256 is constant)
*/

/* Approach 2: Optimal (Sliding Window + Two Pointers)
💡 Idea:
Use two pointers (l, r) → window [l, r]
Expand r
If duplicate found → move l forward
Store last seen index of characters
🔥 Key Trick:

Instead of checking all chars → jump l directly:

l = last_occurrence + 1
🔍 Example:
s = "abba"

r = 0 → 'a'
r = 1 → 'b'
r = 2 → 'b' (duplicate)

Move l → index of previous 'b' + 1 = 2

Window becomes "b"
Code (Optimal) */
import java.util.*;

class Solution {

    // Optimal Sliding Window Approach
    public int longestNonRepeatingSubstring_Optimal(String s) {
        int n = s.length();

        int[] hash = new int[256];
        Arrays.fill(hash, -1);

        int l = 0, maxLen = 0;

        for (int r = 0; r < n; r++) {

            if (hash[s.charAt(r)] >= l) {
                l = hash[s.charAt(r)] + 1;
            }

            maxLen = Math.max(maxLen, r - l + 1);

            hash[s.charAt(r)] = r;
        }

        return maxLen;
    }
}
/*
TC: O(n) (each element processed once)
SC: O(1) (fixed hash array) */
