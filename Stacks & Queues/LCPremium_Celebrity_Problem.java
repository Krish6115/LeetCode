import java.util.*;
/*
Problem: Celebrity Problem
A celebrity is a person who:
1. Knows no one (row = all 0)
2. Is known by everyone (column = all 1 except self)
*/
class CelebrityProblem {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - For each person:
        check row → should be all 0
        check column → should be all 1 (except self)
    TC: O(n^2)
    SC: O(1)
    */
    public int celebrityBrute(int[][] M) {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            boolean knowsNoOne = true;
            boolean knownByAll = true;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                // If i knows someone → not celebrity
                if (M[i][j] == 1) {
                    knowsNoOne = false;
                    break;
                }
                // If someone doesn't know i → not celebrity
                if (M[j][i] == 0) {
                    knownByAll = false;
                    break;
                }
            }
            if (knowsNoOne && knownByAll) {
                return i;
            }
        }
        return -1;
    }
    /*
    -------------------------------------
    Optimal Approach (Two Pointer)
    -------------------------------------
    Idea:
    - Use two pointers to eliminate non-celebrities
    - Compare top and down:
        if top knows down → top not celebrity
        else → down not celebrity
    - Final candidate is verified
    TC: O(n)
    SC: O(1)
    */
    public int celebrityOptimal(int[][] M) {
        int n = M.length;
        int top = 0, down = n - 1;
        // Step 1: Find candidate
        while (top < down) {
            if (M[top][down] == 1) {
                top++;
            } else {
                down--;
            }
        }
        int candidate = top;
        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (M[candidate][i] == 1 || M[i][candidate] == 0) {
                return -1;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 1, 0}
        };
        CelebrityProblem sol = new CelebrityProblem();
        System.out.println("Brute Force: " + sol.celebrityBrute(M));
        System.out.println("Optimal: " + sol.celebrityOptimal(M));
    }
}
