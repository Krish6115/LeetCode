import java.util.*;
/*
Problem: Sum of Subarray Ranges
For each subarray:
range = max(subarray) - min(subarray)
Return the sum of all subarray ranges.
*/
class SubarrayRanges {
    /*
    Brute Force Approach
    Idea:
    - Fix starting index i
    - Extend subarray till j
    - Track current max and min
    - Add (max - min) for each subarray
    TC: O(n^2)
    SC: O(1)
    */
    public long subArrayRangesBrute(int[] nums) {
        int n = nums.length;
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            int currMax = nums[i];
            int currMin = nums[i];
            for (int j = i; j < n; j++) {
                currMax = Math.max(currMax, nums[j]);
                currMin = Math.min(currMin, nums[j]);
                totalSum += (currMax - currMin);
            }
        }
        return totalSum;
    }
    /*
    Optimal Approach (Monotonic Stack)
    Idea:
    Instead of computing range for each subarray,
    calculate contribution of each element.
    Contribution = (times as max) - (times as min)
    Use:
    - Monotonic decreasing stack → for max contribution
    - Monotonic increasing stack → for min contribution
    Final Answer:
    sum(max contributions) - sum(min contributions)

    TC: O(n)
    SC: O(n)
    */
    public long subArrayRangesOptimal(int[] nums) {
        int n = nums.length;
        long maxSum = getContribution(nums, true);
        long minSum = getContribution(nums, false);
        return maxSum - minSum;
    }
    private long getContribution(int[] nums, boolean isMax) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        long sum = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() &&
                   (i == n || (isMax
                        ? nums[stack.peek()] < nums[i]
                        : nums[stack.peek()] > nums[i]))) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                long count = (long)(mid - left) * (right - mid);
                sum += count * nums[mid];
            }
            stack.push(i);
        }
        return sum;
    }
}
