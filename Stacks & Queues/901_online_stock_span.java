import java.util.*;
/*
Problem: Online Stock Span
For each incoming price, return the number of consecutive days
(including today) where price <= today's price.
*/
class StockSpanner {
    /*
    -------------------------------------
    Brute Force Approach
    -------------------------------------
    Idea:
    - Store all previous prices
    - For each new price, go backwards
    - Count until a greater price is found
    TC: O(n) per query → O(n^2) total
    SC: O(n)
    */
    List<Integer> prices;
    public StockSpanner() {
        prices = new ArrayList<>();
    }
    public int nextBrute(int price) {
        prices.add(price);
        int span = 1;
        int i = prices.size() - 2;
        while (i >= 0 && prices.get(i) <= price) {
            span++;
            i--;
        }
        return span;
    }
    /*
    -------------------------------------
    Optimal Approach (Monotonic Stack)
    -------------------------------------
    Idea:
    - Stack stores (price, span)
    - While stack top <= current price:
        merge spans
    - Push (price, total span)
    TC: Amortized O(1)
    SC: O(n)
    */
    Stack<int[]> stack = new Stack<>();
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;
    }
}
