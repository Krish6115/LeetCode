import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Minimum Window Substring
    ============================================

    Given two strings:
    - s → source string
    - t → target string

    Return the minimum window substring of s
    such that every character in t
    (including frequency) is present.

    If no such substring exists → return "".

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Smallest valid substring

    This is the opposite of:
    - longest substring problems

    Here:
    - Expand window until VALID
    - Then SHRINK aggressively
      to get minimum length

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    - Generate all substrings
    - Check whether substring contains all
      characters of t with correct frequency

    Keep track of smallest valid substring.

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "ADOBECODEBANC"
    t = "ABC"

    Valid windows:
    "ADOBEC"
    "CODEBA"
    "BANC"

    Smallest = "BANC"

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(n^3)

    - O(n^2) substrings
    - O(n) validation

    Space Complexity: O(1)
    */

    public String minWindow_BF(String s, String t) {

        if (s.length() == 0 || t.length() == 0)
            return "";

        int minLen = Integer.MAX_VALUE;

        String ans = "";

        for (int i = 0; i < s.length(); i++) {

            int[] freq = new int[128];

            for (char c : t.toCharArray()) {
                freq[c]++;
            }

            for (int j = i; j < s.length(); j++) {

                freq[s.charAt(j)]--;

                // Check validity
                boolean valid = true;

                for (char c : t.toCharArray()) {
                    if (freq[c] > 0) {
                        valid = false;
                        break;
                    }
                }

                // Update minimum answer
                if (valid && (j - i + 1) < minLen) {

                    minLen = j - i + 1;

                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Sliding Window)
    --------------------------------------------

    Maintain:
    - need map → frequencies required from t
    - window map → frequencies in current window

    Variables:
    - required → number of unique chars needed
    - formed → number of chars currently satisfied

    --------------------------------------------
    💡 HOW IT WORKS:
    --------------------------------------------

    Step 1:
    Expand right pointer until window becomes VALID.

    VALID means:
    👉 All required characters are present
       with correct frequencies.

    Step 2:
    Once valid:
    👉 Shrink from left as much as possible
       while keeping window valid.

    This helps find MINIMUM window.

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    s = "ADOBECODEBANC"
    t = "ABC"

    Expand:
    "ADOBEC" → valid

    Shrink:
    remove unnecessary chars

    Continue until:
    "BANC" → minimum valid window

    --------------------------------------------
    ⚠️ IMPORTANT CONCEPT:
    --------------------------------------------

    formed == required

    Means:
    👉 Current window satisfies ALL required
       characters and frequencies.

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
    --------------------------------------------

    Time Complexity: O(n)

    - Each character visited at most twice

    Space Complexity: O(1)

    - HashMaps store limited characters

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Minimum valid substring"

    Standard Template:
    1. Expand right until valid
    2. Shrink left to minimize
    3. Update answer

    This is one of the MOST IMPORTANT
    sliding window problems.
    --------------------------------------------
    */

    public String minWindow_Optimal(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Frequency map for target string
        Map<Character, Integer> need = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();

        // Sliding window variables
        int left = 0, right = 0;

        int formed = 0;

        Map<Character, Integer> window = new HashMap<>();

        // Store minimum window info
        int minLen = Integer.MAX_VALUE;

        int start = 0;

        while (right < s.length()) {

            char c = s.charAt(right);

            // Add current character
            window.put(c,
                    window.getOrDefault(c, 0) + 1);

            // Character requirement satisfied
            if (need.containsKey(c) &&
                window.get(c).intValue()
                == need.get(c).intValue()) {

                formed++;
            }

            // Try shrinking window
            while (left <= right &&
                   formed == required) {

                // Update minimum answer
                if (right - left + 1 < minLen) {

                    minLen = right - left + 1;

                    start = left;
                }

                char ch = s.charAt(left);

                // Remove from window
                window.put(ch,
                        window.get(ch) - 1);

                // Window no longer valid
                if (need.containsKey(ch) &&
                    window.get(ch)
                    < need.get(ch)) {

                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start,
                              start + minLen);
    }
}
