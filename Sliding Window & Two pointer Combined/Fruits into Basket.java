import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Fruits Into Baskets
    ============================================

    You are given an array fruits[] where each element
    represents a fruit type.

    You have 2 baskets, and each basket can hold only
    one type of fruit.

    👉 Find the maximum number of fruits you can collect
       in a contiguous subarray containing at most 2 types.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    This is a classic:
    👉 "Longest subarray with at most 2 distinct elements"

    Normally we use:
    - Sliding window + HashMap

    BUT here we optimize:
    - Track only last two fruit types
    - No extra space needed

    --------------------------------------------
    🚀 APPROACH (Optimized without Map)
    --------------------------------------------

    Maintain:
    - lastFruit → most recent fruit type
    - secondLastFruit → second recent type
    - lastFruitStreak → count of consecutive lastFruit
    - currCount → current valid window size

    --------------------------------------------
    🔍 HOW IT WORKS:
    --------------------------------------------

    Case 1:
    If current fruit matches lastFruit OR secondLastFruit
    → expand window (currCount++)

    Case 2:
    If new third fruit appears
    → reset window:
       currCount = lastFruitStreak + 1

    Why?
    Because we can only keep:
    - last continuous block of lastFruit
    - plus new fruit

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    fruits = [1,2,1,2,3]

    Step-by-step:
    [1,2,1,2] → valid (2 types)
    Add 3 → now 3 types ❌

    So we keep:
    last streak of 2 (just before 3) → [2]
    new window → [2,3]

    --------------------------------------------
    ⏱ COMPLEXITY:
    --------------------------------------------

    Time Complexity: O(n)
    - Single pass

    Space Complexity: O(1)
    - Only variables used

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ This is SAME as:
    - Longest substring with at most 2 distinct chars
    - At most K distinct problems

    👉 Special optimization:
    When K is small (like 2), avoid HashMap

    --------------------------------------------
    */

    public int totalFruit(int[] fruits) {
        
        int maxlen = 0;

        int lastFruit = -1, secondLastFruit = -1;

        int currCount = 0, lastFruitStreak = 0;

        for (int fruit : fruits) {

            // Expand window if valid
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currCount++;
            } else {
                // Reset window when new fruit appears
                currCount = lastFruitStreak + 1;
            }

            // Update fruit tracking
            if (fruit == lastFruit) {
                lastFruitStreak++;
            } else {
                lastFruitStreak = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            // Update max length
            maxlen = Math.max(maxlen, currCount);
        }

        return maxlen;
    }
}
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] fruits = {1,2,1,2,3};

        System.out.println(sol.totalFruit(fruits));
    }
}
