import java.util.*;

/*
Problem: Asteroid Collision

Given an array of integers representing asteroids moving in a row:
- Positive value -> asteroid moving right
- Negative value -> asteroid moving left

When two asteroids collide:
- Smaller one explodes
- If both equal, both explode
- Asteroids moving in same direction never collide
*/


class AsteroidCollision {

    /* ---------------------------------------------------------
       BRUTE FORCE APPROACH
       ---------------------------------------------------------
       Idea:
       Keep simulating the array until no more collisions happen.
       Whenever a positive asteroid is followed by a negative one,
       check their sizes and resolve the collision.

       TC: O(n^2)  (multiple passes through array)
       SC: O(n)    (list used for simulation)
    --------------------------------------------------------- */

    public int[] asteroidCollisionBrute(int[] asteroids) {

        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) list.add(a);

        boolean collision = true;

        while (collision) {
            collision = false;

            for (int i = 0; i < list.size() - 1; i++) {

                int a = list.get(i);
                int b = list.get(i + 1);

                if (a > 0 && b < 0) {

                    collision = true;

                    if (Math.abs(a) > Math.abs(b)) {
                        list.remove(i + 1);
                    }
                    else if (Math.abs(a) < Math.abs(b)) {
                        list.remove(i);
                    }
                    else {
                        list.remove(i + 1);
                        list.remove(i);
                    }

                    break;
                }
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }


    /* ---------------------------------------------------------
       OPTIMAL STACK APPROACH
       ---------------------------------------------------------
       Idea:
       Use a stack to store surviving asteroids.

       Collision happens only when:
       stack top > 0  AND current asteroid < 0

       Resolve collisions until one survives.

       TC: O(n)
       SC: O(n)
    --------------------------------------------------------- */

    public int[] asteroidCollisionStack(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int a : asteroids) {

            if (a > 0) {
                stack.push(a);
            }
            else {

                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }

                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                }
                else if (stack.peek() == -a) {
                    stack.pop();
                }
            }
        }

        int[] res = new int[stack.size()];
        int i = stack.size() - 1;

        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }

        return res;
    }
}
