import java.util.*;
/*
Problem: Postfix to Infix Conversion
Convert a postfix expression (AB*C+)
into infix expression ((A*B)+C)
*/
class PostfixToInfix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Use recursion or manual parsing
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
      → form (op1 operator op2)
      → push back
    TC: O(n)
    SC: O(n)
    */
    public String postfixToInfix(String postfix) {
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
                String expr = "(" + op1 + c + op2 + ")";
                st.push(expr);
            }
        }
        return st.peek();
    }
    public static void main(String[] args) {
        PostfixToInfix sol = new PostfixToInfix();
        String postfix = "AB*C+";
        System.out.println("Postfix: " + postfix);
        System.out.println("Infix: " + sol.postfixToInfix(postfix));
    }
}
