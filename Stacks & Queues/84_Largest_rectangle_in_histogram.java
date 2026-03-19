import java.util.*;
/*
Problem: Largest Rectangle in Histogram
Given an array of heights, find the largest rectangle area.
*/
class LargestRectangleHistogram {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - For each bar, expand left and right
    - Find how far we can go while height >= current
    - Compute area

    TC: O(n^2)
    SC: O(1)
    */
    public int largestRectangleAreaBrute(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int left = i;
            int right = i;
            // expand left
            while (left >= 0 && heights[left] >= height) {
                left--;
            }
            // expand right
            while (right < n && heights[right] >= height) {
                right++;
            }
            int width = right - left - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }
    /*
    -------------------------------------
    Optimal Approach 1 (NSL + NSR Arrays)
    -------------------------------------
    Idea:
    - For each index, find:
        left boundary (nearest smaller to left)
        right boundary (nearest smaller to right)
    - width = right - left + 1
    - area = height * width

    TC: O(n)
    SC: O(n)
    */
    public int largestRectangleAreaBetter(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();

        int[] leftsmall = new int[n];
        int[] rightsmall = new int[n];
        // NSL
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            leftsmall[i] = st.isEmpty() ? 0 : st.peek() + 1;
            st.push(i);
        }
        st.clear();
        // NSR
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            rightsmall[i] = st.isEmpty() ? n - 1 : st.peek() - 1;
            st.push(i);
        }
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = rightsmall[i] - leftsmall[i] + 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }
    /*
    -------------------------------------
    Optimal Approach 2 (Single Stack)
    -------------------------------------
    Idea:
    - Maintain increasing stack
    - When current height is smaller, compute area
    - Treat each popped element as smallest bar

    TC: O(n)
    SC: O(n)
    */
    public int largestRectangleAreaOptimal(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() &&
                  (i == n || heights[stack.peek()] >= (i < n ? heights[i] : 0))) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
