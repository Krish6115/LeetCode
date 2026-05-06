import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Maximum Points You Can Obtain from Cards
    ============================================

    You are given an integer array cardPoints,
    where each element represents points on a card.

    You must pick exactly k cards from:
    - either the beginning
    - or the end

    👉 Return the maximum score possible.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    Since we can only take cards from:
    - front
    - back

    We try all possible combinations:
    - Take all k from front
    - Take k-1 from front and 1 from back
    - Take k-2 from front and 2 from back
    - ...
    - Take all k from back

    Instead of recalculating sums every time,
    we use Sliding Window technique.

    --------------------------------------------
    🚀 APPROACH: Sliding Window
    --------------------------------------------

    Step 1:
    - Take first k cards initially

    Step 2:
    - Gradually remove cards from front
    - Add cards from back

    This efficiently checks all combinations.

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    cardPoints = [1,2,3,4,5,6,1]
    k = 3

    Initial:
    Take first 3 cards:
    [1,2,3] → sum = 6

    Shift:
    Remove 3, add 1
    → [1,2,1] = 4

    Remove 2, add 6
    → [1,6,1] = 8

    Remove 1, add 5
    → [5,6,1] = 12

    Maximum = 12

    --------------------------------------------
    ⚠️ IMPORTANT OBSERVATION:
    --------------------------------------------

    We only need to check k combinations.

    Each move:
    - remove one from front
    - add one from back

    So total complexity becomes O(k).

    --------------------------------------------
    ⏱ COMPLEXITY:
    --------------------------------------------

    Time Complexity: O(k)

    Space Complexity: O(1)

    --------------------------------------------
    🧠 PATTERN TAKEAWAY:
    --------------------------------------------

    ❗ "Pick elements from both ends"

    Common trick:
    - Start with one side
    - Gradually shift contribution

    This is a very important Sliding Window variation.
    --------------------------------------------
    */

    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        // Sum of first k cards
        int total = 0;

        for (int i = 0; i < k; i++) {
            total += cardPoints[i];
        }

        // Store maximum score
        int maxPoints = total;

        // Slide window:
        // remove from front and add from back
        for (int i = 0; i < k; i++) {

            // Remove from front
            total -= cardPoints[k - 1 - i];

            // Add from back
            total += cardPoints[n - 1 - i];

            // Update maximum
            maxPoints = Math.max(maxPoints, total);
        }

        return maxPoints;
    }
}
