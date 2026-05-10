import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Minimum Window Subsequence
    ============================================

    Given two strings:
    - s1 → source string
    - s2 → target string

    Return the minimum substring of s1
    such that s2 appears as a subsequence
    inside that substring.

    If no such window exists → return "".

    --------------------------------------------
    ⚠️ DIFFERENCE FROM MINIMUM WINDOW SUBSTRING
    --------------------------------------------

    Minimum Window Substring:
    - Characters can appear in ANY order
    - Frequency matters

    Minimum Window Subsequence:
    - Characters MUST appear in SAME ORDER
    - Not necessarily contiguous

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Smallest substring of s1
    that contains s2 as a subsequence.

    Key Observation:
    - First find a valid subsequence window
    - Then shrink it backward
      to make it minimum.

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    - Generate all substrings
    - Check whether s2 is a subsequence
      of that substring

    Keep smallest valid window.

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s1 = "abcdebdde"
    s2 = "bde"

    Valid windows:
    "bcde"
    "bdde"

    Minimum = "bcde"

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(n^3)

    - O(n^2) substrings
    - O(n) subsequence check

    Space Complexity: O(1)
    */

    public String minWindow_BF(String s1, String s2) {

        int n = s1.length();

        int minLen = Integer.MAX_VALUE;

        String ans = "";

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                String sub = s1.substring(i, j + 1);

                // Check subsequence
                int p1 = 0, p2 = 0;

                while (p1 < sub.length()
                        && p2 < s2.length()) {

                    if (sub.charAt(p1)
                            == s2.charAt(p2)) {

                        p2++;
                    }

                    p1++;
                }

                // Valid subsequence
                if (p2 == s2.length()) {

                    if (sub.length() < minLen) {

                        minLen = sub.length();

                        ans = sub;
                    }
                }
            }
        }

        return ans;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Forward + Backward Scan)
    --------------------------------------------

    Step 1: Forward Scan
    ---------------------
    Find a valid window containing s2
    as subsequence.

    Step 2: Backward Scan
    ----------------------
    Once valid window found:
    move backward to minimize window.

    --------------------------------------------
    💡 HOW IT WORKS:
    --------------------------------------------

    FORWARD:
    - Move through s1
    - Match characters of s2 in order

    Once all chars matched:
    👉 We found a valid window

    BACKWARD:
    - Move backward from end
    - Match s2 in reverse
    - This minimizes the window

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s1 = "abcdebdde"
    s2 = "bde"

    Forward:
    "abcde" → matched

    Backward shrink:
    "bcde"

    Continue searching.

    --------------------------------------------
    ⚠️ IMPORTANT OBSERVATION:
    --------------------------------------------

    After minimizing:
    👉 Start next search from:
       windowStart + 1

    This avoids rechecking unnecessary windows.

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
    --------------------------------------------

    Time Complexity: O(n * m)

    - Forward + backward scans

    Space Complexity: O(1)

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Subsequence inside substring"

    Common Strategy:
    1. Forward scan → find valid subsequence
    2. Backward scan → minimize window

    This is a HARD sliding window/string problem.
    --------------------------------------------
    */

    public String minWindow_Optimal(String s1,
                                    String s2) {

        int n = s1.length();

        int m = s2.length();

        int minLen = Integer.MAX_VALUE;

        int startIndex = -1;

        int i = 0;

        while (i < n) {

            /*
            --------------------------------
            STEP 1: FORWARD SCAN
            --------------------------------
            Find valid subsequence window
            */

            int j = 0;

            int start = i;

            while (i < n) {

                if (s1.charAt(i)
                        == s2.charAt(j)) {

                    j++;

                    // Entire s2 matched
                    if (j == m)
                        break;
                }

                i++;
            }

            // No valid window found
            if (i == n)
                break;

            /*
            --------------------------------
            STEP 2: BACKWARD SCAN
            --------------------------------
            Minimize the window
            */

            int end = i;

            j = m - 1;

            while (i >= start) {

                if (s1.charAt(i)
                        == s2.charAt(j)) {

                    j--;

                    // Fully minimized
                    if (j < 0)
                        break;
                }

                i--;
            }

            // Current minimum window
            int windowStart = i;

            int windowLen =
                    end - windowStart + 1;

            // Update answer
            if (windowLen < minLen) {

                minLen = windowLen;

                startIndex = windowStart;
            }

            // Start next search
            i = windowStart + 1;
        }

        return startIndex == -1
                ? ""
                : s1.substring(startIndex,
                               startIndex + minLen);
    }
}
