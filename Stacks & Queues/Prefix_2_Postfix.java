import java.util.*;
/*
Problem: Prefix to Postfix Conversion
Convert a prefix expression (*-A/BC-/AKL)
into postfix expression (ABC/-AK/L-*)
*/
class PrefixToPostfix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Recursively process expression
    - Hard to manage without stack
    TC: O(n)
    SC: O(n)
    */
    /*
    -------------------------------------
    Optimal Approach (Stack)
    -------------------------------------
    Idea:
    - Traverse from RIGHT → LEFT
    - If operand → push
    - If operator → pop 2 operands
      → form op1 + op2 + operator
      → push back
    TC: O(n)
    SC: O(n)
    */
    public String prefixToPostfix(String prefix) {
        Stack<String> st = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            // Operand
            if (Character.isLetterOrDigit(c)) {
                st.push(String.valueOf(c));
            }
            // Operator
            else {
                String op1 = st.pop();
                String op2 = st.pop();
                String expr = op1 + op2 + c;
                st.push(expr);
            }
        }
        return st.peek();
    }
    public static void main(String[] args) {
        PrefixToPostfix sol = new PrefixToPostfix();
        String prefix = "*-A/BC-/AKL";
        System.out.println("Prefix: " + prefix);
        System.out.println("Postfix: " + sol.prefixToPostfix(prefix));
    }
}
