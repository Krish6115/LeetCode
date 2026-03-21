import java.util.*;
/*
Problem: Sliding Window Maximum
Given an array nums and window size k,
return the maximum element in every window of size k.
*/
class SlidingWindowMaximum {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - For each window, scan all k elements
    - Find maximum
    TC: O(n * k)
    SC: O(1) (excluding output array)
    */
    public int[] maxSlidingWindowBrute(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }
    /*
    -------------------------------------
    Optimal Approach (Deque / Monotonic Queue)
    -------------------------------------
    Idea:
    - Maintain deque storing indices
    - Elements in deque are in decreasing order
    - Front always has max element
    - Remove:
        1. Out-of-window indices
        2. Smaller elements from back
    TC: O(n)
    SC: O(k)
    */
    public int[] maxSlidingWindowOptimal(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            // Remove out-of-window indices
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            // Maintain decreasing order
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            // Add current index
            dq.offerLast(i);
            // Add result once window is formed
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}
