import java.util.*;
/*
Problem: Infix to Postfix Conversion
Convert an infix expression (A+B) into postfix (AB+)
*/
class InfixToPostfix {
    /*
    -------------------------------------
    Basic Approach (Conceptual)
    -------------------------------------
    Idea:
    - Scan expression
    - Use rules manually for precedence
    - Hard to implement efficiently without stack

    TC: O(n^2) (if naive handling)
    SC: O(n)
    */
    /*
    -------------------------------------
    Optimal Approach (Stack)
    -------------------------------------
    Idea:
    - Use stack for operators
    - Add operands directly to result
    - Handle precedence and parentheses

    TC: O(n)
    SC: O(n)
    */

    public static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Operand → directly add
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // '(' → push
            else if (c == '(') {
                st.push(c);
            }
            // ')' → pop until '('
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }
                st.pop(); // remove '('
            }
            // Operator
            else {
                while (!st.isEmpty() && precedence(c) <= precedence(st.peek())) {
                    result.append(st.pop());
                }
                st.push(c);
            }
        }
        // Pop remaining operators
        while (!st.isEmpty()) {
            result.append(st.pop());
        }
        return result.toString();
    }
    // Function to define precedence
    private static int precedence(char c) {
        if (c == '^') return 3;
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        return -1;
    }
    /*
    -------------------------------------
    Main Method
    -------------------------------------
    */

    public static void main(String[] args) {
        String exp = "(p+q)*(m-n)";

        System.out.println("Infix: " + exp);
        System.out.println("Postfix: " + infixToPostfix(exp));
    }
}
