import java.util.*;
/*
Problem: Next Smaller Element
For each element, find the next smaller element to its right.
If none exists, return -1.
*/
class NextSmallerElement {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - For each element, check all elements to the right
    - First smaller element → answer
    TC: O(n^2)
    SC: O(n)
    */
    public int[] nextSmallerBrute(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    ans[i] = arr[j];
                    break;
                }
            }
        }
        return ans;
    }
    /*
    -------------------------------------
    Optimal Approach (Monotonic Stack)
    -------------------------------------
    Idea:
    - Traverse from right to left
    - Maintain increasing stack
    - Pop all elements >= current
    - Top of stack = next smaller
    TC: O(n)
    SC: O(n)
    */
    public int[] nextSmallerOptimal(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }
            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};
        NextSmallerElement sol = new NextSmallerElement();
        int[] brute = sol.nextSmallerBrute(arr);
        int[] optimal = sol.nextSmallerOptimal(arr);
        System.out.print("Brute: ");
        for (int x : brute) System.out.print(x + " ");
        System.out.print("\nOptimal: ");
        for (int x : optimal) System.out.print(x + " ");
    }
}
