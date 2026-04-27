import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Number of Substrings Containing All Three Characters
    ============================================

    Given a string s consisting only of characters 'a', 'b', and 'c',
    return the number of substrings that contain at least one of each.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Count all substrings that contain 'a', 'b', and 'c'

    Instead of checking all substrings (O(n^2)),
    we use Sliding Window.

    --------------------------------------------
    🚀 APPROACH: Sliding Window
    --------------------------------------------

    Maintain:
    - freq[3] → frequency of 'a', 'b', 'c'
    - left pointer

    Expand right pointer:
    - Add character to window

    When window becomes VALID:
    👉 (contains at least one 'a', 'b', 'c')

    Then:
    👉 All substrings from current window to end are valid

    So:
    res += (n - right)

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "abcabc"

    At right = 2 → window = "abc" (valid)

    Now:
    substrings starting at left:
    "abc", "abca", "abcab", "abcabc"

    Count = n - right = 6 - 2 = 4

    Then shrink window from left

    --------------------------------------------
    ⚠️ KEY TRICK:
    --------------------------------------------

    Once valid:
    👉 Count ALL substrings in one shot

    res += (n - right)

    This avoids nested loops → O(n)

    --------------------------------------------
    ⏱ COMPLEXITY:
    --------------------------------------------

    Time Complexity: O(n)
    - Each element processed at most twice

    Space Complexity: O(1)
    - Only array of size 3

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Count substrings with ALL conditions satisfied"

    Steps:
    1. Expand window
    2. When valid:
       → count += (n - right)
    3. Shrink window

    This pattern is VERY powerful
    --------------------------------------------
    */

    public int numberOfSubstrings(String s) {

        int[] freq = new int[3];

        int left = 0;
        int res = 0;

        for (int right = 0; right < s.length(); right++) {

            // Expand window
            freq[s.charAt(right) - 'a']++;

            // When valid (contains a, b, c)
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {

                // Count all valid substrings
                res += (s.length() - right);

                // Shrink window
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return res;
    }
}
