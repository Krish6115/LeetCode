import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Count Subarrays with Exactly K Odd Numbers
    ============================================

    Given an integer array nums and an integer k,
    return the number of subarrays with exactly k odd numbers.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Subarrays with EXACTLY k odd numbers

    Brute Force:
    - Try all subarrays
    - Count odds manually

    Optimal:
    ❗ EXACTLY k = AtMost(k) - AtMost(k-1)

    This is a VERY IMPORTANT trick in CP.

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    - Fix start index
    - Expand end index
    - Count odd numbers
    - Stop when odd > k

    Example:
    nums = [1,1,2,1,1], k = 3

    Valid subarrays:
    [1,1,2,1]
    [1,2,1,1]

    Answer = 2

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(n^2)
    Space Complexity: O(1)
    */

    public int numberOfSubarrays_BF(int[] nums, int k) {
        int count = 0;

        for (int start = 0; start < nums.length; start++) {
            int oddCount = 0;

            for (int end = start; end < nums.length; end++) {

                if (nums[end] % 2 != 0)
                    oddCount++;

                if (oddCount > k)
                    break;

                if (oddCount == k)
                    count++;
            }
        }

        return count;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Sliding Window)
    --------------------------------------------

    Key Idea:
    EXACTLY k = AtMost(k) - AtMost(k-1)

    So we solve:
    - Count subarrays with at most k odds
    - Count subarrays with at most (k-1) odds
    - Subtract

    --------------------------------------------
    💡 HOW countAtMost WORKS:
    --------------------------------------------

    - Use sliding window
    - Expand right
    - If odd → decrease k
    - If k < 0 → shrink window

    Important:
    For each right:
    👉 number of valid subarrays = (right - left + 1)

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    nums = [1,1,2,1,1], k = 3

    AtMost(3) = 14
    AtMost(2) = 12

    EXACT = 14 - 12 = 2

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
    --------------------------------------------

    Time Complexity: O(n)
    Space Complexity: O(1)

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Exactly K" problems:
    → Convert to:
       AtMost(K) - AtMost(K-1)

    This trick is used in MANY problems:
    - Exactly K distinct
    - Exactly K odd/even
    - Exactly K sum constraints
    --------------------------------------------
    */

    public int countAtMost(int[] nums, int k) {
        int left = 0, res = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] % 2 != 0)
                k--;

            while (k < 0) {
                if (nums[left] % 2 != 0)
                    k++;
                left++;
            }

            res += (right - left + 1);
        }

        return res;
    }

    public int numberOfSubarrays_Optimal(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }
}

public class Main {
    public static void main(String[] args) {

        Solution sol = new Solution();

        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        System.out.println("Brute Force: " + sol.numberOfSubarrays_BF(nums, k));
        System.out.println("Optimal: " + sol.numberOfSubarrays_Optimal(nums, k));
    }
}
