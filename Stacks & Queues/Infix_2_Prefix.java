import java.util.*;
/*
Problem: Infix to Prefix Conversion
Convert an infix expression ((p+q)*(c-d))
into prefix expression (*+pq-cd)
*/
class InfixToPrefix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Convert infix → postfix
    - Reverse logic to get prefix
    - Hard without stack handling
    TC: O(n)
    SC: O(n)
    */
    /*
    -------------------------------------
    Optimal Approach (Stack)
    -------------------------------------
    Idea:
    1. Reverse infix expression
    2. Swap '(' ↔ ')'
    3. Convert to postfix
    4. Reverse result → prefix

    TC: O(n)
    SC: O(n)
    */
    // Function to define precedence
    private static int precedence(char c) {
        if (c == '^') return 3;
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        return 0;
    }
    // Infix → Postfix helper
    private static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            else if (c == '(') {
                st.push(c);
            }
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }
                st.pop();
            }
            else {
                while (!st.isEmpty() && precedence(c) <= precedence(st.peek())) {
                    result.append(st.pop());
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) {
            result.append(st.pop());
        }
        return result.toString();
    }
    // Main conversion: Infix → Prefix
    public static String infixToPrefix(String infix) {
        // Step 1: Reverse
        StringBuilder sb = new StringBuilder(infix);
        sb.reverse();
        // Step 2: Swap brackets
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(')
                sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')')
                sb.setCharAt(i, '(');
        }
        // Step 3: Infix → Postfix
        String postfix = infixToPostfix(sb.toString());
        // Step 4: Reverse → Prefix
        return new StringBuilder(postfix).reverse().toString();
    }
    public static void main(String[] args) {
        String exp = "(p+q)*(c-d)";

        System.out.println("Infix: " + exp);
        System.out.println("Prefix: " + infixToPrefix(exp));
    }
}
