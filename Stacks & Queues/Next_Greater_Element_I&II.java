//496. Next greater element i
class Solution {
    public int[] nextGreaterElement(int[] a, int[] b) {
        Stack<Integer> st = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int x : b){
            while(!st.isEmpty() && st.peek() < x)
                map.put(st.pop(), x);
            st.push(x);
        }
        while(!st.isEmpty()) map.put(st.pop(), -1);
        int[] r = new int[a.length];
        for(int i=0;i<a.length;i++) r[i] = map.get(a[i]);
        return r;
    }
}
//503. Next greater element ii
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);   
        
        Stack<Integer> stack = new Stack<>(); 
        for (int i = 0; i < 2 * n; i++) {
            int num = nums[i % n];
            
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int index = stack.pop();
                result[index] = num;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        
        return result;
    }
}
