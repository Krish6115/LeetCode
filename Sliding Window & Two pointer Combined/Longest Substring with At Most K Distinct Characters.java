import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Longest Substring with At Most K Distinct Characters
    ============================================

    Given a string s and an integer k,
    return the length of the longest substring
    containing at most k distinct characters.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Longest substring
    👉 At most k distinct characters

    This is a classic:
    ❗ Variable Size Sliding Window

    Key Idea:
    - Expand window using right pointer
    - If distinct characters exceed k
      → shrink from left

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    - Generate all substrings
    - Count distinct characters
    - If distinct ≤ k → update answer

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "eceba"
    k = 2

    Valid substrings:
    "ec"
    "ece"
    "ba"

    Longest = "ece"
    Answer = 3

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(n^2)

    Space Complexity: O(256) ≈ O(1)
    */

    public int lengthOfLongestSubstringKDistinct_BF(String s, int k) {

        int n = s.length();

        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            int[] freq = new int[256];

            int distinct = 0;

            for (int j = i; j < n; j++) {

                if (freq[s.charAt(j)] == 0)
                    distinct++;

                freq[s.charAt(j)]++;

                if (distinct > k)
                    break;

                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Sliding Window + HashMap)
    --------------------------------------------

    Maintain:
    - HashMap → frequency of characters
    - left and right pointers

    Expand:
    - Add character at right

    Shrink:
    - While distinct chars > k
      remove from left

    Window always remains valid.

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "eceba"
    k = 2

    Window progression:

    "e" → valid
    "ec" → valid
    "ece" → valid
    "eceb" → 3 distinct ❌

    Shrink from left:
    "ceb" → still 3 distinct
    "eb" → valid

    Maximum = 3

    --------------------------------------------
    ⚠️ IMPORTANT OBSERVATION:
    --------------------------------------------

    HashMap size represents:
    👉 number of distinct characters

    If map size > k:
    → window invalid
    → shrink from left

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
    --------------------------------------------

    Time Complexity: O(n)

    Space Complexity: O(k)

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Longest substring/subarray with at most K..."

    Standard Template:
    1. Expand right
    2. If invalid → shrink left
    3. Update answer

    VERY IMPORTANT sliding window pattern.
    --------------------------------------------
    */

    public int lengthOfLongestSubstringKDistinct_Optimal(String s, int k) {

        // Edge case
        if (k == 0 || s.length() == 0)
            return 0;

        Map<Character, Integer> freq = new HashMap<>();

        int left = 0;

        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            // Add current character
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // Shrink window if invalid
            while (freq.size() > k) {

                char leftChar = s.charAt(left);

                freq.put(leftChar, freq.get(leftChar) - 1);

                // Remove character if frequency becomes 0
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }

                left++;
            }

            // Update maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

public class Main {

    public static void main(String[] args) {

        Solution sol = new Solution();

        String s = "eceba";

        int k = 2;

        System.out.println(
            "Brute Force: " +
            sol.lengthOfLongestSubstringKDistinct_BF(s, k)
        );

        System.out.println(
            "Optimal: " +
            sol.lengthOfLongestSubstringKDistinct_Optimal(s, k)
        );
    }
}
