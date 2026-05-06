import java.util.*;

class Solution {

    /*
    ============================================
    🧠 PROBLEM: Maximum Points You Can Obtain from Cards
    ============================================

    You are given an integer array cardPoints,
    where each element represents points on a card.

    You must pick exactly k cards from:
    - beginning
    - or end

    👉 Return the maximum score possible.

    --------------------------------------------
    💡 INTUITION:
    --------------------------------------------

    Since we can only pick from front or back,
    we try all combinations:
    
    - take all k from front
    - take k-1 from front and 1 from back
    - take k-2 from front and 2 from back
    - ...
    - take all k from back

    --------------------------------------------
    🚀 APPROACH 1: BRUTE FORCE
    --------------------------------------------

    Idea:
    Try every possible split:
    - x cards from front
    - (k-x) cards from back

    For every split:
    - calculate total manually

    --------------------------------------------
    🔍 EXAMPLE:
    --------------------------------------------

    arr = [1,2,3,4,5,6,1]
    k = 3

    Possibilities:
    [1,2,3] = 6
    [1,2] + [1] = 4
    [1] + [6,1] = 8
    [5,6,1] = 12

    Answer = 12

    --------------------------------------------
    ⏱ COMPLEXITY (Brute Force):
    --------------------------------------------

    Time Complexity: O(k^2)

    Space Complexity: O(1)
    */

    public int maxScore_BF(int[] cardPoints, int k) {

        int n = cardPoints.length;

        int maxPoints = 0;

        // x = cards taken from front
        for (int x = 0; x <= k; x++) {

            int total = 0;

            // Take x cards from front
            for (int i = 0; i < x; i++) {
                total += cardPoints[i];
            }

            // Take remaining cards from back
            for (int i = 0; i < k - x; i++) {
                total += cardPoints[n - 1 - i];
            }

            maxPoints = Math.max(maxPoints, total);
        }

        return maxPoints;
    }


    /*
    --------------------------------------------
    🚀 APPROACH 2: OPTIMAL (Sliding Window)
    --------------------------------------------

    Instead of recalculating sums every time:

    - Start with first k cards
    - Remove one card from front
    - Add one card from back

    This efficiently checks all combinations.

    --------------------------------------------
    ⚠️ KEY OBSERVATION:
    --------------------------------------------

    We only need k transitions.

    Each move:
    - remove from front
    - add from back

    --------------------------------------------
    ⏱ COMPLEXITY (Optimal):
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

    Very important interview pattern.
    --------------------------------------------
    */

    public int maxScore_Optimal(int[] cardPoints, int k) {

        int n = cardPoints.length;

        // Take first k cards
        int total = 0;

        for (int i = 0; i < k; i++) {
            total += cardPoints[i];
        }

        int maxPoints = total;

        // Slide:
        // remove from front, add from back
        for (int i = 0; i < k; i++) {

            total -= cardPoints[k - 1 - i];

            total += cardPoints[n - 1 - i];

            maxPoints = Math.max(maxPoints, total);
        }

        return maxPoints;
    }
}
