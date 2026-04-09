import java.util.*;

class Solution {
    /*
    🧠 PROBLEM: Longest Repeating Character Replacement

    Given a string s (only uppercase letters) and an integer k,
    you can replace at most k characters.

    👉 Find the length of the longest substring that can be
       made of the same character after at most k replacements.
       
    We want the longest substring where:
    - We can make all characters SAME
    - Using at most k replacements

    Key Idea:
    👉 In any window, if we know the most frequent character,
       we can replace all others to match it.

    So:
    replacements needed = window size - max frequency

    --------------------------------------------
    🚀 APPROACH: Sliding Window
    --------------------------------------------

    Maintain:
    - freq[26] → frequency of characters
    - maxCount → count of most frequent char in window

    Condition:
    👉 (window size - maxCount) ≤ k → VALID window
    👉 else → shrink window

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "AABABBA", k = 1

    Window grows:
    "AABA" → maxCount = 3 (A)
    replacements = 4 - 3 = 1 → valid

    Next:
    "AABAB" → replacements = 5 - 3 = 2 → invalid

    Shrink window

    Answer = 4

    --------------------------------------------
    ⚠️ IMPORTANT TRICK:
    --------------------------------------------

    We DO NOT decrease maxCount while shrinking.

    Why?
    - Even if maxCount becomes slightly incorrect,
      it still gives correct max length.
    - This avoids recomputation → keeps O(n)

    Time Complexity: O(n)
    - Each character visited once

    Space Complexity: O(1)
    - Fixed array of size 26

    🧠 PATTERN TAKEAWAY:
    
    ❗ "Make all elements same with k changes"
    → Think:
       window_size - max_freq ≤ k
    This pattern appears in MANY problems!

    */

    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];

        int left = 0, right = 0;

        int maxCount = 0;

        int maxLength = 0;

        while (right < s.length()) {

            // Add current character
            freq[s.charAt(right) - 'A']++;

            // Update most frequent character count
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);

            // If invalid window, shrink from left
            while ((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);

            right++;
        }

        return maxLength;
    }
}
