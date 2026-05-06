import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Subarrays with Exactly K Distinct Integers
    ============================================

    Given an integer array nums and an integer k,
    return the number of subarrays with exactly
    k distinct integers.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    We need:
    👉 Count subarrays
    👉 Exactly k distinct elements

    Directly solving EXACTLY k is difficult.

    So we use the MOST IMPORTANT trick:

    ❗ EXACTLY(k) = ATMOST(k) - ATMOST(k-1)

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    - Generate all subarrays
    - Use HashSet to count distinct elements
    - If distinct count == k → increment answer

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    nums = [1,2,1,2,3]
    k = 2

    Valid subarrays:
    [1,2]
    [2,1]
    [1,2]
    [2,3]
    [1,2,1]
    [2,1,2]
    [1,2,1,2]

    Answer = 7

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(n^2)

    Space Complexity: O(n)
    */

    public int subarraysWithKDistinct_BF(int[] nums, int k) {

        int n = nums.length;

        int count = 0;

        for (int i = 0; i < n; i++) {

            Set<Integer> set = new HashSet<>();

            for (int j = i; j < n; j++) {

                set.add(nums[j]);

                if (set.size() == k)
                    count++;

                // Stop if distinct exceeds k
                else if (set.size() > k)
                    break;
            }
        }

        return count;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Sliding Window)
    --------------------------------------------

    Key Formula:
    EXACTLY(k) = ATMOST(k) - ATMOST(k-1)

    So:
    1. Count subarrays with at most k distinct
    2. Count subarrays with at most (k-1) distinct
    3. Subtract them

    --------------------------------------------
    💡 HOW atMostK WORKS:
    --------------------------------------------

    Maintain:
    - HashMap → frequencies
    - left and right pointers

    Expand:
    - Add nums[right]

    If new distinct element appears:
    → decrease K

    If K becomes negative:
    → shrink from left

    --------------------------------------------
    ⚠️ IMPORTANT OBSERVATION:
    --------------------------------------------

    For every valid window:
    👉 Number of valid subarrays ending at right =
       (right - left + 1)

    So:
    count += (right - left + 1)

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    nums = [1,2,1,2,3]
    k = 2

    AtMost(2) = 12
    AtMost(1) = 5

    EXACTLY(2) = 12 - 5 = 7

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
    --------------------------------------------

    Time Complexity: O(n)

    Space Complexity: O(k)

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Exactly K Distinct"

    ALWAYS think:
    EXACTLY(k) = ATMOST(k) - ATMOST(k-1)

    This is one of the MOST IMPORTANT
    Sliding Window patterns in CP/interviews.
    --------------------------------------------
    */

    public int atMostK(int[] nums, int K) {

        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;

        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add current element
            freq.put(nums[right],
                    freq.getOrDefault(nums[right], 0) + 1);

            // New distinct element
            if (freq.get(nums[right]) == 1) {
                K--;
            }

            // Shrink window if invalid
            while (K < 0) {

                freq.put(nums[left],
                        freq.get(nums[left]) - 1);

                // Remove distinct element
                if (freq.get(nums[left]) == 0) {
                    K++;
                }

                left++;
            }

            // Count valid subarrays
            count += (right - left + 1);
        }

        return count;
    }


    public int subarraysWithKDistinct_Optimal(int[] nums, int k) {

        return atMostK(nums, k)
             - atMostK(nums, k - 1);
    }
}
