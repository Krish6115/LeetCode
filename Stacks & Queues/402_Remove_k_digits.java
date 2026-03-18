import java.util.*;
/*
Problem: Remove K Digits
Given a number as a string, remove k digits so that
the new number is the smallest possible.
*/
class RemoveKDigits {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - Try removing one digit at every possible position
    - Recursively repeat k times
    - Keep track of smallest result

    NOTE: This is highly inefficient and only for understanding

    TC: O(n^k) (very large, exponential)
    SC: O(n)
    */
    public String removeKdigitsBrute(String num, int k) {
        return helper(num, k);
    }
    private String helper(String num, int k) {
        if (k == 0) return removeLeadingZeros(num);
        if (num.length() == 0) return "0";
        String minStr = null;
        for (int i = 0; i < num.length(); i++) {
            String newNum = num.substring(0, i) + num.substring(i + 1);
            String candidate = helper(newNum, k - 1);
            if (minStr == null || compare(candidate, minStr) < 0) {
                minStr = candidate;
            }
        }
        return removeLeadingZeros(minStr);
    }
    private int compare(String a, String b) {
        if (a.length() != b.length()) return a.length() - b.length();
        return a.compareTo(b);
    }
    private String removeLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') i++;
        s = s.substring(i);
        return s.isEmpty() ? "0" : s;
    }
    /*
    -------------------------------------
    Optimal Approach (Monotonic Stack)
    -------------------------------------
    Idea:
    - Use a stack to maintain increasing digits
    - If current digit is smaller than stack top,
      remove stack top (greedy: remove bigger digits first)
    - Continue until k removals done
    - Build result and remove leading zeros

    TC: O(n)
    SC: O(n)
    */

    public String removeKdigitsOptimal(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char curr = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() > curr) {
                stack.pop();
                k--;
            }
            stack.push(curr);
        }
        // If still k left, remove from end
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        // Build result
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        res.reverse();
        // Remove leading zeros
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        String ans = res.substring(i);
        return ans.isEmpty() ? "0" : ans;
    }
}
