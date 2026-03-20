import java.util.*;
/*
Problem: Maximal Rectangle

Given a binary matrix filled with 0's and 1's,
find the largest rectangle containing only 1's.
*/
class MaximalRectangle {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - For each cell, treat it as top-left corner
    - Expand rectangle and check all 1s
    - Compute max area
    TC: O(n^3) or worse
    SC: O(1)
    */
    public int maximalRectangleBrute(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') continue;
                int minWidth = Integer.MAX_VALUE;
                for (int k = i; k < n && matrix[k][j] == '1'; k++) {
                    int width = 0;
                    for (int l = j; l < m && matrix[k][l] == '1'; l++) {
                        width++;
                    }
                    minWidth = Math.min(minWidth, width);
                    maxArea = Math.max(maxArea, minWidth * (k - i + 1));
                }
            }
        }
        return maxArea;
    }
    /*
    -------------------------------------
    Optimal Approach (Histogram + Stack)
    -------------------------------------
    Idea:
    - Convert each row into histogram
    - For each row, calculate largest rectangle
    - Use stack-based histogram solution

    TC: O(n * m)
    SC: O(m)
    */
    public int maximalRectangleOptimal(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix[0].length;
        int[] height = new int[m];
        int maxArea = 0;
        for (char[] row : matrix) {
            // Build histogram
            for (int i = 0; i < m; i++) {
                if (row[i] == '1') height[i]++;
                else height[i] = 0;
            }
            // Apply largest rectangle in histogram
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }
        return maxArea;
    }
    /*
    Helper: Largest Rectangle in Histogram (Optimal Stack)
    TC: O(n)
    SC: O(n)
    */
    private int largestRectangleArea(int[] heights) {
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
