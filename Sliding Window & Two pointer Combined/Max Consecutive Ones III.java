import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Max Consecutive Ones III
    ============================================

    Given a binary array nums (containing only 0s and 1s),
    and an integer k, you can flip at most k zeros to 1s.

    Find the maximum number of consecutive 1s possible.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We want the longest subarray containing:
    - At most k zeros (since we can flip them)

    So the problem becomes:
    👉 Find longest subarray with at most k zeros

    --------------------------------------------
    🚀 APPROACH: Sliding Window (Variable Size)
    --------------------------------------------

    Idea:
    - Use two pointers (left, right)
    - Expand right pointer
    - Count number of zeros in window
    - If zeros > k → shrink window from left

    Maintain:
    - Window always valid (zeros ≤ k)

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2

    Window expands:
    [1,1,1,0,0] → 2 zeros (valid)
    Add one more 0 → 3 zeros (invalid)

    Shrink from left until zeros ≤ k

    Final answer = 6

    --------------------------------------------
    ⏱ COMPLEXITY:
    --------------------------------------------

    Time Complexity: O(n)
    - Each element visited at most twice

    Space Complexity: O(1)
    - Only variables used

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    If question says:
    - "At most k"
    - "Longest subarray / substring"
    - "Flip / replace / change"

    👉 Think Sliding Window immediately
    */

    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int zerocount = 0;
        int maxlen = 0;

        for (int right = 0; right < nums.length; right++) {

            // Expand window
            if (nums[right] == 0) {
                zerocount++;
            }

            // Shrink window if invalid
            if (zerocount > k) {
                if (nums[left] == 0) {
                    zerocount--;
                }
                left++;
            }

            // Update max length
            maxlen = Math.max(maxlen, right - left + 1);
        }

        return maxlen;
    }
}

public class Main {
    public static void main(String[] args) {

        Solution sol = new Solution();

        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        System.out.println(sol.longestOnes(nums, k));
    }
}
