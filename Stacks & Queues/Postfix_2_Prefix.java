import java.util.*;
/*
Problem: Postfix to Prefix Conversion
Convert a postfix expression (ABC/-AK/L-*)
into prefix expression (*-A/BC-/AKL)
*/
class PostfixToPrefix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Process expression manually or recursively
    - Hard to maintain order without stack
    TC: O(n)
    SC: O(n)
    */
    /*
    -------------------------------------
    Optimal Approach (Stack)
    -------------------------------------
    Idea:
    - Traverse from LEFT → RIGHT
    - If operand → push
    - If operator → pop 2 operands
      → form operator + op1 + op2
      → push back
    TC: O(n)
    SC: O(n)
    */
    public String postfixToPrefix(String postfix) {
        Stack<String> st = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            // Operand
            if (Character.isLetterOrDigit(c)) {
                st.push(String.valueOf(c));
            }
            // Operator
            else {
                String op2 = st.pop();
                String op1 = st.pop();
                String expr = c + op1 + op2;
                st.push(expr);
            }
        }
        return st.peek();
    }
    public static void main(String[] args) {
        PostfixToPrefix sol = new PostfixToPrefix();
        String postfix = "ABC/-AK/L-*";
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix: " + sol.postfixToPrefix(postfix));
    }
}
