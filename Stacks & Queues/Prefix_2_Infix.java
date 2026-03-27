import java.util.*;
/*
Problem: Prefix to Infix Conversion
Convert a prefix expression (*-A/BC-/AKL)
into infix expression ((A-(B/C))*((A/K)-L))
*/
class PrefixToInfix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Process expression manually using recursion
    - Hard to manage order and parentheses
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
      → form (op1 operator op2)
      → push back
    TC: O(n)
    SC: O(n)
    */
    public String prefixToInfix(String prefix) {
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
                String expr = "(" + op1 + c + op2 + ")";
                st.push(expr);
            }
        }
        return st.peek();
    } 
    public static void main(String[] args) {
        PrefixToInfix sol = new PrefixToInfix();
        String prefix = "*-A/BC-/AKL";
        System.out.println("Prefix: " + prefix);
        System.out.println("Infix: " + sol.prefixToInfix(prefix));
    }
}
